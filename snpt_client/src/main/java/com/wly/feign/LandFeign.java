package com.wly.feign;

import com.wly.entity.AgroChemical;
import com.wly.entity.EchartsMap;
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
    public Result findById(@PathVariable("id") long id);


    @PutMapping("/soil/update")
    public Result update(@RequestBody Soil soil);

    @DeleteMapping("/soil/deleteById")
    public Result deleteById(@RequestParam("id") long id);

    @PostMapping("/soil/save")
    public Result save(Soil soil);

    @GetMapping("/soiltype/findAllTypes")
    public Result findAllTypes();

    @GetMapping("/agrochemical/findAll/{index}/{limit}")
    public Result findAgchedAll(@PathVariable("index") int index, @PathVariable("limit") int limit);


    @GetMapping("/agrochemical/findAllByuserid/{index}/{limit}/{userid}")
    public Result findAgcheAll(@PathVariable int index, @PathVariable int limit, @PathVariable String userid);

    @PostMapping("/agrochemical/save")
    public Result saveAgrochemical(AgroChemical agroChemical);

    @PutMapping("/agrochemical/update")
    public Result updateAgrochemical(@RequestBody AgroChemical agroChemical);

    @GetMapping("/agrochemical/geteCharts")
    public EchartsMap geteCharts();






}
