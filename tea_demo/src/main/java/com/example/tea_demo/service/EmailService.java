package com.example.tea_demo.service;

import com.example.tea_demo.domain.ResponseResult;

public interface EmailService {
    ResponseResult sendMail(String email);
}
