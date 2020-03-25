package com.wly.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Auth {
    private String authid;
    private String name;
    private String pId;
    private String url;
    private Boolean open;
    private Boolean checked;
    private Date createtime;
}
