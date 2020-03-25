package com.wly.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {
    private String id;
    private String username;
    private String nickname;
    private String sex;
    private String password;
    private String phone;
    private String status;
    private List<Role> roles;
    private Date createtime;
}
