package com.wly.repository;

import com.wly.entity.Catogory;
import java.util.List;

public interface CatogoryRepository {
    //查询所有的产品分类
    public  List<Catogory> findAll(int index, int limit);
    //通过Id查找分类
    public Catogory findById(String id);
    public Catogory findByName(String name);
    //新增分类
    public void save(Catogory catogory);
    //更改分类
    public void update(Catogory catogory);
    //删除分类
    public void deleteById(String id);
    public int count();
}
