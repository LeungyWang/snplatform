package com.wly.repository;
import com.wly.entity.Report;

import java.util.List;

public interface ReportRepository {
    public List<Report> findAll(int index, int limit);
    public List<Report> findAllByUserId(String userid, int index, int limit);
    public Report findById(String id);
    public int countByUserid(String userid);
    public void save(Report report);
    public void update(Report report);
    public void deleteById(String id);
    public int count();
}
