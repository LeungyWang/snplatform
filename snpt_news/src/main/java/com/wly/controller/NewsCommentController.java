package com.wly.controller;

import com.wly.entity.News;
import com.wly.entity.NewsComment;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/newscomment")
public class NewsCommentController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IdWorker idWorker;

    @PostMapping("/save")
    //保存评论
    public Result saveComment(@RequestBody NewsComment comemnt) {
        comemnt.setReview_time(new Date());
        mongoTemplate.save(comemnt);
        return new Result(200,"评论成功！",1,"");
    }

    //查看所有评论
    @GetMapping("/findAll")
    public List<NewsComment> findAll() {
        return mongoTemplate.findAll(NewsComment.class);
    }

    public NewsComment getCommentById(String commentid) {
        Query query = new Query(Criteria.where("_id").is(commentid));
        return mongoTemplate.findOne(query, NewsComment.class);
    }

    //根据资讯id查询所有评论
    @GetMapping("/findCommentByNewsid/{newsid}")
    public List<NewsComment> getCommentsByNewsId(@PathVariable Integer newsid) {
        Query query = new Query(Criteria.where("newsid").is(newsid));
        return mongoTemplate.find(query, NewsComment.class);
    }

    //根据资讯id查询所有评论条数
    @GetMapping("/getCount/{newsid}")
    public Integer getCountByNewsId(@PathVariable Integer newsid) {
        Query query = new Query(Criteria.where("newsid").is(newsid));
        return mongoTemplate.find(query, NewsComment.class).size();
    }

    //删除评论对象
    public void deleteComment(NewsComment comment) {
        mongoTemplate.remove(comment);
    }

    //根据id删除评论
    @DeleteMapping("/deleteById/{commentid}")
    public Result deleteCommentById(@PathVariable String commentid) {
        // findOne
        NewsComment comment = getCommentById(commentid);
        // delete
        deleteComment(comment);
        return new Result(200,"删除成功！",1,"");
    }

    //



}
