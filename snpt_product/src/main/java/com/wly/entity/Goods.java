package com.wly.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Goods {
    private String id;
    private String name;
    private String originplace;
    private String category;
    private String picture;
    private double quantity;
    private String content;
    private String unit;
    private double cost;
    private double price;
    private String status;
    private String applicant;
    private Date applicationtime;

}
