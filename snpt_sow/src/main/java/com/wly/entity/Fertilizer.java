package com.wly.entity;

import lombok.Data;

@Data
public class Fertilizer {
    private long id;
    private  String name;
    private double dosage;
    private String usage;
    private double cost;
}
