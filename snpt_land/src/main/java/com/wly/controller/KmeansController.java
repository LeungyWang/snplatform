package com.wly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/predict")
public class KmeansController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/health")
    public String PythonHealth() {
        String url = "http://health";
        return restTemplate.getForEntity(url, String.class).getBody();
    }

    //获取土地评价等级
    @GetMapping("/soil/{n}/{p}/{k}")
    public Integer PythonPredcit(@PathVariable double n,@PathVariable double p,@PathVariable double k) {
        String url = "http://kmeans/predict/"+n+'/'+p+'/'+k;
        Map star = restTemplate.getForEntity(url, Map.class).getBody();
        Integer type = (Integer) star.get("soiltype");
        return type;
    }

}
