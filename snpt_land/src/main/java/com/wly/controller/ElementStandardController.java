package com.wly.controller;

import com.wly.entity.Element;
import com.wly.entity.ElementStandard;
import com.wly.repository.ElementRepository;
import com.wly.repository.ElementStandardRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/elementstandard")
public class ElementStandardController {

    @Autowired
    public ElementStandardRepository elementStandardRepository;


    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable int index, @PathVariable int limit){
        return new Result(0,"",elementStandardRepository.count(),elementStandardRepository.findAll(index,limit));
    }

    @PutMapping("/update")
    public Result update(@RequestBody ElementStandard elementStandard){
        elementStandardRepository.update(elementStandard);
        return new Result(200,"修改成功！",1,"");
    }

    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam("id") String id){
        elementStandardRepository.deleteById(id);
        return new Result(200,"删除成功！",1,"");
    }

    //增加功能
    @PostMapping("/save")
    public Result save(@RequestBody ElementStandard elementStandard){
        elementStandardRepository.save(elementStandard);
        return new Result(200,"保存成功！",0,"");
    }
}
