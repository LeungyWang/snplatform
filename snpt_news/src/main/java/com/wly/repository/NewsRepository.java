package com.wly.repository;
import com.wly.entity.News;

import java.util.List;

public interface NewsRepository {
    public List<News> findAll(int index, int limit);
    //根据板块查找资讯内容
    public List<News> findNews(int newstypeid);
    public List<News> findHotNews(int newstypeid);
    //更新访问量
    public void updateHits(int newsid);
    public News findById(int newsid);
    public int count();
    public void updateStatus(int newsid,String adminid);
    public void deleteById(int id);
}
