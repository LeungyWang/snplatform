package com.wly.repository;

import com.wly.entity.Harvest;

import java.util.List;

public interface HarvestRepository {
    public List<Harvest> findAll(int index, int limit);
    public Harvest findById(long id);
    public int countById(long id);
    public void save(Harvest harvest);
    public void update(Harvest harvest);
    public void deleteById(Harvest harvest);
    public int count();
}
