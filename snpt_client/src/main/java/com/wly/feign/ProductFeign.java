package com.wly.feign;

import com.wly.entity.Cart;
import com.wly.entity.Goods;
import com.wly.entity.Order;
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


    /**
     * 购物车功能
     */

    //查找用户购物车
    @GetMapping("/cart/findAll/{userid}")
    public List<Cart> findAllCarts(@PathVariable String userid);

    //加入购物车
    @PostMapping("/cart/save")
    public Result addCart(@RequestBody Cart cart);

    //删除购物车商品
    @DeleteMapping("/cart/deleteById/{id}")
    public Result deleteCartById(@PathVariable String id);


    /**
     * 下单功能
     */

    //提交订单
    @PostMapping("/order/save/{userid}")
    public void saveOrder(Order order,@PathVariable("userid") String userid);

    /**
     * 商户订单管理功能
     */

    //查询卖家订单
    @GetMapping("/order/bs/findAll/{userid}/{index}/{limit}")
    public Result findOrderByBuser(@PathVariable int index, @PathVariable int limit, @PathVariable String userid);

    //查询订单下的所有产品 有分页
    @GetMapping("/orderdetails/findAll/{order_id}/{index}/{limit}")
    public Result findDetailsByOrder(@PathVariable String order_id, @PathVariable int index, @PathVariable int limit);

    //产品发货功能
    @PutMapping("/order/deliver/{order_id}")
    public Result deliver(@PathVariable String order_id);

    //产品收货功能
    @PutMapping("/order/receive/{order_id}")
    public Result receive(@PathVariable String order_id);

}
