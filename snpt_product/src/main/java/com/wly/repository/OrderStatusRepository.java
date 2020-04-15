package com.wly.repository;

import com.wly.entity.OrderStatus;

import java.util.List;

public interface OrderStatusRepository {
    //查询所有的状态
    public  List<OrderStatus> findAll(int index, int limit);
    //通过Id查找状态
    public OrderStatus findById(String id);
    //新增状态
    public void save(OrderStatus status);
    //更改状态
    public void update(OrderStatus status);
    //删除状态
    public void deleteById(String id);
    public int count();
}
