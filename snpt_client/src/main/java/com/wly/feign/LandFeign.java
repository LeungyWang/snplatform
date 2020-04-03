package com.wly.feign;

import com.wly.entity.AgroChemical;
import com.wly.entity.EchartsMap;
import com.wly.entity.Report;
import com.wly.entity.Soil;
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

}
