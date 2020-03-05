package com.wly.controller;

import com.wly.repository.HarvestRepository;
import com.wly.repository.SeedRecordRepository;
import com.wly.repository.SeedRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seed")
public class SeedController {

    @Autowired
    public SeedRepository seedRepository;

    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable int index,@PathVariable int limit){
        return new Result(0,"",seedRepository.count(),seedRepository.findAll(index,limit));
    }
}
