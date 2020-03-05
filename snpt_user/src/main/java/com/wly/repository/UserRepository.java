package com.wly.repository;


import com.wly.entity.User;

import java.util.List;

public interface UserRepository {
    public List<User> findAll(int index, int limit);
    public User findById(long id);
    public User findByUsername(String username);
    public int count();
    public void save(User user);
    public void update(User user);
    public void deleteById(long id);
    public User login(String username, String password);
}
