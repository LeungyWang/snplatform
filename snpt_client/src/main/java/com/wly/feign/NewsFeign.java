package com.wly.feign;

import com.wly.entity.*;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("news")
public interface NewsFeign {
    /**
     *
     *农业资讯类别
     *
     */

    //查找所有农业资讯类别 后台用
    @GetMapping("/newstype/findAll/{index}/{limit}")
    public Result findAllTypes(@PathVariable int index, @PathVariable int limit);

    //查找所有农业资讯类别 前台用
    @GetMapping("/newstype/findAll")
    public List<NewsType> findTypes();

    //保存农业资讯类别
    @PostMapping("/newstype/save")
    public Result saveType(@RequestBody NewsType newsType);

    //删除农业资讯类别
    @DeleteMapping("/newstype/deleteById/{id}")
    public Result deleteType(@PathVariable("id") int id);


    //修改农业资讯类别
    @PutMapping("/newstype/update")
    public Result updateType(@RequestBody NewsType newsType);

    //查找所有农业资讯 后台用
    @GetMapping("/news/findAll/{index}/{limit}")
    public Result findAllNews(@PathVariable int index, @PathVariable int limit);

    /**
     * 农业资讯发布与管理
     */

    //查找所有农业资讯类别 前台用
    @GetMapping("/news/findAll/{newstypeid}")
    public List<News> findNews(@PathVariable int newstypeid);

    //删除农业资讯
    @DeleteMapping("/news/deleteById/{id}")
    public Result deleteNews(@PathVariable("id") int id);

    //发布爬取的农业资讯
    @PutMapping("/news/releaseNews/{newsid}/{adminid}")
    public Result releaseNews(@PathVariable("newsid") int id,@PathVariable("adminid") String adminid);


    //资讯详情
    @GetMapping("/news/findById/{newsid}")
    public News findById(@PathVariable int newsid);
}
