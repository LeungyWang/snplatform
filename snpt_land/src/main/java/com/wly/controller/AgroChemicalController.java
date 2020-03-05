package com.wly.controller;

import com.wly.entity.AgroChemical;
import com.wly.entity.EchartsMap;
import com.wly.entity.Soil;
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

    @GetMapping("/geteCharts")
    public EchartsMap geteCharts(){
        EchartsMap echartsMap = new EchartsMap();
        List<Double> value = Arrays.asList(0.1,0.4,0.6,0.9);
        List<String > name = Arrays.asList("有机质", "速效氮", "速效磷", "速效钾");
        echartsMap.setValue(value);
        echartsMap.setName(name);
        return echartsMap;
    }

}
