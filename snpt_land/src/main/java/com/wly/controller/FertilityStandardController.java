package com.wly.controller;

import com.wly.entity.ElementStandard;
import com.wly.entity.FertilityStandard;
import com.wly.repository.ElementStandardRepository;
import com.wly.repository.FertilityStandardRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fertilitystandard")
public class FertilityStandardController {

    @Autowired
    public FertilityStandardRepository fertilityStandardRepository;


    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable int index, @PathVariable int limit){
        return new Result(0,"",fertilityStandardRepository.count(),fertilityStandardRepository.findAll(index,limit));
    }

    @PutMapping("/update")
    public Result update(@RequestBody FertilityStandard fertilityStandard){
        fertilityStandardRepository.update(fertilityStandard);
        return new Result(200,"修改成功！",1,"");
    }

    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam("id") int id){
        fertilityStandardRepository.deleteById(id);
        return new Result(200,"删除成功！",1,"");
    }

    //增加功能
    @PostMapping("/save")
    public Result save(@RequestBody FertilityStandard fertilityStandard){
        fertilityStandardRepository.save(fertilityStandard);
        return new Result(200,"保存成功！",0,"");
    }

    //返回土壤智能评级结果
    @GetMapping("/findByLevel/{level}")
    public FertilityStandard findByLevel(@PathVariable Integer level){
        return fertilityStandardRepository.findByLevel(level);
    }
}
