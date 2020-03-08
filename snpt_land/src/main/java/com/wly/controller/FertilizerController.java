package com.wly.controller;

import com.wly.entity.Fertilizer;
import com.wly.repository.FertilizerRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.Date;

@RestController
@RequestMapping("/fertilizer")
public class FertilizerController {

    @Autowired
    public FertilizerRepository fertilizerRepository;
    @Autowired
    public IdWorker idWorker;

    //查找功能
    @GetMapping("/findAll/{index}/{limit}/{userid}")
    public Result findAll(@PathVariable int index,@PathVariable int limit,@PathVariable String userid){
        return new Result(0,"",fertilizerRepository.countByUserid(userid),fertilizerRepository.findAllByUserid(userid,index,limit));
    }

    //查找功能
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable String id){
        return new Result(200,"查询成功！",0,fertilizerRepository.findById(id));
    }

    //增加功能
    @PostMapping("/save/{userid}")
    public Result save(@RequestBody Fertilizer fertilizer,@PathVariable String userid){
        fertilizer.setUserid(userid);
        fertilizer.setPurchasedate(new Date());
        fertilizer.setId("FL"+idWorker.nextId());
        fertilizerRepository.save(fertilizer);
        return new Result(200,"保存成功！",0,"");
    }

    //删除功能
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable String id){
        fertilizerRepository.deleteById(id);
        return new Result(200,"删除成功！",0,"");
    }

    //修改功能
    @PutMapping("/update")
    public Result update(@RequestBody Fertilizer fertilizer){
        fertilizerRepository.update(fertilizer);
        return new Result(200,"修改成功！",1,"");
    }
}
