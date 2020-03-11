package com.wly.controller;

import com.wly.entity.Element;
import com.wly.repository.ElementRepository;
import com.wly.repository.FarmWorkRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/farmwork")
public class FarmWorkController {

    @Autowired
    public FarmWorkRepository farmWorkRepository;

    @GetMapping("/findAll")
    public Result findAll(){
        return  new Result(200,"查询成功！",0,farmWorkRepository.findAll());
    }


}
