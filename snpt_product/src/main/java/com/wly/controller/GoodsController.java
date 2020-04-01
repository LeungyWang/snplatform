package com.wly.controller;

import com.wly.entity.Goods;
import com.wly.repository.GoodsRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    public GoodsRepository goodsRepository;

    @Autowired
    public IdWorker idWorker;

    //查找指定用户的产品
    @GetMapping("/findAll/{index}/{limit}/{userid}")
    public Result findAll(@PathVariable int index, @PathVariable int limit, @PathVariable String userid){
        return new Result(0,"",goodsRepository.countByUserid(userid),goodsRepository.findAllByUserId(userid,index,limit));
    }

    //查找所有产品正在审核中的产品
    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable int index, @PathVariable int limit){
        return new Result(0,"",goodsRepository.count(),goodsRepository.findAll(index,limit));
    }

    //查找功能
    @GetMapping("/findAll/{userid}")
    public Result findAll(@PathVariable String userid){
        return new Result(0,"",goodsRepository.countByUserid(userid),goodsRepository.findGoodByuserid(userid));
    }


    //查找功能
    @GetMapping("/findById/{id}")
    public Goods findById(@PathVariable String id){
        return goodsRepository.findById(id);
    }

    //增加功能
    @PostMapping("/save/{userid}")
    public Result save(@RequestBody Goods goods,@PathVariable String userid){
        goods.setApplicationtime(new Date());
        goods.setApplicant(userid);
        goods.setId("GD"+idWorker.nextId());
        goods.setStatus("未上架");
        goodsRepository.save(goods);
        return new Result(200,"保存成功！",0,"");
    }

    //删除功能
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable String id){
        goodsRepository.deleteById(id);
        return new Result(200,"删除成功！",0,"");
    }

    //修改功能
    @PutMapping("/update/{status}")
    public Result update(@RequestBody Goods goods){
        goodsRepository.update(goods);
        return new Result(200,"修改成功！",1,"");
    }

    //农产品申请上架
    @PutMapping("/verifyapply/{id}")
    public Result verifyApply(@PathVariable String id){
        goodsRepository.updateStatus("审核中",id);
        return new Result(200,"产品正在审核中！",1,"");
    }

    //农产品取消上架申请
    @PutMapping("/cancelapply/{id}")
    public Result cancelApply(@PathVariable String id){
        goodsRepository.updateStatus("未上架",id);
        return new Result(200,"产品上架申请取消成功！",1,"");
    }

    //农户下架农产品
    @PutMapping("/soldout/{id}")
    public Result soldout(@PathVariable String id){
        goodsRepository.updateStatus("已下架",id);
        return new Result(200,"产品下架成功！",1,"");
    }

    //管理员通过农产品审核
    @PutMapping("/approve/{id}")
    public Result approveApply(@PathVariable String id){
        goodsRepository.updateStatus("审核通过",id);
        return new Result(200,"产品审核通过成功！",1,"");
    }

    //管理员不通过农产品审核
    @PutMapping("/disapprove/{id}")
    public Result disapproveApply(@PathVariable String id){
        goodsRepository.updateStatus("审核不通过",id);
        return new Result(200,"产品审核不通过成功！",1,"");
    }

    //展示审核通过的蔬菜类农产品
    @GetMapping("/findVegetablesProduct")
    public List<Goods> findVegetablesProduct(){
        return goodsRepository.findAllByCategory("1");
    }

    //展示审核通过的水果类农产品
    @GetMapping("/findFruitsProduct")
    public List<Goods> findFruitsProduct(){
        return goodsRepository.findAllByCategory("2");
    }

    //展示审核通过的粮食作物类农产品
    @GetMapping("/findCerealsProduct")
    public List<Goods> findCerealsProduct(){
        return goodsRepository.findAllByCategory("3");
    }

    //展示审核通过的种子农资产品
    @GetMapping("/findSeedProduct")
    public List<Goods> findSeedProduct(){
        return goodsRepository.findAllByCategory("4");
    }

    //展示审核通过的肥料农资产品
    @GetMapping("/findFertilizerProduct")
    public List<Goods> findFertilizerProduct(){
        return goodsRepository.findAllByCategory("5");
    }

    //展示审核通过的肥料农资产品
    @GetMapping("/findPesticideProduct")
    public List<Goods> findPesticideProduct(){
        return goodsRepository.findAllByCategory("6");
    }



}
