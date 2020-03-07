package com.wly.feign;

import com.wly.entity.Fertilizer;
import com.wly.entity.Pesticide;
import com.wly.entity.Seed;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("sow")
public interface SowFeign {
    /**
     *
     *种子模块
     *
     */

    @GetMapping("/seed/findAll/{index}/{limit}/{userid}")
    public Result findAllSeed(@PathVariable int index, @PathVariable int limit, @PathVariable String userid);

    //查找功能
    @GetMapping("/seed/findById/{id}")
    public Result findSeedById(@PathVariable String id);

    //增加功能
    @PostMapping("/seed/save/{userid}")
    public Result saveSeed(@RequestBody Seed seed, @PathVariable String userid);

    //删除功能
    @DeleteMapping("/seed/deleteById/{id}")
    public Result deleteSeedById(@PathVariable String id);

    //修改功能
    @PutMapping("/seed/update")
    public Result updateSeed(@RequestBody Seed seed);


    /**
     *
     *肥料模块
     *
     */

    @GetMapping("/fertilizer/findAll/{index}/{limit}/{userid}")
    public Result findAllFertilizer(@PathVariable int index, @PathVariable int limit, @PathVariable String userid);

    //查找功能
    @GetMapping("/fertilizer/findById/{id}")
    public Result findFertilizerById(@PathVariable String id);

    //增加功能
    @PostMapping("/fertilizer/save/{userid}")
    public Result saveFertilizer(@RequestBody Fertilizer fertilizer, @PathVariable String userid);

    //删除功能
    @DeleteMapping("/fertilizer/deleteById/{id}")
    public Result deleteFertilizerById(@PathVariable String id);

    //修改功能
    @PutMapping("/fertilizer/update")
    public Result updateFertilize(@RequestBody Fertilizer fertilizer);


    /**
     *
     *农药模块
     *
     */

    @GetMapping("/pesticide/findAll/{index}/{limit}/{userid}")
    public Result findAllPesticide(@PathVariable int index, @PathVariable int limit, @PathVariable String userid);

    //查找功能
    @GetMapping("/pesticide/findById/{id}")
    public Result findPesticideById(@PathVariable String id);

    //增加功能
    @PostMapping("/pesticide/save/{userid}")
    public Result savePesticide(@RequestBody Pesticide pesticide, @PathVariable String userid);

    //删除功能
    @DeleteMapping("/pesticide/deleteById/{id}")
    public Result deletePesticideById(@PathVariable String id);

    //修改功能
    @PutMapping("/pesticide/update")
    public Result updatePesticide(@RequestBody Pesticide pesticide);

}
