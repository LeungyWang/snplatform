package com.wly.repository;

import com.wly.entity.Order;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderRepository {
    //查询所有的订单
    public  List<Order> findAll(int index, int limit);
    //查找用户所有的农产品 有分页
    public List<Order> findAllByUserid(String userid, int index, int limit);
    //查找用户所有的农产品 无分页
    public List<Order> findOrderByuserid(String userid);
    //通过Id查找农产品
    public Order findById(String id);
    public Order findOrderById(String id);
    public int countByUserid(String userid);
    //新增农产品
    public void save(Order order);
    public void update(Order order);
    //删除农产品
    public void deleteById(String id);
//
//    public void updateStatus(Status status, String id);
    public int count();
}
