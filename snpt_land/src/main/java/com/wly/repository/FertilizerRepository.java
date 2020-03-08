package com.wly.repository;

import com.wly.entity.Fertilizer;

import java.util.List;

public interface FertilizerRepository {
    public List<Fertilizer> findAllByUserid(String userid, int index, int limit);
    public Fertilizer findById(String id);
    public int countByUserid(String userid);
    public void save(Fertilizer fertilizer);
    public void update(Fertilizer fertilizer);
    public void deleteById(String id);
    public int count();
}
