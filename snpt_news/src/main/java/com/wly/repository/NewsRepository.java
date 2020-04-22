package com.wly.repository;
import com.wly.entity.NewsType;

import java.util.List;

public interface NewsTypeRepository {
    public List<NewsType> findAll(int index, int limit);
    public List<NewsType> findNewsType();
    public NewsType findById(int newstypeid);
    public int count();
    public void save(NewsType newsType);
    public void update(NewsType newsType);
    public void deleteById(int id);
}
