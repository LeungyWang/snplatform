package com.wly.controller;

import com.wly.entity.Element;
import com.wly.repository.ElementRepository;
import com.wly.repository.ProductionTypeRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/prodcutiontype")
public class ProductionTypeController {

    @Autowired
    public ProductionTypeRepository productionTypeRepository;

    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable int index, @PathVariable int limit){
        return new Result(0,"",productionTypeRepository.count(),productionTypeRepository.findAll(index,limit));
    }

    @GetMapping("/findAllPT")
    public Result findAllPT(){
        return new Result(200,"",productionTypeRepository.count(),productionTypeRepository.findAllPT());
    }


}
