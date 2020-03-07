package com.wly.controller;

import com.wly.entity.AgroChemical;
import com.wly.repository.AgroChemicalRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("agrochemical")
public class AgroChemicalController {

    @Autowired
    public AgroChemicalRepository agroChemicalRepository;

    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable int index, @PathVariable int limit){
        return new Result(0,"",agroChemicalRepository.count(),agroChemicalRepository.findAll(index,limit));
    }


    @GetMapping("/findAllByuserid/{index}/{limit}/{userid}")
    public Result findAll(@PathVariable int index, @PathVariable int limit, @PathVariable String userid){
        return new Result(0,"",agroChemicalRepository.countByUserid(userid),agroChemicalRepository.findAllByuserid(userid,index,limit));
    }

    @GetMapping("/findLatestphByid/{id}")
    public double findLatestphByid(@PathVariable long id){
        AgroChemical agroChemical = agroChemicalRepository.findLatestphByid(id);
        double ph = agroChemical.getPh();
        return ph;
    }

    @GetMapping("/findLatestmacroByid/{id}")
    public List<Double> findLatestmacroByid(@PathVariable long id){
        AgroChemical agroChemical = agroChemicalRepository.findLatestmacroByid(id);
        double organic = agroChemical.getOrganic();
        double nitrogen = agroChemical.getNitrogen();
        double raphosphorus = agroChemical.getRaphosphorus();
        double rapotassium = agroChemical.getRapotassium();
        List<Double> value = Arrays.asList(organic,nitrogen,raphosphorus,rapotassium);
        return value;
    }

    @GetMapping("/findLatestmicroByid/{id}")
    public List<Double> findLatestmicroByid(@PathVariable long id){
        AgroChemical agroChemical = agroChemicalRepository.findLatestmicroByid(id);
        double iron = agroChemical.getIron();
        double manganese = agroChemical.getManganese();
        double zinc = agroChemical.getZinc();
        List<Double> value = Arrays.asList(iron,manganese,zinc);
        return value;
    }

    @PostMapping("/save")
    public Result save(@RequestBody AgroChemical agroChemical){
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
