package com.wly.repository;

import com.wly.entity.Seed;

import java.util.List;

public interface SeedRepository {
    public List<Seed> findAllByUserid(String userid,int index, int limit);
    public Seed findById(String id);
    public int countByUserid(String id);
    public void save(Seed seed);
    public void update(Seed seed);
    public void deleteById(String id);
    public int count();
}
