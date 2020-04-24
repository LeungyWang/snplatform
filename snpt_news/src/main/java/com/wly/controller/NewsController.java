package com.wly.controller;

import com.wly.entity.News;
import com.wly.entity.NewsType;
import com.wly.repository.NewsRepository;
import com.wly.repository.NewsTypeRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    public NewsRepository newsRepository;


    //查找所有的资讯 有分页
    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable int index, @PathVariable int limit){
        return new Result(0,"",newsRepository.count(),newsRepository.findAll(index,limit));
    }

    //查找所有的资讯 无分页
    @GetMapping("/findAll/{newstypeid}")
    public List<News> findAll(@PathVariable int newstypeid){
        return newsRepository.findNews(newstypeid);
    }

    //根据Id查找农业资讯 资讯详情页
    @GetMapping("/findById/{newsid}")
    public News findById(@PathVariable int newsid){
        return newsRepository.findById(newsid);
    }

    //发布资讯
    @PutMapping("/releaseNews/{newsid}/{adminid}")
    public Result updateStatus(@PathVariable int newsid,@PathVariable String adminid){
        newsRepository.updateStatus(newsid,adminid);
        return new Result(200,"资讯发布成功！",1,"");
    }

    //删除资讯
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") Integer newsid){
        newsRepository.deleteById(newsid);
        return new Result(200,"资讯删除成功！",1,"");
    }


}
