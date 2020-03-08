package com.wly.controller;

import com.wly.entity.Pesticide;
import com.wly.repository.PesticideRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.Date;

@RestController
@RequestMapping("/pesticide")
public class PesticideController {

    @Autowired
    public PesticideRepository pesticideRepository;
    @Autowired
    public IdWorker idWorker;

    //查找功能
    @GetMapping("/findAll/{index}/{limit}/{userid}")
    public Result findAll(@PathVariable int index,@PathVariable int limit,@PathVariable String userid){
        return new Result(0,"",pesticideRepository.countByUserid(userid),pesticideRepository.findAllByUserid(userid,index,limit));
    }

    //查找功能
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable String id){
        return new Result(200,"查询成功！",0,pesticideRepository.findById(id));
    }

    //增加功能
    @PostMapping("/save/{userid}")
    public Result save(@RequestBody Pesticide pesticide, @PathVariable String userid){
        pesticide.setUserid(userid);
        pesticide.setPurchasedate(new Date());
        pesticide.setId("PC"+idWorker.nextId());
        pesticideRepository.save(pesticide);
        return new Result(200,"保存成功！",0,"");
    }

    //删除功能
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable String id){
        pesticideRepository.deleteById(id);
        return new Result(200,"删除成功！",0,"");
    }

    //修改功能
    @PutMapping("/update")
    public Result update(@RequestBody Pesticide pesticide){
        pesticideRepository.update(pesticide);
        return new Result(200,"修改成功！",1,"");
    }
}
