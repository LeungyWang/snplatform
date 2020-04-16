package com.wly.repository;
import com.wly.entity.ElementType;

import java.util.List;

public interface ElementTypeRepository {

    public List<ElementType> findAll(int index, int limit);
    public List<ElementType> findByid(int id);
    public int count();
    public void save(ElementType element);
    public void update(ElementType element);
    public void deleteById(String id);

}
