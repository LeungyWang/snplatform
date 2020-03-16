package com.wly.controller;

import com.wly.entity.Goods;
import com.wly.entity.User;
import com.wly.feign.ProductFeign;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


    //查询用户所有农产品功能实现
    @GetMapping("/goods/findAll")
    @ResponseBody
    public Result findAllSeed(@RequestParam("page") int page, @RequestParam("limit") int limit, HttpSession session){
        int index = (page-1)*limit;
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        Result goods = productFeign.findAll(index,limit,userid);
        return goods;
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
}
