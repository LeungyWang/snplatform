package com.wly.entity;

import lombok.Data;

import java.util.Date;

@Data
public class GoodsComment {
    private String commentid;
    private String userid;
    private String productid;
    private String review_content;
    private Date review_time;
}
