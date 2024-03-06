package com.example.tea_demo.controller;

import com.example.tea_demo.domain.ResponseResult;
import com.example.tea_demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService mailService;

    @PostMapping("/sendEmail")
    public ResponseResult sendMail(@RequestParam String email) {
        return mailService.sendMail(email);
    }
}
