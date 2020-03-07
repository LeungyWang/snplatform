package com.wly.repository;

import com.wly.entity.Pesticide;

import java.util.List;

public interface PesticideRepository {
    public List<Pesticide> findAllByUserid(String userid, int index, int limit);
    public Pesticide findById(String id);
    public int countByUserid(String userid);
    public void save(Pesticide pesticide);
    public void update(Pesticide pesticide);
    public void deleteById(String id);
    public int count();
}
