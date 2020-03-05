package com.wly.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AgroChemical {
    private long id;
    private Soil soil;
    private double organic;
    private double nitrogen;
    private double phosphorus;
    private double potassium;
    private double ahnitrogen;
    private double raphosphorus;
    private double rapotassium;
    private double ph;
    private Date date;


}
