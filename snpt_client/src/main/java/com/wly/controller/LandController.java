package com.wly.controller;

import com.wly.entity.*;
import com.wly.feign.LandFeign;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("land")
public class LandController {
    @Autowired
    private LandFeign landFeign;


    @GetMapping("/soil/findAll")
    @ResponseBody
    public Result findAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        Result land = landFeign.findAll(index,limit);
        return land;
//        return menuFeign.findAll(index,limit);
    }

    @GetMapping("/soil/findByUserId")
    @ResponseBody
    public Result findByUserId(@RequestParam("page") int page, @RequestParam("limit") int limit,HttpSession session){
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        int index = (page-1)*limit;
        return landFeign.findByUserId(index,limit,userid);
    }



    @GetMapping("/soil/findSoilByUserid")
    @ResponseBody
    public Result findSoilByUserid(HttpSession session){
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        return landFeign.findSoilByuserid(userid);
    }



    @GetMapping("/soil/findSoil")
    @ResponseBody
    public Result findSoil(){
        return landFeign.findSoil();
//        return menuFeign.findAll(index,limit);
    }

    @GetMapping("/redirect/{localtion}")
    public String redirect(@PathVariable("localtion") String localtion){
        return localtion;
    }

    @GetMapping("/redirect/console/{localtion}")
    public String console(@PathVariable("localtion") String localtion){
        return "page/console/"+localtion;
    }

    @GetMapping("/findById/{id}")
    @ResponseBody
    public Result findById(@PathVariable("id") long id){
        return landFeign.findById(id);
//        return menuFeign.findAll(index,limit);
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(Soil soil){
        return landFeign.update(soil);
    }

    @GetMapping("/deleteById")
    @ResponseBody
    public Result deleteById(@RequestParam("id") long id){
        return landFeign.deleteById(id);
    }

    @PostMapping("/save")
    @ResponseBody
    public Result save(Soil soil){
        return landFeign.save(soil);
    }


    @GetMapping("/soiltype/findAllTypes")
    @ResponseBody
    public Result findAllTypes(){
        return landFeign.findAllTypes();
    }

    @GetMapping("/agrochemical/findAll")
    @ResponseBody
    public Result findAgcheAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        return landFeign.findAgchedAll(index,limit);
    }

    @GetMapping("/agrochemical/findAgcheAll")
    @ResponseBody
    public Result findAgcheByuserid(@RequestParam("page") int page, @RequestParam("limit") int limit,HttpSession session){
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        int index = (page-1)*limit;
        return landFeign.findAgcheAll(index,limit,userid);
    }

    @PostMapping("/agrochemical/save")
    @ResponseBody
    public Result savaAgrochemical(AgroChemical agroChemical){
        return landFeign.saveAgrochemical(agroChemical);
    }

    @PostMapping("/agrochemical/update")
    @ResponseBody
    public Result agrochemicalupdate(AgroChemical agroChemical){
        return landFeign.updateAgrochemical(agroChemical);
    }


    @GetMapping("/agrochemical/saveEvaluateid")
    @ResponseBody
    public void saveEvaluateid(@RequestParam long landid,HttpSession session){
        session.setAttribute("EvaluateId", landid);
    }

    //返回ph分析图
    @GetMapping("/agrochemical/findLatestphByid")
    @ResponseBody
    private EchartsMap getPhCharts(HttpSession session){
        long id =(long) session.getAttribute("EvaluateId");
        EchartsMap echartsMap = new EchartsMap();
        List<Double> value = Arrays.asList(landFeign.findLatestphByid(id));
        echartsMap.setValue(value);
        echartsMap.setName(landFeign.findByTypeid(3));
        return echartsMap;
    }

    //返回常规元素分析图
    @GetMapping("/agrochemical/findLatestmacroByid")
    @ResponseBody
    private EchartsMap getMacroCharts(HttpSession session){
        long id =(long) session.getAttribute("EvaluateId");
        EchartsMap echartsMap = new EchartsMap();
        echartsMap.setValue(landFeign.findLatestmacroByid(id));
        echartsMap.setName(landFeign.findByTypeid(1));
        return echartsMap;
    }

    //返回微量元素分析图
    @GetMapping("/agrochemical/findLatestmicroByid")
    @ResponseBody
    private EchartsMap getMicroCharts(HttpSession session){
        long id =(long) session.getAttribute("EvaluateId");
        EchartsMap echartsMap = new EchartsMap();
        echartsMap.setValue(landFeign.findLatestmicroByid(id));
        echartsMap.setName(landFeign.findByTypeid(2));
        return echartsMap;
    }



}
