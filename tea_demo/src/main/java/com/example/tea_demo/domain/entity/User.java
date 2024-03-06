package com.example.tea_demo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;

    @TableId
    private Long id;

    private String userEmail;

    private String nickName;

    private String password;

    private String phoneNumber;

    private String picture;

    private String userStatus;

    private String alipayAmount;

    /**
     * 用户类型——1为普通用户，2为商户
     */
    private Integer type;
}