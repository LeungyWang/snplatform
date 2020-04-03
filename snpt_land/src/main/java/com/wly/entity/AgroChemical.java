package com.wly.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AgroChemical {
    private String id;
    private Soil soil;
    private double organic;//有机质
    private double nitrogen;//全氮
    private double phosphorus;//全磷
    private double potassium;//全钾
    private double iron;//铁
    private double manganese;//锰
    private double zinc;//锌
    private double raphosphorus;//速效磷
    private double rapotassium;//速效钾
    private double ph;
    private Date date;


}
