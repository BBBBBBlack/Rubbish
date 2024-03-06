package com.example.tea_demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.tea_demo.domain.Email;
import com.example.tea_demo.domain.ResponseResult;
import com.example.tea_demo.domain.entity.User;
import com.example.tea_demo.mapper.UserMapper;
import com.example.tea_demo.service.EmailService;
import com.example.tea_demo.utils.EmailUtil;
import com.example.tea_demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${spring.mail.username}")
    String from;

    @Override
    public ResponseResult sendMail(String email) {
        String code = UUID.randomUUID().toString();
        Email mail = new Email(from, email, null, code);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("user_email", email);
        if (userMapper.selectOne(queryWrapper) != null) {
            return new ResponseResult(200, "用户已存在");
        }
        mail.setSubject("注册邮件：请复制以下验证码（30分钟内有效）\n");
        redisUtil.setCacheObject(mail.getTo(), code, 30, TimeUnit.MINUTES);
        EmailUtil.send(mail);
        return new ResponseResult(200, "邮件发送成功");
    }
}

