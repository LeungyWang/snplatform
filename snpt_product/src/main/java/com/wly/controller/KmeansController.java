package com.wly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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


    @RequestMapping("/soil/{n}/{p}/{k}")
    public String PythonPredcit(@PathVariable float n,@PathVariable float p,@PathVariable float k) {
        String url = "http://kmeans/predict/"+n+'/'+p+'/'+k;
        return restTemplate.getForEntity(url, String.class).getBody();
    }

}
