package com.wly.controller;

import com.wly.entity.Soil;
import com.wly.repository.SoilRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.List;


@RestController
@RequestMapping("/soil")
public class SoilController {

    @Autowired
    public SoilRepository soilRepository;

    @Autowired
    public IdWorker idWorker;

    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable int index, @PathVariable int limit){
        return new Result(0,"",soilRepository.count(),soilRepository.findAll(index,limit));
    }

    @GetMapping("/findAllByUserId/{index}/{limit}/{userid}")
    public Result findAll(@PathVariable int index, @PathVariable int limit, @PathVariable String userid){
        return new Result(0,"",soilRepository.countByUserid(userid),soilRepository.findAllByUserId(userid,index,limit));
    }

    @GetMapping("/findSoilByuserid/{userid}")
    public Result findSoilByuserid(@PathVariable String userid){
        return new Result(0,"",0,soilRepository.findSoilByuserid(userid));

    }

    @GetMapping("/findNameByuserid/{userid}")
    public List<Soil> findNameByuserid(@PathVariable String userid){
        return soilRepository.findNameByuserid(userid);
    }


    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable String id){
        return new Result(0,"",1,soilRepository.findById(id));
    }

    @PutMapping("/update")
        public Result update(@RequestBody Soil soil){
        soilRepository.update(soil);
        return new Result(200,"修改成功！",1,"");
    }

    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam("id") String id){
        soilRepository.deleteById(id);
        return new Result(200,"删除成功！",1,"");
    }

    @PostMapping("/save/{userid}")
    public Result save(@RequestBody Soil soil,@PathVariable String userid){
        soil.setId("SL"+idWorker.nextId());
        soil.setUserid(userid);
        soilRepository.save(soil);
        return new Result(200,"保存成功！",0,"");
    }

    /**
     * 土地农事统计
     */




}
