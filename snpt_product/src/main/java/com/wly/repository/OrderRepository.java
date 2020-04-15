package com.wly.repository;

import com.wly.entity.Order;
import org.aspectj.weaver.ast.Or;

import java.util.Date;
import java.util.List;

public interface OrderRepository {
    //查询所有的订单
    public  List<Order> findAll(int index, int limit);
    //查找用户所有的订单 有分页
    public List<Order> findOrderByCUserId(String userid, int index, int limit);
    //查询商户的所有订单
    public List<Order> findOrderByBUserid(String userid, int index, int limit);
    //查找用户所有的订单 无分页
    public List<Order> findOrderByuserid(String userid);
    //通过Id查找订单
    public Order findById(String id);
    public Order findOrderById(String id);
    public int countByUserid(String userid);
    //新增订单
    public void save(Order order);
    //订单发货
    public void updateOrderShipping(Date shipping_time,String order_id);
    //订单收货
    public void updateOrderReceive(Date receive_time,String order_id);
    //删除订单
    public void deleteById(String id);
//
//    public void updateStatus(Status status, String id);
    public int count();
}
