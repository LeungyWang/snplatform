package com.wly.controller;

import com.wly.entity.Report;
import com.wly.entity.Soil;
import com.wly.repository.ReportRepository;
import com.wly.repository.SoilRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.Date;


@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    public ReportRepository reportRepository;

    @Autowired
    public IdWorker idWorker;

    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable int index, @PathVariable int limit){
        return new Result(0,"",reportRepository.count(),reportRepository.findAll(index,limit));
    }

    @GetMapping("/findAllByUserId/{index}/{limit}/{userid}")
    public Result findAllByUserId(@PathVariable int index, @PathVariable int limit, @PathVariable String userid){
        return new Result(0,"",reportRepository.countByUserid(userid),reportRepository.findAllByUserId(userid,index,limit));
    }



    @GetMapping("/findById/{id}")
    public Report findById(@PathVariable String id){
        return reportRepository.findById(id);
    }

//    @PutMapping("/update")
//        public Result update(@RequestBody Soil soil){
//        soilRepository.update(soil);
//        return new Result(200,"修改成功！",1,"");
//    }

    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam("id") String id){
        reportRepository.deleteById(id);
        return new Result(200,"删除成功！",1,"");
    }

    @PostMapping("/save")
    public Result save(@RequestBody Report report){
        report.setId("RP"+idWorker.nextId());
        report.setCreatetime(new Date());
        reportRepository.save(report);
        return new Result(200,"保存成功！",0,"");
    }



}
