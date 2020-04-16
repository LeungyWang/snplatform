package com.wly.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Admin {
    private String id;
    private String name;
    private String idno;
    private String username;
    private String password;
    private Date createtime;
}
