package com.wly.controller;

import com.wly.entity.Element;
import com.wly.entity.FarmWork;
import com.wly.repository.ElementRepository;
import com.wly.repository.FarmWorkRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable int index, @PathVariable int limit){
        return new Result(0,"",farmWorkRepository.count(),farmWorkRepository.findAllWorks(index,limit));
    }

    @PutMapping("/update")
    public Result update(@RequestBody FarmWork farmWork){
        farmWorkRepository.update(farmWork);
        return new Result(200,"修改成功！",1,"");
    }

    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam("id") String id){
        farmWorkRepository.deleteById(id);
        return new Result(200,"删除成功！",1,"");
    }

    //增加功能
    @PostMapping("/save")
    public Result save(@RequestBody FarmWork farmWork){
        farmWorkRepository.save(farmWork);
        return new Result(200,"保存成功！",0,"");
    }

}
