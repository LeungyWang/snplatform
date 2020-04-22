package com.wly.entity;

import lombok.Data;

import java.util.Date;

@Data
public class NewsComemnt {
    private String commentid;
    private User user;
    private String newsid;
    private String review_content;
    private Date review_time;
}
