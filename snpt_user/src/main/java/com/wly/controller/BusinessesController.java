package com.wly.controller;

import com.wly.entity.Businesses;
import com.wly.entity.Client;
import com.wly.repository.BusinessesRepository;
import com.wly.repository.ClientRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.List;

@RestController
@RequestMapping("/businesses")
public class BusinessesController {

    @Autowired
    private BusinessesRepository businessesRepository;

    @Autowired
    public IdWorker idWorker;

    //查找所有商户
    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
        List<Businesses> businesses = businessesRepository.findAll(index,limit);
        return new Result(0,"",businessesRepository.count(),businesses);
    }

    //查找商户个人信息
    @GetMapping("/findInfoByPhone/{phone}")
    public Businesses findInfoByPhone(@PathVariable String phone){
        return businessesRepository.findByPhone(phone);
    }


    /**
     *商户信息保存
     * @param businesses
     */
    @PostMapping("/save")
    public Result saveFarmer(@RequestBody Businesses businesses){
        if (businessesRepository.findByPhone(businesses.getPhone())==null) {
            businesses.setId("BS"+idWorker.nextId());
            businessesRepository.save(businesses);
            return new Result(200,"增加成功！",1,"");
        }else{
            return new Result(500,"此手机号已注册！",1,"");
        }
    }

    //修改商户信息
    @PutMapping("/update")
    public void update(@RequestBody Businesses businesses){
        businessesRepository.update(businesses);
    }

    //删除商户
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") String id){
        businessesRepository.deleteById(id);
    }


}
