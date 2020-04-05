package com.wly.repository;

import com.wly.entity.Cart;
import com.wly.entity.Status;

import java.util.List;

public interface CartRepository {
    //查询所有购物车商品
    public  List<Cart> findAll(int index, int limit);
    //查找用户的购物车商品
    public List<Cart> findAllByUserId(String userid);
    //查询购物车某件商品
    public Cart findById(String id);
    public int countByUserid(String userid);
    //新增购物车商品
    public void save(Cart goods);
    //更新购物车商品
    public void update(Cart goods);
    //删除购物车商品
    public void deleteById(String id);
    //统计个数
    public int count();
}
