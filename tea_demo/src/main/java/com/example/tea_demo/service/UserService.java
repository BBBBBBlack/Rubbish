package com.example.tea_demo.service;

import com.example.tea_demo.domain.ResponseResult;
import com.example.tea_demo.domain.entity.Store;
import com.example.tea_demo.domain.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    ResponseResult login(User user);

    ResponseResult logout();

    ResponseResult register(User user, String code);

    ResponseResult fillInfo(User user, Store store, MultipartFile picture);
}
