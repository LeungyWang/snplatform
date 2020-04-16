package com.wly.repository;
import com.wly.entity.Element;

import java.util.List;

public interface ElementRepository {
    public List<Element> findAll(int index, int limit);
    public List<Element> findElements();
    public List<Element> findByTypeid(int id);
    public int count();
    public void save(Element element);
    public void update(Element element);
    public void deleteById(String id);

}
