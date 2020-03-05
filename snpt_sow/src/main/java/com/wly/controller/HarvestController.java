package com.wly.controller;

import com.wly.repository.HarvestRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/harvest")
public class HarvestController {

    @Autowired
    public HarvestRepository harvestRepository;

    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable int index,@PathVariable int limit){
        return new Result(0,"",harvestRepository.count(),harvestRepository.findAll(index,limit));
    }
}
