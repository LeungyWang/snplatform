package com.wly.controller;

import com.wly.entity.*;
import com.wly.feign.LandFeign;
import com.wly.feign.SowFeign;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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

    //查询用户所有种子功能实现 无分页
    @GetMapping("/seed/findAllSeeds")
    @ResponseBody
    public Result findAllSeed(HttpSession session){
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        Result seed = sowFeign.findAllSeed(userid);
        return seed;
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




    //查询用户所有农事记录功能实现
    @GetMapping("/sowrecord/findAllRecord")
    @ResponseBody
    public Result findAllRecord(HttpSession session) {
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        Result record = sowFeign.findAllRecord(userid);
        return record;
    }

    //查询所有的农事类型
    @GetMapping("/farmwork/findAll")
    @ResponseBody
    public Result findAllfarmwork(){

        return sowFeign.findAllfarmwork();
    }

    //实现农事记录的增加功能
    @PostMapping("/sowrecord/save")
    @ResponseBody
    public Result save(@RequestParam("landid") String landid, @RequestParam("fertilizerid") String fertilizerid,@RequestParam("farmworkid") String farmworkid,@RequestParam("dateRange") String dateRange,@RequestParam("timeRange") String timeRange,@RequestParam("content") String content,HttpSession session) throws ParseException {

        SowRecord sowRecord = new SowRecord();
        Fertilizer fertilizer  = new Fertilizer();
        if (fertilizerid.equals("-1")){
            fertilizer.setName("无");
        }
        Soil soil  = new Soil();
        FarmWork farmWork = new FarmWork();
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        String[] dates = dateRange.split(" - ");
        String[] times = timeRange.split("-");
        //格式化时间
        String starttime = times[0].trim();
        String endtime = times[1].trim();
        //格式化日期
        String startDate = dates[0].trim();
        Date startdate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        String endDate = dates[1].trim();
        Date enddate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        //传值
        fertilizer.setId(fertilizerid);
        soil.setId(landid);
        farmWork.setId(farmworkid);
        sowRecord.setUserid(userid);
        sowRecord.setContent(content);
        sowRecord.setFarmWork(farmWork);
        sowRecord.setFertilizer(fertilizer);
        sowRecord.setSoil(soil);
        sowRecord.setStartdate(startdate);
        sowRecord.setEnddate(enddate);
        sowRecord.setStarttime(starttime);
        sowRecord.setEndtime(endtime);
        return sowFeign.saveSowrecord(sowRecord,userid);
    }

    //查询所有的农资类型
    @GetMapping("/productiontype/findAll")
    @ResponseBody
    public Result findAllPT(){
        return sowFeign.findAllPT();
    }

    //查询所有的农资
    @GetMapping("/fertilizer/findAllFertilizer")
    @ResponseBody
    public Result findAllFertilizer(HttpSession session) {
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        Result fertilizers = sowFeign.findAllFertilizer(userid);
        return fertilizers;
    }


    //查找农资类型
    @GetMapping("/agrotype/findAll")
    @ResponseBody
    public Result findTypes(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        return sowFeign.findAllTypes(index,limit);
    }

    //增加农资类型
    @PostMapping("/agrotype/save")
    @ResponseBody
    public Result saveType(AgroType type){
        return sowFeign.saveType(type);
    }

    //删除农资类型
    @GetMapping("/agrotype/deleteById")
    @ResponseBody
    public Result deleteType(@RequestParam("id") String id){
        return sowFeign.deleteType(id);
    }

    //修改农资类型
    @PostMapping("/agrotype/update")
    @ResponseBody
    public Result updateType(AgroType type){
        return sowFeign.updateType(type);
    }


    //查找农事类型
    @GetMapping("/farmwork/findFarmworks")
    @ResponseBody
    public Result findFarmwork(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        return sowFeign.findAllFarmwork(index,limit);
    }

    //增加农事类型
    @PostMapping("/farmwork/save")
    @ResponseBody
    public Result saveFarmwork(FarmWork farmWork){
        return sowFeign.saveFarmwork(farmWork);
    }

    //删除农事类型
    @GetMapping("/farmwork/deleteById")
    @ResponseBody
    public Result deleteFarmwork(@RequestParam("id") String id){
        return sowFeign.deleteFarmwork(id);
    }

    //修改农事类型
    @PostMapping("/farmwork/update")
    @ResponseBody
    public Result updateFarmwork(FarmWork farmWork){
        return sowFeign.updateFarmwork(farmWork);
    }

}
