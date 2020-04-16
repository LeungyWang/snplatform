package com.wly.repository;

import com.wly.entity.SowRecord;

import java.util.List;

public interface SowRecordRepository {
    public List<SowRecord> findAllByUserid(String userid, int index, int limit);
    public List<SowRecord> findAll(String userid);
    public SowRecord findById(String id);
    public int countByUserid(String id);
    public int countSoilRecord(String landid,String farmworkid);
    public void save(SowRecord sowRecord);
    public void update(SowRecord sowRecord);
    public void deleteById(String id);
    public int count();
}
