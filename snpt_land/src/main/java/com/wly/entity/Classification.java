package com.wly.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Classification {
    private String id;
    private String name;
    private String introduce;
    private Date createtime;
}
