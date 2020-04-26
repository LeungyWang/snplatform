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
import java.util.ArrayList;
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
//        土壤元素评价
        Report report = landFeign.findReportById(id);
        AgroChemical agroChemical = report.getAgroChemical();
//        土地肥力分级
        Double nitrogen = agroChemical.getNitrogen();
        Double phosphorus  = agroChemical.getPhosphorus();
        Double potassium  = agroChemical.getPotassium();
        Integer level = landFeign.PythonPredcit(nitrogen,phosphorus,potassium);
        FertilityStandard fertilityStandard = landFeign.findByLevel(level);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("evaluate_result");
        modelAndView.addObject("comment",report.getComment());
        modelAndView.addObject("agroid",agroChemical.getId());
        modelAndView.addObject("classify",fertilityStandard);
        return modelAndView;
    }

    //删除土壤评估报告
    @GetMapping("/report/deleteById")
    @ResponseBody
    public Result deleteRepById(@RequestParam("id") String id){
        return landFeign.deleteReportById(id);
    }



    /**
     * 管理员土壤参数设置功能
     */

    //查找元素信息
    @GetMapping("/element/findAll")
    @ResponseBody
    public Result findEleAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        return landFeign.findAllElement(index,limit);
    }

    //查找元素信息
    @GetMapping("/element/findElements")
    @ResponseBody
    public Result findElements(){
        return landFeign.findElements();
    }

    //增加元素信息
    @PostMapping("/element/save")
    @ResponseBody
    public Result saveElement(Element element){
        return landFeign.saveElement(element);
    }

    //删除元素信息
    @GetMapping("/element/deleteById")
    @ResponseBody
    public Result deleteEleById(@RequestParam("id") String id){
        return landFeign.deleteElementById(id);
    }

    //修改元素信息
    @PostMapping("/element/update")
    @ResponseBody
    public Result updateElement(Element element){
        return landFeign.updateElement(element);
    }

    //查找分级标准信息
    @GetMapping("/standard/findAll")
    @ResponseBody
    public Result findStdAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        return landFeign.findAllStandard(index,limit);
    }

    //增加分级标准信息
    @PostMapping("/standard/save")
    @ResponseBody
    public Result saveStandard(ElementStandard standard){
        return landFeign.saveStandard(standard);
    }

    //删除分级标准信息
    @GetMapping("/standard/deleteById")
    @ResponseBody
    public Result deleteStdById(@RequestParam("id") String id){
        return landFeign.deleteStandardById(id);
    }

    //修改分级标准信息
    @ResponseBody
    public Result updateStd(ElementStandard standard){
        return landFeign.updateStandard(standard);
    }

    //查找土壤肥力信息
    @GetMapping("/fstandard/findAll")
    @ResponseBody
    public Result findFStdAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        return landFeign.findAllFStandard(index,limit);
    }

    //增加土壤肥力分级信息
    @PostMapping("/fstandard/save")
    @ResponseBody
    public Result saveFStandard(FertilityStandard standard){
        return landFeign.saveFStandard(standard);
    }

    //删除土壤肥力分级信息
    @GetMapping("/fstandard/deleteById")
    @ResponseBody
    public Result deleteFStdById(@RequestParam("id") int id){
        return landFeign.deleteFStandardById(id);
    }

    //修改土壤肥力分级信息
    @PostMapping("/fstandard/update")
    @ResponseBody
    public Result updateFStd(FertilityStandard standard){
        return landFeign.updateFStandard(standard);
    }

    /**
     * 土地农事统计图
     */

    //返回土地月施肥记录
    @GetMapping("/soil/frecordcharts")
    @ResponseBody
    private EchartsMap getSoilRecordCharts(HttpSession session){
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        EchartsMap echartsMap = new EchartsMap();
        List<String> names = new ArrayList<String>();
        List<Double> value = new ArrayList<Double>();
        List<Soil> soils = landFeign.findNameByuserid(userid);
        for (int i=0;i<soils.size();i++){
            Soil soil = soils.get(i);
            names.add(soil.getName());
            String soilid = soil.getId();
            Double v = Double.valueOf(landFeign.countSoilRecord(soilid,"3"));
            value.add(v);
        }
        echartsMap.setValue(value);
        echartsMap.setName(names);
        return echartsMap;
    }

    //返回土地月施药记录图
    @GetMapping("/soil/precordcharts")
    @ResponseBody
    private EchartsMap getSoilPRecordCharts(HttpSession session){
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        EchartsMap echartsMap = new EchartsMap();
        List<String> names = new ArrayList<String>();
        List<Double> value = new ArrayList<Double>();
        List<Soil> soils = landFeign.findNameByuserid(userid);
        for (int i=0;i<soils.size();i++){
            Soil soil = soils.get(i);
            names.add(soil.getName());
            String soilid = soil.getId();
            Double v = Double.valueOf(landFeign.countSoilRecord(soilid,"7"));
            value.add(v);
        }
        echartsMap.setValue(value);
        echartsMap.setName(names);
        return echartsMap;
    }

    //返回土地月播种记录图
    @GetMapping("/soil/srecordcharts")
    @ResponseBody
    private EchartsMap getSoilSRecordCharts(HttpSession session){
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        EchartsMap echartsMap = new EchartsMap();
        List<String> names = new ArrayList<String>();
        List<Double> value = new ArrayList<Double>();
        List<Soil> soils = landFeign.findNameByuserid(userid);
        for (int i=0;i<soils.size();i++){
            Soil soil = soils.get(i);
            names.add(soil.getName());
            String soilid = soil.getId();
            Double v = Double.valueOf(landFeign.countSoilRecord(soilid,"2"));
            value.add(v);
        }
        echartsMap.setValue(value);
        echartsMap.setName(names);
        return echartsMap;
    }

    /**
     * 土地肥力分级
     */


}
