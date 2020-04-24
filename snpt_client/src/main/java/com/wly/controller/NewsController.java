package com.wly.controller;

import com.wly.entity.*;
import com.wly.feign.NewsFeign;
import com.wly.feign.SowFeign;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("news")
public class NewsController {
    @Autowired
    private NewsFeign newsFeign;

    //页面跳转
    @GetMapping("/redirect/{localtion}")
    public String redirect(@PathVariable("localtion") String localtion){
        return localtion;
    }

    //查询所有的农业资讯类别
    @GetMapping("/newstype/findAll")
    @ResponseBody
    public Result findAllTypes(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        return newsFeign.findAllTypes(index,limit);
    }

    //增加农业资讯类型
    @PostMapping("/newstype/save")
    @ResponseBody
    public Result saveType(NewsType type) {
        return newsFeign.saveType(type);
    }

    //删除农业资讯类型
    @GetMapping("/newstype/deleteById")
    @ResponseBody
    public Result deleteType(@RequestParam("id") int id) {
        return newsFeign.deleteType(id);
    }

    //修改农业资讯类型
    @PostMapping("/newstype/update")
    @ResponseBody
    public Result updateType(NewsType type) {
        return newsFeign.updateType(type);
    }

    /**
     * 农业资讯
     * @param page
     * @param limit
     * @return
     */

    //查询所有的农业资讯
    @GetMapping("/news/findAll")
    @ResponseBody
    public Result findAllNews(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        return newsFeign.findAllNews(index,limit);
    }

    //删除农业资讯
    @GetMapping("/news/deleteById")
    @ResponseBody
    public Result deleteNews(@RequestParam("id") int id) {
        return newsFeign.deleteNews(id);
    }

    //查询发布农业资讯
    @GetMapping("/news/release")
    @ResponseBody
    public Result findAllNews(@RequestParam("newsid") int newsid,HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        String adminid = admin.getId();
        return newsFeign.releaseNews(newsid,adminid);
    }


    //资讯主页面
    @GetMapping("/{newstypeid}/index")
    public ModelAndView findNewsType(@PathVariable int newstypeid){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("news_index");
        modelAndView.addObject("types",newsFeign.findTypes());
        modelAndView.addObject("news",newsFeign.findNews(newstypeid));
        return modelAndView;
    }

    //资讯详情页
    @GetMapping("/details/{newsid}")
    public ModelAndView findNewsById(@PathVariable int newsid){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("news_details");
        modelAndView.addObject("types",newsFeign.findTypes());
        modelAndView.addObject("news",newsFeign.findById(newsid));
        return modelAndView;
    }

}
