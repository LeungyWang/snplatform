package com.wly.controller;

import com.alibaba.fastjson.JSON;
import com.wly.entity.*;
import com.wly.repository.OrderDetailsRepository;
import com.wly.repository.OrderRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public IdWorker idWorker;

    //查找顾客所有订单
    @GetMapping("/cs/findAll/{userid}")
    public List<Order> findAllCS(@PathVariable String userid){
        return orderRepository.findOrdersByCUserId(userid);
    }

    //查找卖家的所有订单
    @GetMapping("/bs/findAll/{userid}/{index}/{limit}")
    public Result findAllBS(@PathVariable String userid,@PathVariable int index,@PathVariable int limit){
        return new Result(0,"",orderRepository.countByUserid(userid),orderRepository.findOrderByBUserid(userid,index,limit));
    }

    //查找功能
    @GetMapping("/findById/{id}")
    public Order findById(@PathVariable String id){
        return orderRepository.findById(id);
    }

    //新增订单功能
    @PostMapping("/save/{userid}")
    public void save(@RequestBody Order order, @PathVariable String userid, HttpSession session){
        //保存订单
        String orderInfoStr = order.getOrderInfoStr();
        order.setOrder_status(1);
        order.setCustomer_id(userid);
        orderRepository.save(order);
        //保存订单下的商品到数据库中
        List<Cart> Carts =  JSON.parseArray(orderInfoStr,Cart.class);
        for (int i = 0;i<Carts.size();i++){
            Cart cart = Carts.get(i);
            Goods good = cart.getGoods();
            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setGoods(good);
            orderDetail.setOrder_detailid("DT"+idWorker.nextId());
            orderDetail.setOrder_id(order.getOrder_id());
            orderDetail.setProduct_cnt(cart.getProduct_amount());
            orderDetailsRepository.save(orderDetail);
        }
    }

    //删除功能
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable String id){
        orderRepository.deleteById(id);
        return new Result(200,"删除成功！",0,"");
    }

    //订单发货功能
    @PutMapping("/deliver/{order_id}")
    public Result deliver(@PathVariable String order_id){
        orderRepository.updateOrderShipping(new Date(),order_id);
        return new Result(200,"产品发货成功！",1,"");
    }

    //订单收货功能
    @PutMapping("/receive/{order_id}")
    public Result receive(@PathVariable String order_id){
        orderRepository.updateOrderReceive(new Date(),order_id);
        return new Result(200,"产品收货成功！",1,"");
    }

    //订单支付成功操作
    @PutMapping("/pay/{money}/{order_id}")
    public void pay_success(@PathVariable Double money,@PathVariable String order_id){
        orderRepository.updateOrderPaying(new Date(),money,order_id);
    }



}
