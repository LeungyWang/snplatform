package com.wly.controller;

import com.wly.entity.Seed;
import com.wly.repository.SeedRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.Date;

@RestController
@RequestMapping("/seed")
public class SeedController {

    @Autowired
    public SeedRepository seedRepository;

    @Autowired
    public IdWorker idWorker;

    //查找功能
    @GetMapping("/findAll/{index}/{limit}/{userid}")
    public Result findAll(@PathVariable int index,@PathVariable int limit,@PathVariable String userid){
        return new Result(0,"",seedRepository.countByUserid(userid),seedRepository.findAllByUserid(userid,index,limit));
    }

    //查找功能
    @GetMapping("/findAll/{userid}")
    public Result findAll(@PathVariable String userid){
        return new Result(0,"",seedRepository.countByUserid(userid),seedRepository.findAllSeeds(userid));
    }

    //查找功能
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable String id){
        return new Result(200,"查询成功！",0,seedRepository.findById(id));
    }

    //增加功能
    @PostMapping("/save/{userid}")
    public Result save(@RequestBody Seed seed,@PathVariable String userid){
        seed.setPurchasedate(new Date());
        seed.setUserid(userid);
        seed.setId("SD"+idWorker.nextId());
        seedRepository.save(seed);
        return new Result(200,"保存成功！",0,"");
    }

    //删除功能
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable String id){
        seedRepository.deleteById(id);
        return new Result(200,"删除成功！",0,"");
    }

    //修改功能
    @PutMapping("/update")
    public Result update(@RequestBody Seed seed){
        seedRepository.update(seed);
        return new Result(200,"修改成功！",1,"");
    }




}
