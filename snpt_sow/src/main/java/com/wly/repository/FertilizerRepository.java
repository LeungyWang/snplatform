package com.wly.repository;

import com.wly.entity.Fertilizer;

import java.util.List;

public interface FertilizerRepository {
    public List<Fertilizer> findAll(int index, int limit);
    public Fertilizer findById(long id);
    public int countById(long id);
    public void save(Fertilizer fertilizer);
    public void update(Fertilizer fertilizer);
    public void deleteById(Fertilizer fertilizer);
    public int count();
}
