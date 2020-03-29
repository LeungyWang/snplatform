package com.wly.repository;


import com.wly.entity.Businesses;

import java.util.List;

public interface BusinessesRepository {
    public List<Businesses> findAll(int index, int limit);
    public Businesses findByPhone(String phone);
    public int count();
    public void save(Businesses businesses);
    public void update(Businesses businesses);
    public void deleteById(String id);
}
