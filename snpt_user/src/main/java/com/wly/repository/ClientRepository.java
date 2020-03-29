package com.wly.repository;


import com.wly.entity.Client;

import java.util.List;

public interface ClientRepository {
    public List<Client> findAll(int index, int limit);
    public Client findByPhone(String phone);
    public int count();
    public void save(Client client);
    public void update(Client client);
    public void deleteById(String id);
}
