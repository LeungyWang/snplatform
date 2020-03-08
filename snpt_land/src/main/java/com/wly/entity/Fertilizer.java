package com.wly.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Fertilizer {
    private String id;
    private  String name;
    private String specification;
    private double price;
    private int quantity;
    private Date purchasedate;
    private String userid;
}
