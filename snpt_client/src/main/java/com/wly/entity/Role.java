package com.wly.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Role {
    private String id;
    private String rolename;
    private String rolecode;
    private String remark;
    private List<Auth> auths;
    private Date createtime;
}
