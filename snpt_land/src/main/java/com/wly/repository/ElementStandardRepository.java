package com.wly.repository;
import com.wly.entity.ElementStandard;

import java.util.List;

public interface ElementStandardRepository {
    public List<ElementStandard> findAll(int index, int limit);
    public List<ElementStandard> findByid(String id);
    public int count();
    public void save(ElementStandard elementStandard);
    public void update(ElementStandard elementStandard);
    public void deleteById(String id);
}
