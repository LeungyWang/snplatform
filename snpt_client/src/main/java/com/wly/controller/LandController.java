package com.wly.controller;

import com.wly.entity.*;
import com.wly.feign.LandFeign;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTML;
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
    public Result findById(@PathVariable("id") String id){
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
    public Result deleteById(@RequestParam("id") String id){
        return landFeign.deleteById(id);
    }

    @PostMapping("/save")
    @ResponseBody
    public Result save(Soil soil, HttpSession session){
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        return landFeign.save(soil,userid);
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

    @GetMapping("/agrochemical/findAgche")
    @ResponseBody
    public Result findAgche(HttpSession session){
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        return landFeign.findArgo(userid);
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


    //保存土壤质量评估报告
    @GetMapping("/agrochemical/saveEvaluateResult")
    @ResponseBody
    public void saveEvaluateResult(@RequestParam String id){
        List<String> comments = landFeign.getComments(id);
        AgroChemical agroChemical = landFeign.findAgroById(id);
        String comment = "";
        for (int i = 0;i<comments.size();i++){
            comment = comment + (i+1)+"." + comments.get(i)+"\n"+"\n";
        }
        Report report = new Report();
        report.setComment(comment);
        report.setAgroChemical(agroChemical);
        landFeign.saveReport(report);
    }

    //返回ph分析图
    @GetMapping("/agrochemical/findphByid/{id}")
    @ResponseBody
    private EchartsMap getPhCharts(@PathVariable String id){
        EchartsMap echartsMap = new EchartsMap();
        List<Double> value = Arrays.asList(landFeign.findphByid(id));
        echartsMap.setValue(value);
        echartsMap.setName(landFeign.findByTypeid(3));
        return echartsMap;
    }

    //返回常规元素分析图
    @GetMapping("/agrochemical/findmacroByid/{id}")
    @ResponseBody
    private EchartsMap getMacroCharts(@PathVariable String id){
        EchartsMap echartsMap = new EchartsMap();
        echartsMap.setValue(landFeign.findmacroByid(id));
        echartsMap.setName(landFeign.findByTypeid(1));
        return echartsMap;
    }

    //返回微量元素分析图
    @GetMapping("/agrochemical/findmicroByid/{id}")
    @ResponseBody
    private EchartsMap getMicroCharts(@PathVariable String id){
        EchartsMap echartsMap = new EchartsMap();
        echartsMap.setValue(landFeign.findmicroByid(id));
        echartsMap.setName(landFeign.findByTypeid(2));
        return echartsMap;
    }

    //返回数据解读
//    @GetMapping("agrochemical/getComments")
//    public ModelAndView getComments(HttpSession session){
//        long id =(long) session.getAttribute("EvaluateId");
//        ModelAndView modelAndView = new ModelAndView();
//        List<String> comments = landFeign.getComments(id);
//        String comment = "";
//        for (int i = 0;i<comments.size();i++){
//            comment = comment + (i+1)+"." + comments.get(i)+"\n"+"\n";
//        }
//        System.out.println(comment);
//        modelAndView.setViewName("evaluate_result");
//        modelAndView.addObject("comment",comment);
//        return modelAndView;
//    }

    //查询用户生成的土壤报告
    @GetMapping("/report/findAll")
    @ResponseBody
    public Result findAllReports(@RequestParam("page") int page, @RequestParam("limit") int limit, HttpSession session){
        int index = (page-1)*limit;
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        Result reports = landFeign.findAllReport(index,limit,userid);
        return reports;
    }

    //查看土壤报告详情
    @GetMapping("/report/details/{id}")
    public ModelAndView ReportDetails(@PathVariable String id){
        Report report = landFeign.findReportById(id);
        AgroChemical agroChemical = report.getAgroChemical();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("evaluate_result");
        modelAndView.addObject("comment",report.getComment());
        modelAndView.addObject("agroid",agroChemical.getId());
        return modelAndView;
    }

}
