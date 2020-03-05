package com.wly.repository;

import com.wly.entity.SeedRecord;

import java.util.List;

public interface SeedRecordRepository {
    public List<SeedRecord> findAll(int index, int limit);
    public SeedRecord findById(long id);
    public int countById(long id);
    public void save(SeedRecord seedRecord);
    public void update(SeedRecord seedRecord);
    public void deleteById(SeedRecord seedRecord);
    public int count();
}
