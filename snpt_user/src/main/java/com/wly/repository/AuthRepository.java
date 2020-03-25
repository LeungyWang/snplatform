package com.wly.repository;


import com.wly.entity.Auth;

import java.util.List;

public interface AuthRepository {
    public List<Auth> findAll(int index, int limit);
    public Auth findById(String id);
    public List<Auth> findAuths(String roleid);
    public int count();
    public void save(Auth auth);
    public void update(Auth auth);
    public void deleteById(String id);
}
