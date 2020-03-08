package com.wly.controller;

import com.wly.entity.SowRecord;
import com.wly.repository.SowRecordRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

@RestController
@RequestMapping("/sowrecord")
public class SowRecordController {

    @Autowired
    public SowRecordRepository sowRecordRepository;

    @Autowired
    public IdWorker idWorker;

    //查找功能
    @GetMapping("/findAll/{index}/{limit}/{userid}")
    public Result findAll(@PathVariable int index,@PathVariable int limit,@PathVariable String userid){
        return new Result(0,"",sowRecordRepository.countByUserid(userid),sowRecordRepository.findAllByUserid(userid,index,limit));
    }

    //查找功能
    @GetMapping("/findAll/{userid}")
    public Result findAll(@PathVariable String userid){
        return new Result(200,"",sowRecordRepository.countByUserid(userid),sowRecordRepository.findAll(userid));
    }

    //查找功能
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable String id){
        return new Result(200,"查询成功！",0,sowRecordRepository.findById(id));
    }

    //增加功能
    @PostMapping("/save/{userid}")
    public Result save(@RequestBody SowRecord sowRecord, @PathVariable String userid){
        sowRecord.setUserid(userid);
        sowRecord.setId("SD"+idWorker.nextId());
        sowRecordRepository.save(sowRecord);
        return new Result(200,"保存成功！",0,"");
    }

    //删除功能
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable String id){
        sowRecordRepository.deleteById(id);
        return new Result(200,"删除成功！",0,"");
    }

//    //修改功能
//    @PutMapping("/update")
//    public Result update(@RequestBody Seed seed){
//        seedRepository.update(seed);
//        return new Result(200,"修改成功！",1,"");
//    }




}
