package com.wly.entity;

import lombok.Data;

import java.util.Date;

@Data
public class News {
    private String newsid;
    private String title;
    private String content;
    private String source;
    private Date release_time;
    private NewsType newsType;
    private Integer hits;
    private Integer status;
    private String auditor;
}
