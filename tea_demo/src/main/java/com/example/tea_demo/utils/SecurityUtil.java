package com.example.tea_demo.utils;


import com.example.tea_demo.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static LoginUser getLoginUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (LoginUser) authentication.getPrincipal();
    }
    public static Long getNowUserId(){
        LoginUser loginUser = getLoginUser();
        return loginUser.getUser().getId();
    }
}
