package com.wly.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Farmer {
    private String id;
    private String name;
    private String phone;
    private String idno;
    private String creditno;
    private String province;
    private String district;
    private String county;
    private String village;
}