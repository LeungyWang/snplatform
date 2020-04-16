package com.wly.entity;

import lombok.Data;

@Data
public class ElementStandard {
    private String id;
    private Element element;
    private Double value;
    private Double points;
    private String comment;
}
