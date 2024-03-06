package com.example.tea_demo.controller;

import com.example.tea_demo.domain.ResponseResult;
import com.example.tea_demo.domain.entity.Store;
import com.example.tea_demo.domain.entity.User;
import com.example.tea_demo.service.UserService;
import com.example.tea_demo.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ResponseResult login(@RequestParam String userEmail,
                                @RequestParam String password) {
        User user = new User();
        user.setUserEmail(userEmail);
        user.setPassword(password);
        return userService.login(user);
    }

    @RequestMapping("/logout")
    public ResponseResult logout() {
        return userService.logout();
    }

    @PostMapping("/register")
    public ResponseResult register(@RequestParam String userEmail,
                                   @RequestParam String password,
                                   @RequestParam Integer type,
                                   @RequestParam String code) {
        User user = new User();
        user.setUserEmail(userEmail);
        user.setPassword(password);
        user.setType(type);
        return userService.register(user, code);
    }

    @PostMapping("/fileInfo")
    @PreAuthorize("hasAuthority('seller:updateinfo')")
    public ResponseResult fillInfo(String name,
                                   String phoneNumber,
                                   MultipartFile picture,
                                   String link,
                                   String description) {
        User user = SecurityUtil.getLoginUser().getUser();
        user.setNickName(name);
        user.setPhoneNumber(phoneNumber);
        Store store = new Store(user.getId(), name, link, description);
        return userService.fillInfo(user, store, picture);
    }


    @RequestMapping("/hello")
    @PreAuthorize("hasAuthority('all:login')")
    public String hello() {
        return "hello";
    }
}
