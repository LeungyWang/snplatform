package com.wly.feign;

import com.wly.entity.Goods;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("product")
public interface ProductFeign {

    //查询农户所有的农产品
    @GetMapping("/goods/findAll/{index}/{limit}/{userid}")
    public Result findAll(@PathVariable int index, @PathVariable int limit, @PathVariable String userid);

    //增加功能
    @PostMapping("/goods/save/{userid}")
    public Result saveGoods(@RequestBody Goods goods, @PathVariable String userid);





}
