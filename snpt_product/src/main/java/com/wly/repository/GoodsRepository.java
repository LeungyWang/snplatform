package com.wly.repository;

import com.wly.entity.Goods;

import java.util.List;

public interface GoodsRepository {
    public List<Goods> findAllByUserId(String userid,int index, int limit);
    public List<Goods> findGoodByuserid(String userid);
    public Goods findById(String id);
    public int countByUserid(String userid);
    public void save(Goods goods);
    public void update(Goods goods);
    public void deleteById(String id);
    public int count();
}
