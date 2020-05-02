package com.wly.entity;

import lombok.Data;

import java.util.Date;

@Data
public class NewsComment {
    private String commentid;
    private String userid;
    private Integer newsid;
    private String review_content;
    private Date review_time;
}
