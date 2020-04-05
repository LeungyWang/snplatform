package com.wly.controller;

import com.wly.entity.Goods;
import com.wly.entity.User;
import com.wly.feign.ProductFeign;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductFeign productFeign;

    //查询所有产品
    @GetMapping("/goods/findAllGoods")
    @ResponseBody
    public Result findAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        Result goods = productFeign.findAll(index,limit);
        return goods;
    }

    //查询用户所有农产品功能实现
    @GetMapping("/goods/findAll")
    @ResponseBody
    public Result findAllGoods(@RequestParam("page") int page, @RequestParam("limit") int limit, HttpSession session){
        int index = (page-1)*limit;
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        Result goods = productFeign.findAllByuser(index,limit,userid);
        return goods;
    }

    //
    @GetMapping("/goods/detail/{id}")
    public ModelAndView findById(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product_details");
        modelAndView.addObject("product",productFeign.findById(id));
        return modelAndView;
    }



    //页面跳转
    @GetMapping("/redirect/{localtion}")
    public String redirect(@PathVariable("localtion") String localtion){
        return localtion;
    }

    //图片上传
    //图片上传测试
    @ResponseBody
    @RequestMapping("/upload")
    public Map upload(MultipartFile file, HttpServletRequest request){

        String prefix="";
        String dateStr="";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                Date date = new Date();
                String uuid = UUID.randomUUID()+"";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
                //绝对路径
                String filepath = "/Users/ouryouhime/snplatform/snpt_client/src/main/resources/static/assets/images/" + dateStr+"/"+uuid+"." + prefix;
//                String filepath = "./snpt_client/src/main/resources/static/assets/images/" + dateStr+"/"+uuid+"." + prefix;


                File files=new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                System.out.println(System.getProperty("user.dir"));
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                map2.put("src","/assets/images/"+ dateStr+"/"+uuid+"." + prefix);
                return map;
            }

        }catch (Exception e){
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;

    }

    //保存农产品
    @PostMapping("/goods/save")
    @ResponseBody
    public Result saveGood(Goods goods, HttpSession session){
    User user = (User) session.getAttribute("user");
    String userid = user.getId();
    return productFeign.saveGoods(goods,userid);
    }

    //农产申请上架
    @GetMapping("/goods/verifyapply")
    @ResponseBody
    public Result verifyGood(@RequestParam("id") String id){
        return productFeign.verifyApply(id);
    }

    //农产取消上架申请
    @GetMapping("/goods/cancelapply")
    @ResponseBody
    public Result cancelGood(@RequestParam("id") String id){
        return productFeign.cancelApply(id);
    }

    //农户下架农产品
    @GetMapping("/goods/soldout")
    @ResponseBody
    public Result goodsoldout(@RequestParam("id") String id){
        return productFeign.soldout(id);
    }

    //管理员通过上架申请
    @GetMapping("/goods/approveapply")
    @ResponseBody
    public Result approveApply(@RequestParam("id") String id){
        return productFeign.approveApply(id);
    }

    //管理员不通过上架申请
    @GetMapping("/goods/disapproveapply")
    @ResponseBody
    public Result disapproveApply(@RequestParam("id") String id){
        return productFeign.disapproveApply(id);
    }

    //查找所有已上架产品
    @GetMapping("/goods/findAllProduct")
    public ModelAndView findAllProduct(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shop_index");
        modelAndView.addObject("vegetables",productFeign.findVegetablesProduct());
        modelAndView.addObject("fruits",productFeign.findFruitsProduct());
        modelAndView.addObject("cereals",productFeign.findCerealsProduct());
        modelAndView.addObject("seeds",productFeign.findSeedProduct());
        modelAndView.addObject("fertilizers",productFeign.findFertilizerProduct());
        modelAndView.addObject("pesticides",productFeign.findPesticideProduct());
        return modelAndView;
    }

    /**
     * 购物车功能实现
     */

    //用户查看购物车
    @GetMapping("/cart/findAllCart")
    public ModelAndView findAllCart(HttpSession session){
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shop_cart");
        modelAndView.addObject("carts",productFeign.findAllCarts(userid));
        return modelAndView;
    }



}
