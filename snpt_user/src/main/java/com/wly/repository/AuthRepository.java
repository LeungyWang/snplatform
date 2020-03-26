package com.wly.repository;


import com.wly.entity.Auth;

import java.util.List;

public interface AuthRepository {
    public List<Auth> findAll();
    public Auth findById(int id);
    public List<Auth> findAuths(String roleid);
    public List<Integer> Authids();
    public int count();
    public void save(Auth auth);
    public void update(Auth auth);
    public void deleteById(int id);
}
