package com.wly.controller;

import com.wly.entity.Goods;
import com.wly.repository.GoodsRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.Date;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    public GoodsRepository goodsRepository;

    @Autowired
    public IdWorker idWorker;

    //查找功能
    @GetMapping("/findAll/{index}/{limit}/{userid}")
    public Result findAll(@PathVariable int index, @PathVariable int limit, @PathVariable String userid){
        return new Result(0,"",goodsRepository.countByUserid(userid),goodsRepository.findAllByUserId(userid,index,limit));
    }

    //查找功能
    @GetMapping("/findAll/{userid}")
    public Result findAll(@PathVariable String userid){
        return new Result(0,"",goodsRepository.countByUserid(userid),goodsRepository.findGoodByuserid(userid));
    }

    //查找功能
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable String id){
        return new Result(200,"查询成功！",0,goodsRepository.findById(id));
    }

    //增加功能
    @PostMapping("/save/{userid}")
    public Result save(@RequestBody Goods goods,@PathVariable String userid){
        goods.setApplicationtime(new Date());
        goods.setApplicant(userid);
        goods.setId("GD"+idWorker.nextId());
        goods.setStatus("未上架");
        goodsRepository.save(goods);
        return new Result(200,"保存成功！",0,"");
    }

    //删除功能
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable String id){
        goodsRepository.deleteById(id);
        return new Result(200,"删除成功！",0,"");
    }

    //修改功能
    @PutMapping("/update/{status}")
    public Result update(@RequestBody Goods goods){
        goodsRepository.update(goods);
        return new Result(200,"修改成功！",1,"");
    }

}
