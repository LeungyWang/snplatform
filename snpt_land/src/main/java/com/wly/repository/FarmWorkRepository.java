package com.wly.repository;
import com.wly.entity.FarmWork;

import java.util.List;

public interface FarmWorkRepository {

    public List<FarmWork> findAll();
    public List<FarmWork> findAllWorks(int index, int limit);
    public List<FarmWork> findByid(String id);
    public int count();
    public void save(FarmWork farmWork);
    public void update(FarmWork farmWork);
    public void deleteById(String id);
}
