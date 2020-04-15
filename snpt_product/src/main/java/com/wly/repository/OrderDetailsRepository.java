package com.wly.repository;

import com.wly.entity.OrderDetails;

import java.util.List;

public interface OrderDetailsRepository {
    //查询所有的订单产品
    public  List<OrderDetails> findAll(int index, int limit);
    //查找用户所有的订单产品 有分页
    public List<OrderDetails> findAllByOrderId(String order_id, int index, int limit);
    //查找用户所有的订单产品 无分页
    public List<OrderDetails> findDetailsByOrderid(String orderid);
    //通过Id查找农产品
    public OrderDetails findById(String id);
    public OrderDetails findDetailById(String id);
    public int countByOrderid(String orderid);
    //新增订单商品
    public void save(OrderDetails details);
    public void update(OrderDetails details);
    //新增订单商品
    public void deleteById(String id);
//
//    public void updateStatus(Status status, String id);
    public int count();
}
