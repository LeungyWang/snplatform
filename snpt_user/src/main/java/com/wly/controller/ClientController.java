package com.wly.controller;

import com.wly.entity.Client;
import com.wly.entity.Farmer;
import com.wly.repository.ClientRepository;
import com.wly.repository.FarmerRepository;
import entity.Result;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    public IdWorker idWorker;


    //查找所有农户
    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
        List<Client> clients = clientRepository.findAll(index,limit);

        return new Result(0,"",clientRepository.count(),clients);
    }

    /**
     * 农户信息保存
     * @param client
     */
    @PostMapping("/save")
    public Result saveFarmer(@RequestBody Client client){
        if (clientRepository.findByPhone(client.getPhone())==null) {
            client.setId("CL"+idWorker.nextId());
            clientRepository.save(client);
            return new Result(200,"增加成功！",1,"");
        }else{
            return new Result(500,"此手机号已注册！",1,"");
        }
    }

    //修改农户信息
    @PutMapping("/update")
    public void update(@RequestBody Client client){
        clientRepository.update(client);
    }

    //删除用户
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") String id){
        clientRepository.deleteById(id);
    }


}
