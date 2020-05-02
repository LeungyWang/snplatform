package com.wly.entity;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDetails {
    private String order_detailid;
    private String order_id ;
    private Goods goods;
    private Integer product_cnt;
    private Date modified_time;
}
