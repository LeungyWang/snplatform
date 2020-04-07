package com.wly.controller;

import com.wly.entity.Cart;
import com.wly.entity.Catogory;
import com.wly.entity.Goods;
import com.wly.repository.CartRepository;
import com.wly.repository.CatogoryRepository;
import com.wly.repository.GoodsRepository;
import com.wly.repository.StatusRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    public CartRepository cartRepository;

    @Autowired
    public IdWorker idWorker;



    //查找功能
    @GetMapping("/findAll/{userid}")
    public List<Cart> findAll(@PathVariable String userid){
        return cartRepository.findAllByUserId(userid);
    }


    //查找功能
    @GetMapping("/findById/{id}")
    public Cart findById(@PathVariable String id){
        return cartRepository.findById(id);
    }

    //加入购物车功能
    @PostMapping("/save")
    public Result save(@RequestBody Cart cart){
        cart.setCart_id("CT"+idWorker.nextId());
        cartRepository.save(cart);
        return new Result(200,"保存成功！",0,"");
    }

    //删除功能
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable String id){
        cartRepository.deleteById(id);
        return new Result(200,"删除成功！",0,"");
    }

    //修改功能
    @PutMapping("/update/{status}")
    public Result update(@RequestBody Cart cart){
        cartRepository.update(cart);
        return new Result(200,"修改成功！",1,"");
    }


}
