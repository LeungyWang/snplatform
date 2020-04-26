package com.wly.feign;

import com.wly.entity.*;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@FeignClient("land")
public interface LandFeign {
    @GetMapping("/soil/findAll/{index}/{limit}")
    public Result findAll(@PathVariable("index") int index, @PathVariable("limit") int limit);


    @GetMapping("/soil/findSoil")
    public Result findSoil();

    @GetMapping("/soil/findAllByUserId/{index}/{limit}/{userid}")
    public Result findByUserId(@PathVariable("index") int index, @PathVariable("limit") int limit,@PathVariable String userid);


    @GetMapping("/soil/findSoilByuserid/{userid}")
    public Result findSoilByuserid(@PathVariable String userid);

    @GetMapping("/soil/findById/{id}")
    public Result findById(@PathVariable("id") String id);


    @PutMapping("/soil/update")
    public Result update(@RequestBody Soil soil);

    @DeleteMapping("/soil/deleteById")
    public Result deleteById(@RequestParam("id") String id);

    @PostMapping("/soil/save/{userid}")
    public Result save(@RequestBody Soil soil,@PathVariable String userid);

    @GetMapping("/soiltype/findAllTypes")
    public Result findAllTypes();

    @GetMapping("/agrochemical/findAll/{index}/{limit}")
    public Result findAgchedAll(@PathVariable("index") int index, @PathVariable("limit") int limit);

    @GetMapping("/agrochemical/findById/{id}")
    public AgroChemical findAgroById(@PathVariable("id") String id);

    @GetMapping("/agrochemical/findAllByuserid/{index}/{limit}/{userid}")
    public Result findAgcheAll(@PathVariable int index, @PathVariable int limit, @PathVariable String userid);

    @PostMapping("/agrochemical/save")
    public Result saveAgrochemical(AgroChemical agroChemical);

    @PutMapping("/agrochemical/update")
    public Result updateAgrochemical(@RequestBody AgroChemical agroChemical);

    @GetMapping("/agrochemical/findArgoByuserid/{userid}")
    public Result findArgo(@PathVariable String userid);

    @GetMapping("/agrochemical/geteCharts")
    public EchartsMap geteCharts();

    @GetMapping("/agrochemical/findphByid/{id}")
    public double findphByid(@PathVariable String id);

    @GetMapping("/agrochemical/findmacroByid/{id}")
    public List<Double> findmacroByid(@PathVariable String id);

    @GetMapping("/agrochemical/findmicroByid/{id}")
    public List<Double> findmicroByid(@PathVariable String id);

    @GetMapping("/element/findByTypeid/{id}")
    public List<String> findByTypeid(@PathVariable int id);

    @GetMapping("agrochemical/getComments/{id}")
    public List<String> getComments(@PathVariable String id);

    @PostMapping("/report/save")
    public Result saveReport(@RequestBody Report report);

    @GetMapping("/report/findById/{id}")
    public Report findReportById(@PathVariable String id);

    @GetMapping("/report/findAllByUserId/{index}/{limit}/{userid}")
    public Result findAllReport(@PathVariable int index, @PathVariable int limit, @PathVariable String userid);

    @DeleteMapping("/report/deleteById")
    public Result deleteReportById(@RequestParam("id") String id);


    /**
     * 土壤参数设置功能
     */

    //查找所有土壤元素
    @GetMapping("/element/findAll/{index}/{limit}")
    public Result findAllElement(@PathVariable int index, @PathVariable int limit);

    @GetMapping("/element/findAll")
    public Result findElements();

    //保存土壤元素信息
    @PostMapping("/element/save")
    public Result saveElement(@RequestBody Element element);

    //删除土壤元素信息
    @DeleteMapping("/element/deleteById")
    public Result deleteElementById(@RequestParam("id") String id);

    //修改土壤元素信息
    @PutMapping("/element/update")
    public Result updateElement(@RequestBody Element element);

    //查找所有土壤分级标准
    @GetMapping("/elementstandard/findAll/{index}/{limit}")
    public Result findAllStandard(@PathVariable int index, @PathVariable int limit);

    //保存土壤分级标准
    @PostMapping("/elementstandard/save")
    public Result saveStandard(@RequestBody ElementStandard standard);

    //删除土壤分级标准
    @DeleteMapping("/elementstandard/deleteById")
    public Result deleteStandardById(@RequestParam("id") String id);

    //修改土壤分级标准
    @PutMapping("/fertilitystandard/update")
    public Result updateStandard(@RequestBody ElementStandard standard);

    //查找所有土壤肥力分级
    @GetMapping("/fertilitystandard/findAll/{index}/{limit}")
    public Result findAllFStandard(@PathVariable int index, @PathVariable int limit);

    //保存土壤肥力分级
    @PostMapping("/fertilitystandard/save")
    public Result saveFStandard(@RequestBody FertilityStandard standard);

    //删除土壤肥力分级
    @DeleteMapping("/fertilitystandard/deleteById")
    public Result deleteFStandardById(@RequestParam("id") int id);

    //修改土壤肥力分级
    @PutMapping("/fertilitystandard/update")
    public Result updateFStandard(@RequestBody FertilityStandard standard);

    /**
     * 农事统计功能实现
     */

    @GetMapping("/soil/findNameByuserid/{userid}")
    public List<Soil> findNameByuserid(@PathVariable String userid);

    //查找土地该月的农事id次数
    @GetMapping("/sowrecord/findSoilRecord/{landid}/{farmworkid}")
    public int countSoilRecord(@PathVariable String landid,@PathVariable String farmworkid);

    /**
     * 土壤质量智能评级
     */

    //获取土地肥力分级
    @GetMapping("/predict/soil/{n}/{p}/{k}")
    public Integer PythonPredcit(@PathVariable double n,@PathVariable double p,@PathVariable double k);

    //获取土地肥力分级评价
    @GetMapping("/fertilitystandard/findByLevel/{level}")
    public FertilityStandard findByLevel(@PathVariable Integer level);

}
