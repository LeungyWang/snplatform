package com.wly.repository;
import com.wly.entity.ProductionType;

import java.util.List;

public interface ProductionTypeRepository {
    public List<ProductionType> findAll(int index, int limit);
    public List<ProductionType> findAllPT();
    public List<ProductionType> findById(String id);
    public int count();


}
