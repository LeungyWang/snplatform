package com.wly.repository;
import com.wly.entity.Soil;

import java.util.List;

public interface SoilRepository {
    public List<Soil> findAll(int index, int limit);
    public List<Soil> findAllByUserId(String userid,int index, int limit);
    public List<Soil> findSoilByuserid(String userid);
    public Soil findById(String id);
    public int countByUserid(String userid);
    public void save(Soil soil);
    public void update(Soil soil);
    public void deleteById(String id);
    public int count();
}
