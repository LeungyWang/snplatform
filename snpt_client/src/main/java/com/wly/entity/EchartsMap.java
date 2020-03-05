package com.wly.entity;

import lombok.Data;

import java.util.List;

@Data
public class EchartsMap {
    private List<Double> value;
    private List<String> name;
}
