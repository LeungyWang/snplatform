package com.wly.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Seed {
    private String id;
    private String name;
    private String unit;
    private double price;
    private double quantity;
    private Date purchasedate;
    private String userid;
}
