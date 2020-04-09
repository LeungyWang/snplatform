package com.wly.controller;

import com.wly.entity.Catogory;
import com.wly.entity.Order;
import com.wly.repository.OrderRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public IdWorker idWorker;

    //查找顾客所有订单
    @GetMapping("/findAll/{userid}")
    public Result findAll(@PathVariable String userid){
        return new Result(0,"",orderRepository.countByUserid(userid),orderRepository.findOrderByuserid(userid));
    }

//    //查找所有产品正在审核中的产品
//    @GetMapping("/findAll/{index}/{limit}")
//    public Result findAll(@PathVariable int index, @PathVariable int limit){
//        return new Result(0,"",goodsRepository.count(),goodsRepository.findAll(index,limit));
//    }

    //查找功能
//    @GetMapping("/findAll/{userid}")
//    public Result findAll(@PathVariable String userid){
//        return new Result(0,"",goodsRepository.countByUserid(userid),goodsRepository.findGoodByuserid(userid));
//    }
    //查找功能
    @GetMapping("/findById/{id}")
    public Order findById(@PathVariable String id){
        return orderRepository.findById(id);
    }

    //新增订单功能
    @PostMapping("/save/{userid}")
    public Result save(@RequestBody Order order, @PathVariable String userid){
        order.setOrder_status(1);
        order.setCustomer_id(userid);
        order.setOrder_id("OD"+idWorker.nextId());
        return new Result(200,"保存成功！",0,"");
    }

    //删除功能
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable String id){
        orderRepository.deleteById(id);
        return new Result(200,"删除成功！",0,"");
    }

//    //修改功能
//    @PutMapping("/update/{status}")
//    public Result update(@RequestBody Goods goods){
//        goodsRepository.update(goods);
//        return new Result(200,"修改成功！",1,"");
//    }

//    //农产品申请上架
//    @PutMapping("/verifyapply/{id}")
//    public Result verifyApply(@PathVariable String id){
//        goodsRepository.updateStatus(statusRepository.findById("300"),id);
//        return new Result(200,"产品正在审核中！",1,"");
//    }
//
//    //农产品取消上架申请
//    @PutMapping("/cancelapply/{id}")
//    public Result cancelApply(@PathVariable String id){
//        goodsRepository.updateStatus(statusRepository.findById("200"),id);
//        return new Result(200,"产品上架申请取消成功！",1,"");
//    }
//
//    //农户下架农产品
//    @PutMapping("/soldout/{id}")
//    public Result soldout(@PathVariable String id){
//        goodsRepository.updateStatus(statusRepository.findById("600"),id);
//        return new Result(200,"产品下架成功！",1,"");
//    }
//
//    //管理员通过农产品审核
//    @PutMapping("/approve/{id}")
//    public Result approveApply(@PathVariable String id){
//        goodsRepository.updateStatus(statusRepository.findById("400"),id);
//        return new Result(200,"产品审核通过成功！",1,"");
//    }
//
//    //管理员不通过农产品审核
//    @PutMapping("/disapprove/{id}")
//    public Result disapproveApply(@PathVariable String id){
//        goodsRepository.updateStatus(statusRepository.findById("500"),id);
//        return new Result(200,"产品审核不通过成功！",1,"");
//    }
}
