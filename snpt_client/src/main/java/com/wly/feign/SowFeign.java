package com.wly.feign;

import com.wly.entity.*;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("land")
public interface SowFeign {
    /**
     *
     *种子模块
     *
     */

    @GetMapping("/seed/findAll/{index}/{limit}/{userid}")
    public Result findAllSeed(@PathVariable int index, @PathVariable int limit, @PathVariable String userid);

    @GetMapping("/seed/findAll/{userid}")
    public Result findAllSeed(@PathVariable String userid);

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



    /**
     * 农事记录模块
     */
    //查找全部功能
    @GetMapping("/sowrecord/findAll/{userid}")
    public Result findAllRecord(@PathVariable String userid);


    //返回所有农事类型
    @GetMapping("/farmwork/findAll")
    public Result findAllfarmwork();

    //查找所有农事类型
    @GetMapping("/farmwork/findAll/{index}/{limit}")
    public Result findAllFarmwork(@PathVariable int index, @PathVariable int limit);

    //保存农事类型
    @PostMapping("/farmwork/save")
    public Result saveFarmwork(@RequestBody FarmWork farmWork);

    //删除农事类型
    @DeleteMapping("/farmwork/deleteById")
    public Result deleteFarmwork(@RequestParam("id") String id);

    //修改农事类型
    @PutMapping("/farmwork/update")
    public Result updateFarmwork(@RequestBody FarmWork farmWork);


    //增加功能
    @PostMapping("/sowrecord/save/{userid}")
    public Result saveSowrecord(@RequestBody SowRecord sowRecord, @PathVariable String userid);

    /**
     * 农资
     * @return
     */
    //返回农资类型
    @GetMapping("/prodcutiontype/findAllPT")
    public Result findAllPT();

    //返回用户农资
    @GetMapping("/fertilizer/findAllFertilizer/{userid}")
    public Result findAllFertilizer(@PathVariable String userid);

    //查找所有农资类型
    @GetMapping("/agrotype/findAll/{index}/{limit}")
    public Result findAllTypes(@PathVariable int index, @PathVariable int limit);

    //保存农资类型
    @PostMapping("/agrotype/save")
    public Result saveType(@RequestBody AgroType agroType);

    //删除农资类型
    @DeleteMapping("/agrotype/deleteById")
    public Result deleteType(@RequestParam("id") String id);

    //修改农资类型
    @PutMapping("/agrotype/update")
    public Result updateType(@RequestBody AgroType agroType);


}
