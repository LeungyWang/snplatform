package com.wly.repository;
import com.wly.entity.FertilityStandard;

import java.util.List;

public interface FertilityStandardRepository {
    public List<FertilityStandard> findAll(int index, int limit);
    public int count();
    public void save(FertilityStandard standard);
    public void update(FertilityStandard standard);
    public void deleteById(int id);

}
