package com.wly.controller;

import com.wly.entity.Order;
import com.wly.entity.OrderDetails;
import com.wly.repository.OrderDetailsRepository;
import com.wly.repository.OrderRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.List;


@RestController
@RequestMapping("/orderdetails")
public class OrderDetailsController {

    @Autowired
    public OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public IdWorker idWorker;

    //查询订单下的所有产品 有分页 卖家用
    @GetMapping("/findAll/{order_id}/{index}/{limit}")
    public Result findAllDetails(@PathVariable String order_id,@PathVariable int index,@PathVariable int limit){
        return new Result(0,"",orderDetailsRepository.countByOrderid(order_id),orderDetailsRepository.findAllByOrderId(order_id,index,limit));
    }


    //查找订单下所有的产品 无分页 用户用
    @GetMapping("/findAll/{orderid}")
    public List<OrderDetails> findAll(@PathVariable String orderid){
        return orderDetailsRepository.findDetailsByOrderid(orderid);
    }

    //查找功能
    @GetMapping("/findById/{id}")
    public OrderDetails findById(@PathVariable String id){
        return orderDetailsRepository.findById(id);
    }

    //新增订单功能
    @PostMapping("/save")
    public void save(@RequestBody OrderDetails orderDetails){
        orderDetails.setOrder_detailid("DT"+idWorker.nextId());
        orderDetailsRepository.save(orderDetails);
    }

    //删除功能
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable String id){
        orderDetailsRepository.deleteById(id);
        return new Result(200,"删除成功！",0,"");
    }
}

