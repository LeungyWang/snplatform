package com.wly.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SeedRecord {
    private long id;
    private long landid;
    private long seedid;
    private long fertilizer;
    private long seedform;
    private double totalcost;
    private Date time;

}
