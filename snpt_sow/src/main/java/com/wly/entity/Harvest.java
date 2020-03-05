package com.wly.entity;

import lombok.Data;

@Data
public class Harvest {
    private long id;
    private long landid;
    private double area;
    private double output;
    private double cost;
    private double income;
    private double netyield;
}
