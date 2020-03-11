package com.wly.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SowRecord {
    private String id;
    private  String userid;
    private Soil soil;
    private Seed seed;
    private FarmWork farmWork;
    private String content;
    private String starttime;
    private String endtime;
    private Date startdate;
    private Date enddate;

}
