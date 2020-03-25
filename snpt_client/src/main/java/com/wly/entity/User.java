package com.wly.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String id;
    private String username;
    private String nickname;
    private String sex;
    private String password;
    private String phone;
    private String status;
    private Date createtime;
}
