package com.wly.repository;

import com.wly.entity.Seed;

import java.util.List;

public interface SeedRepository {
    public List<Seed> findAll(int index, int limit);
    public Seed findById(long id);
    public int countById(long id);
    public void save(Seed seed);
    public void update(Seed seed);
    public void deleteById(Seed seed);
    public int count();
}
