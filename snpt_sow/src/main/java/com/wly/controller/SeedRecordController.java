package com.wly.controller;

import com.wly.repository.SeedRecordRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seedrecord")
public class SeedRecordController {

    @Autowired
    public SeedRecordRepository seedRecordRepository;

    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable int index,@PathVariable int limit){
        return new Result(0,"",seedRecordRepository.count(),seedRecordRepository.findAll(index,limit));
    }
}
