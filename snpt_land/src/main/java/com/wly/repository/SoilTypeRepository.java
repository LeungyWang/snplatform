package com.wly.repository;
import com.wly.entity.SoilType;

import java.util.List;

public interface SoilTypeRepository {
    public List<SoilType> findAll(int index, int limit);
    public List<SoilType> findAllTypes();
    public SoilType findById(long id);
    public int countByUserid(long id);
    public void save(SoilType soil);
    public void update(SoilType soil);
    public void deleteById(long id);
    public int count();
}
