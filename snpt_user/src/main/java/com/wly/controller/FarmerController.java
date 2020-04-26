package com.wly.controller;

import com.wly.entity.Farmer;
import com.wly.entity.User;
import com.wly.repository.FarmerRepository;
import com.wly.repository.RoleRepository;
import com.wly.repository.UserRepository;
import entity.Result;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/farmer")
public class FarmerController {

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public IdWorker idWorker;


    //查找所有农户
    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
        List<Farmer> farmers = farmerRepository.findAll(index,limit);
        return new Result(0,"",farmerRepository.count(),farmers);
    }

    //查找农户个人信息
    @GetMapping("/findInfoByPhone/{phone}")
    public Farmer findInfoByPhone(@PathVariable String phone){
        return farmerRepository.findByPhone(phone);
    }

    /**
     * 农户信息保存
     * @param farmer
     */
    @PostMapping("/save")
    public Result saveFarmer(@RequestBody Farmer farmer){
        if (farmerRepository.findByPhone(farmer.getPhone())==null) {
            farmer.setId("FM"+idWorker.nextId());
            farmerRepository.save(farmer);
            return new Result(200,"增加成功！",1,"");
        }else{
            return new Result(500,"此手机号已注册！",1,"");
        }
    }

    //修改农户信息
    @PutMapping("/update")
    public void update(@RequestBody Farmer farmer){
        farmerRepository.updateByPhone(farmer);
    }

    //删除用户
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") String id){
        farmerRepository.deleteById(id);
    }


}
