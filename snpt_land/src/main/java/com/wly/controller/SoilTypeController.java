package com.wly.controller;

import com.wly.entity.SoilType;
import com.wly.repository.SoilTypeRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/soiltype")
public class SoilTypeController {

    @Autowired
    public SoilTypeRepository soilTypeRepository;

    @GetMapping("/findAllTypes")
    public Result findAllTypes(){
        return new Result(0,"",soilTypeRepository.count(),soilTypeRepository.findAllTypes());
    }




}
