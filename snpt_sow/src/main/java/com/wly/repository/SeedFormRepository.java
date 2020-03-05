package com.wly.repository;

import com.wly.entity.SeedForm;

import java.util.List;

public interface SeedFormRepository {
    public List<SeedForm> findAll(int index, int limit);
    public SeedForm findById(long id);
    public int countById(long id);
    public void save(SeedForm seedForm);
    public void update(SeedForm seedForm);
    public void deleteById(SeedForm seedForm);
    public int count();
}
