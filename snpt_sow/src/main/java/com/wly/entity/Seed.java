package com.wly.entity;

import lombok.Data;

@Data
public class Seed {
    private long id;
    private String name;
    private double germinationrate;
    private double cleanrate;
    private double weight;
    private double cost;
}
