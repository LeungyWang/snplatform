package com.wly.controller;

import com.wly.entity.AgroType;
import com.wly.entity.ElementStandard;
import com.wly.repository.AgroTypeRepository;
import com.wly.repository.ElementStandardRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agrotype")
public class AgroTypeController {

    @Autowired
    public AgroTypeRepository agroTypeRepository;


    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable int index, @PathVariable int limit){
        return new Result(0,"",agroTypeRepository.count(),agroTypeRepository.findAll(index,limit));
    }

    @PutMapping("/update")
    public Result update(@RequestBody AgroType agroType){
        agroTypeRepository.update(agroType);
        return new Result(200,"修改成功！",1,"");
    }

    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam("id") String id){
        agroTypeRepository.deleteById(id);
        return new Result(200,"删除成功！",1,"");
    }

    //增加功能
    @PostMapping("/save")
    public Result save(@RequestBody AgroType agroType){
        agroTypeRepository.save(agroType);
        return new Result(200,"保存成功！",0,"");
    }
}
