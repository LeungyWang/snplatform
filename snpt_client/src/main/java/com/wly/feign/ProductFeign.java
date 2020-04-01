package com.wly.feign;

import com.wly.entity.Goods;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("product")
public interface ProductFeign {

    //查询所有农产品
    //查找所有产品
    @GetMapping("/goods/findAll/{index}/{limit}")
    public Result findAll(@PathVariable int index, @PathVariable int limit);

    //查询农户所有的农产品
    @GetMapping("/goods/findAll/{index}/{limit}/{userid}")
    public Result findAllByuser(@PathVariable int index, @PathVariable int limit, @PathVariable String userid);

    //单个产品详情页
    @GetMapping("/goods/findById/{id}")
    public Goods findById(@PathVariable String id);

    //增加功能
    @PostMapping("/goods/save/{userid}")
    public Result saveGoods(@RequestBody Goods goods, @PathVariable String userid);

    //农产品申请上架
    @PutMapping("/goods/verifyapply/{id}")
    public Result verifyApply(@PathVariable String id);

    //农产品取消上架申请
    @PutMapping("/goods/cancelapply/{id}")
    public Result cancelApply(@PathVariable String id);

    //农户下架农产品
    @PutMapping("/goods/soldout/{id}")
    public Result soldout(@PathVariable String id);

    //管理员通过农产品审核
    @PutMapping("/goods/approve/{id}")
    public Result approveApply(@PathVariable String id);

    //管理员不通过农产品审核
    @PutMapping("/goods/disapprove/{id}")
    public Result disapproveApply(@PathVariable String id);


    //展示审核通过的蔬菜类农产品
    @GetMapping("/goods/findVegetablesProduct")
    public List<Goods> findVegetablesProduct();

    //展示审核通过的水果类农产品
    @GetMapping("/goods/findFruitsProduct")
    public List<Goods> findFruitsProduct();

    //展示审核通过的粮食作物类农产品
    @GetMapping("/goods/findCerealsProduct")
    public List<Goods> findCerealsProduct();

    //展示审核通过的种子农资产品
    @GetMapping("/goods/findSeedProduct")
    public List<Goods> findSeedProduct();

    //展示审核通过的肥料农资产品
    @GetMapping("/goods/findFertilizerProduct")
    public List<Goods> findFertilizerProduct();

    //展示审核通过的肥料农资产品
    @GetMapping("/goods/findPesticideProduct")
    public List<Goods> findPesticideProduct();
}
