package com.wly.controller;
import com.wly.entity.Catogory;
import com.wly.repository.CatogoryRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goodstype")
public class GoodsTypeController {

    @Autowired
    public CatogoryRepository catogoryRepository;


    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable int index, @PathVariable int limit){
        return new Result(0,"",catogoryRepository.count(),catogoryRepository.findAll(index,limit));
    }

    @PutMapping("/update")
    public Result update(@RequestBody Catogory catogory){
        catogoryRepository.update(catogory);
        return new Result(200,"修改成功！",1,"");
    }

    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam("id") String id){
        catogoryRepository.deleteById(id);
        return new Result(200,"删除成功！",1,"");
    }

    //增加功能
    @PostMapping("/save")
    public Result save(@RequestBody Catogory catogory){
        catogoryRepository.save(catogory);
        return new Result(200,"保存成功！",0,"");
    }
}
