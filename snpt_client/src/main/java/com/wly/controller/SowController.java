package com.wly.controller;

import com.wly.entity.*;
import com.wly.feign.LandFeign;
import com.wly.feign.SowFeign;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("sow")
public class SowController {
    @Autowired
    private SowFeign sowFeign;



    //查询用户所有种子功能实现
    @GetMapping("/seed/findAllSeed")
    @ResponseBody
    public Result findAllSeed(@RequestParam("page") int page, @RequestParam("limit") int limit,HttpSession session){
        int index = (page-1)*limit;
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        Result land = sowFeign.findAllSeed(index,limit,userid);
        return land;
//        return menuFeign.findAll(index,limit);
    }


    //根据种子的ID查询种子信息功能
    @GetMapping("/seed/findById/{id}")
    @ResponseBody
    public Result findSeedById(@PathVariable("id") String id){
        return sowFeign.findSeedById(id);
//        return menuFeign.findAll(index,limit);
    }


    @GetMapping("/redirect/{localtion}")
    public String redirect(@PathVariable("localtion") String localtion){
        return localtion;
    }


    //实现种子信息的修改功能
    @PostMapping("/seed/update")
    @ResponseBody
    public Result updateSeed(Seed seed){
        return sowFeign.updateSeed(seed);
    }

    //实现种子信息的删除功能
    @GetMapping("/seed/deleteById")
    @ResponseBody
    public Result deleteSeedById(@RequestParam("id") String id){
        return sowFeign.deleteSeedById(id);
    }

    //实现种子信息的增加功能
    @PostMapping("/seed/save")
    @ResponseBody
    public Result saveSeed(Seed seed,HttpSession session){
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        return sowFeign.saveSeed(seed,userid);
    }



    //查询用户所有化肥功能实现
    @GetMapping("/fertilizer/findAllFer")
    @ResponseBody
    public Result findAllFert(@RequestParam("page") int page, @RequestParam("limit") int limit,HttpSession session){
        int index = (page-1)*limit;
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        Result fertilizer = sowFeign.findAllFertilizer(index,limit,userid);
        return fertilizer;
//        return menuFeign.findAll(index,limit);
    }


    //根据化肥的ID查询种子信息功能
    @GetMapping("/fertilizer/findById/{id}")
    @ResponseBody
    public Result findFerById(@PathVariable("id") String id){
        return sowFeign.findFertilizerById(id);
//        return menuFeign.findAll(index,limit);
    }


    //实现化肥信息的修改功能
    @PostMapping("/fertilizer/update")
    @ResponseBody
    public Result updateFer(Fertilizer fertilizer){
        return sowFeign.updateFertilize(fertilizer);
    }

    //实现化肥信息的删除功能
    @GetMapping("/fertilizer/deleteById")
    @ResponseBody
    public Result deleteFerById(@RequestParam("id") String id){
        return sowFeign.deleteFertilizerById(id);
    }

    //实现化肥信息的增加功能
    @PostMapping("/fertilizer/save")
    @ResponseBody
    public Result saveFer(Fertilizer fertilizer,HttpSession session){
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        return sowFeign.saveFertilizer(fertilizer,userid);
    }


    //查询用户所有农药功能实现
    @GetMapping("/pesticide/findAllPest")
    @ResponseBody
    public Result findAllPest(@RequestParam("page") int page, @RequestParam("limit") int limit,HttpSession session){
        int index = (page-1)*limit;
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        Result pesticide = sowFeign.findAllPesticide(index,limit,userid);
        return pesticide;
//        return menuFeign.findAll(index,limit);
    }


    //根据农药的ID查询种子信息功能
    @GetMapping("/pesticide/findById/{id}")
    @ResponseBody
    public Result findPestById(@PathVariable("id") String id){
        return sowFeign.findPesticideById(id);
//        return menuFeign.findAll(index,limit);
    }


    //实现农药信息的修改功能
    @PostMapping("/pesticide/update")
    @ResponseBody
    public Result updatePest(Pesticide pesticide){
        return sowFeign.updatePesticide(pesticide);
    }

    //实现农药信息的删除功能
    @GetMapping("/pesticide/deleteById")
    @ResponseBody
    public Result deletePestById(@RequestParam("id") String id){
        return sowFeign.deletePesticideById(id);
    }

    //实现农药信息的增加功能
    @PostMapping("/pesticide/save")
    @ResponseBody
    public Result savePest(Pesticide pesticide,HttpSession session){
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        return sowFeign.savePesticide(pesticide,userid);
    }




}
