package com.wly.controller;

import com.wly.entity.AgroChemical;
import com.wly.repository.AgroChemicalRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("agrochemical")
public class AgroChemicalController {

    @Autowired
    public AgroChemicalRepository agroChemicalRepository;

    @Autowired
    public IdWorker idWorker;

    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable int index, @PathVariable int limit){
        return new Result(0,"",agroChemicalRepository.count(),agroChemicalRepository.findAll(index,limit));
    }

    @GetMapping("/findById/{id}")
    public AgroChemical findById(@PathVariable String id){
        return agroChemicalRepository.findById(id);
    }


    @GetMapping("/findAllByuserid/{index}/{limit}/{userid}")
    public Result findAll(@PathVariable int index, @PathVariable int limit, @PathVariable String userid){
        return new Result(0,"",agroChemicalRepository.countByUserid(userid),agroChemicalRepository.findAllByuserid(userid,index,limit));
    }

    @GetMapping("/findArgoByuserid/{userid}")
    public Result findArgo(@PathVariable String userid){
        return new Result(200,"",agroChemicalRepository.countByUserid(userid),agroChemicalRepository.findArgoByuserid(userid));
    }

    @GetMapping("/findphByid/{id}")
    public double findLatestphByid(@PathVariable String id){
        AgroChemical agroChemical = agroChemicalRepository.findphByid(id);
        double ph = agroChemical.getPh();
        return ph;
    }

    @GetMapping("/findmacroByid/{id}")
    public List<Double> findLatestmacroByid(@PathVariable String id){
        AgroChemical agroChemical = agroChemicalRepository.findmacroByid(id);
        double organic = agroChemical.getOrganic();
        double nitrogen = agroChemical.getNitrogen();
        double raphosphorus = agroChemical.getRaphosphorus();
        double rapotassium = agroChemical.getRapotassium();
        List<Double> value = Arrays.asList(organic,nitrogen,raphosphorus,rapotassium);
        return value;
    }

    @GetMapping("/findmicroByid/{id}")
    public List<Double> findLatestmicroByid(@PathVariable String id){
        AgroChemical agroChemical = agroChemicalRepository.findmicroByid(id);
        double iron = agroChemical.getIron();
        double manganese = agroChemical.getManganese();
        double zinc = agroChemical.getZinc();
        List<Double> value = Arrays.asList(iron,manganese,zinc);
        return value;
    }

    @GetMapping("/getComments/{id}")
    public List<String> getComments(@PathVariable String id){
        AgroChemical agroChemical = agroChemicalRepository.findById(id);
        double organic = agroChemical.getOrganic();
        double nitrogen = agroChemical.getNitrogen();
        double raphosphorus = agroChemical.getRaphosphorus();
        double rapotassium = agroChemical.getRapotassium();
        double ph = agroChemical.getPh();
        String ogcomment = agroChemicalRepository.findCommentByvalue(organic,"EL001");
        String ngcomment = agroChemicalRepository.findCommentByvalue(nitrogen,"EL002");
        String racomment = agroChemicalRepository.findCommentByvalue(raphosphorus,"EL003");
        String rgcomment = agroChemicalRepository.findCommentByvalue(rapotassium,"EL004");
        String phcomment = agroChemicalRepository.findCommentByvalue(ph,"EL005");
        List<String> comments = Arrays.asList(ogcomment,ngcomment,racomment,rgcomment,phcomment);
        return comments;
    }


    @PostMapping("/save")
    public Result save(@RequestBody AgroChemical agroChemical){
        agroChemical.setId("AC"+idWorker.nextId());
        agroChemical.setDate(new Date());
        agroChemicalRepository.save(agroChemical);
        return new Result(200,"保存成功！",0,"");
    }

    @PutMapping("/update")
    public Result update(@RequestBody AgroChemical agroChemical){
        agroChemical.setDate(new Date());
        agroChemicalRepository.update(agroChemical);
        return new Result(200,"修改成功！",1,"");
    }



}
