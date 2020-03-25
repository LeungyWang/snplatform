package com.wly.repository;


import com.wly.entity.Role;

import java.util.List;

public interface RoleRepository {
    public List<Role> findAll(int index, int limit);
    public Role findById(String id);
    public List<Role> findRoles(String userid);
    public int count();
    public void save(Role role);
    public void update(Role role);
    public void deleteById(String id);
}
