package com.wly.repository;


import com.wly.entity.Admin;

import java.util.List;

public interface AdminRepository {
    public List<Admin> findAll(int index, int limit);
    public Admin findById(String id);
    public Admin findByUsername(String username);
    public int count();
    public void save(Admin admin);
    public void update(Admin admin);
    public void deleteById(String id);
    public Admin login(String username, String password);
}
