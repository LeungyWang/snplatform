package com.wly.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Report {
    private String id;
    private  AgroChemical agroChemical;
    private String comment;
    private Date createtime;

}
