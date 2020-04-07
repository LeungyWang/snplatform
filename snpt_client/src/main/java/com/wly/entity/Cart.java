package com.wly.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Cart {
    private String cart_id;
    private String customer_id;
    private Goods goods;
    private int product_amount;
    private double price;
    private Date add_time;
    private Date modified_time;
}
