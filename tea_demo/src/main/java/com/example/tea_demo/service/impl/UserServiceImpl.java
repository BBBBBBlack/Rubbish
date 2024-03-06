package com.example.tea_demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.tea_demo.domain.LoginUser;
import com.example.tea_demo.domain.ResponseResult;
import com.example.tea_demo.domain.entity.Store;
import com.example.tea_demo.domain.entity.User;
import com.example.tea_demo.mapper.RoleMapper;
import com.example.tea_demo.mapper.StoreMapper;
import com.example.tea_demo.mapper.UserMapper;
import com.example.tea_demo.service.UserService;
import com.example.tea_demo.utils.FileUtil;
import com.example.tea_demo.utils.JwtUtil;
import com.example.tea_demo.utils.RedisUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private StoreMapper storeMapper;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        //使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //authenticate存入redis
        redisUtil.setCacheObject("login:" + userId, loginUser);
        //把token响应给前端
        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return new ResponseResult(200, "登陆成功", map);
    }


    @Override
    public ResponseResult logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        redisUtil.deleteObject("login:" + userid);
        return new ResponseResult(200, "退出成功");
    }

    @Override
    public ResponseResult register(User user, String code) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("user_email", user.getUserEmail());
        if (userMapper.selectOne(queryWrapper) != null) {
            return new ResponseResult(200, "用户已存在");
        }
        String cc = redisUtil.getCacheObject(user.getUserEmail());
        if (code.equals(cc)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setNickName(UUID.randomUUID().toString());
            userMapper.insert(user);
            roleMapper.addRole(user.getId(), user.getType().longValue());
            redisUtil.deleteObject(user.getUserEmail());
            return new ResponseResult(200, "注册成功，请重新登录");
        }
        return new ResponseResult(200, "验证码错误");
    }

    @Override
    public ResponseResult fillInfo(User user, Store store, MultipartFile picture) {
        //更改头像
        try {
            String pic_url = FileUtil.upLoadProImag(picture);
            if (StringUtils.hasText(user.getPicture())) {
                FileUtil.delete(user.getPicture());
            }
            user.setPicture(pic_url);
        } catch (Exception e) {
            return new ResponseResult(500, e.getMessage());
        }
        userMapper.updateById(user);
        storeMapper.updateById(store);
        return new ResponseResult(200, "已更新");
    }
}