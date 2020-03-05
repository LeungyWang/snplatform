package com.wly.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String name;
    private String gender;
    private String telephone;
    private Date registdate;
    private String address;

}
