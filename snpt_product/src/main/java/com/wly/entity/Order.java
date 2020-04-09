package com.wly.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private String order_id;
    private String customer_id;
    private String shipping_user;
    private String province;
    private String city;
    private String district;
    private String address;
    private Integer payment_method;
    private Integer order_status;
    private double order_money;
    private double payment_money;
    private Date shipping_time;
    private Date pay_time;
    private Date receive_time;
    private Date modified_time;
    private Date create_time;
}
