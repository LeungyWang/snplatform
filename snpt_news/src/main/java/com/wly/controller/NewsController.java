package com.wly.controller;

import com.wly.entity.NewsType;
import com.wly.repository.NewsTypeRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/newstype")
public class NewsTypeController {

    @Autowired
    public NewsTypeRepository newsTypeRepository;


    //查找所有的资讯类型 有分页
    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable int index, @PathVariable int limit){
        return new Result(0,"",newsTypeRepository.count(),newsTypeRepository.findAll(index,limit));
    }

    //查找所有的资讯类型 无分页
    @GetMapping("/findAll")
    public Result findAll(){
        return new Result(0,"",newsTypeRepository.count(),newsTypeRepository.findNewsType());
    }

    @PutMapping("/update")
    public Result update(@RequestBody NewsType newsType){
        newsTypeRepository.update(newsType);
        return new Result(200,"修改成功！",1,"");
    }

    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam("id") Integer id){
        newsTypeRepository.deleteById(id);
        return new Result(200,"删除成功！",1,"");
    }

    //增加功能
    @PostMapping("/save")
    public Result save(@RequestBody NewsType newsType){
        newsTypeRepository.save(newsType);
        return new Result(200,"保存成功！",0,"");
    }
}
