package com.wly.repository;
import com.wly.entity.AgroType;

import java.util.List;

public interface AgroTypeRepository {
    public List<AgroType> findAll(int index, int limit);
    public List<AgroType> findByid(String id);
    public int count();
    public void save(AgroType agroType);
    public void update(AgroType agroType);
    public void deleteById(String id);
}
