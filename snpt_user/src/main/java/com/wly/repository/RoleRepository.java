package com.wly.repository;


import com.wly.entity.Role;

import java.util.List;

public interface RoleRepository {
    public List<Role> findAll(int index, int limit);
    public Role findById(String id);
    public List<Role> findRoles(String userid);
    //得到权限的值
    public Boolean findChecked(String roleid,int authid);
    //分配权限
    public void updateChecked(String roleid,int authid);
    //取消权限
    public void updateCheck(String roleid,int authid);
    public int count();
    public void save(Role role);
    public void update(Role role);
    public void deleteById(String id);
}
