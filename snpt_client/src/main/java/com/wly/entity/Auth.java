package com.wly.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Auth {
    private int id;
    private String name;
    @JsonProperty("pId")
    private int pId;
    private String url;
    private Boolean open=true;
    private Boolean checked=false;
    private Date createtime;
}
