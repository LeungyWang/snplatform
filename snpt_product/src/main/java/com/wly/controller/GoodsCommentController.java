package com.wly.controller;

import com.wly.entity.GoodsComment;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/goodscomment")
public class GoodsCommentController {

    @Autowired
    private MongoTemplate mongoTemplate;


    @PostMapping("/save")
    //保存评论
    public Result saveComment(@RequestBody GoodsComment comemnt) {
        comemnt.setReview_time(new Date());
        mongoTemplate.save(comemnt);
        return new Result(200,"评论成功！",1,"");
    }

    //查看所有评论
    @GetMapping("/findAll")
    public List<GoodsComment> findAll() {
        return mongoTemplate.findAll(GoodsComment.class);
    }

    public GoodsComment getCommentById(String commentid) {
        Query query = new Query(Criteria.where("_id").is(commentid));
        return mongoTemplate.findOne(query, GoodsComment.class);
    }

    //根据资讯id查询所有评论
    @GetMapping("/findCommentByPruductId/{productid}")
    public List<GoodsComment> getCommentsByNewsId(@PathVariable String productid) {
        Query query = new Query(Criteria.where("productid").is(productid));
        return mongoTemplate.find(query, GoodsComment.class);
    }

    //根据资讯id查询所有评论条数
    @GetMapping("/getCount/{productid}")
    public Integer getCountByNewsId(@PathVariable String productid) {
        Query query = new Query(Criteria.where("productid").is(productid));
        return mongoTemplate.find(query, GoodsComment.class).size();
    }

    //删除评论对象
    public void deleteComment(GoodsComment comment) {
        mongoTemplate.remove(comment);
    }

    //根据id删除评论
    @DeleteMapping("/deleteById/{commentid}")
    public Result deleteCommentById(@PathVariable String commentid) {
        // findOne
        GoodsComment goodsComment = getCommentById(commentid);
        // delete
        deleteComment(goodsComment);
        return new Result(200,"删除成功！",1,"");
    }

    //



}
