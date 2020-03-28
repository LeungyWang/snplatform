package com.wly.repository;


import com.wly.entity.Farmer;

import java.util.List;

public interface FarmerRepository {
    public List<Farmer> findAll(int index, int limit);
    public Farmer findByPhone(String phone);
    public int count();
    public void save(Farmer farmer);
    public void update(Farmer farmer);
    public void deleteById(String id);
}
