package com.wly.repository;

import com.wly.entity.Catogory;
import com.wly.entity.Goods;
import com.wly.entity.Status;

import java.util.List;

public interface GoodsRepository {
    //查询所有的农产品
    public  List<Goods> findAll(int index, int limit);
    //查找用户所有的农产品 有分页
    public List<Goods> findAllByUserId(String userid,int index, int limit);
    //查找用户所有的农产品 无分页
    public List<Goods> findGoodByuserid(String userid);
    //展示蔬菜类农产品到商城中
    public List<Goods> findAllByCategory(Catogory ct);
    //通过Id查找农产品
    public Goods findById(String id);
    public Goods findCartById(String id);
    public int countByUserid(String userid);
    //新增农产品
    public void save(Goods goods);
    public void update(Goods goods);
    //删除农产品
    public void deleteById(String id);
    //申请审核农产品
    public void updateStatus(Status status, String id);
    public int count();
}
