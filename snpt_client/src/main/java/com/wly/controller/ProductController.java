package com.wly.controller;

import com.alibaba.fastjson.JSON;
import com.netflix.ribbon.proxy.annotation.Http;
import com.wly.entity.Cart;
import com.wly.entity.Goods;
import com.wly.entity.Order;
import com.wly.entity.User;
import com.wly.feign.ProductFeign;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

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
        JSON shop_cart_json = (JSON) JSON.toJSON(productFeign.findAllCarts(userid));
        modelAndView.setViewName("shop_cart");
        modelAndView.addObject("carts",shop_cart_json);
        return modelAndView;
    }

    //加入购物车功能
    @PostMapping("/cart/addCart")
    @ResponseBody
    public Result addCart(@RequestParam("productid") String productid,@RequestParam("product_amount") int amount,@RequestParam ("price") double price, HttpSession session){
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        Cart cart = new Cart();
        Goods goods = new Goods();
        goods.setId(productid);
        cart.setGoods(goods);
        cart.setPrice(price);
        cart.setProduct_amount(amount);
        cart.setCustomer_id(userid);
        return productFeign.addCart(cart);
    }

    //删除购物车商品
    @GetMapping("/cart/delete/{id}")
    public void deleteById(ServletResponse servletResponse, @PathVariable String id) throws IOException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        productFeign.deleteCartById(id);
        response.sendRedirect("/product/cart/findAllCart");
    }


    //提交购物车内容到订单页面
    @PostMapping("/order/addPre")
    public String addPre(@RequestParam("orderInfoStr") String orderInfoStr,@RequestParam("allcost") Double allcost,Model model){
        System.err.println(orderInfoStr);
        //把字符串转换成json
        List<Cart> Carts =  JSON.parseArray(orderInfoStr,Cart.class);
        model.addAttribute("carts",Carts);
        model.addAttribute("allcost",allcost);
        model.addAttribute("orderInfo",orderInfoStr);
        return "product_checkout";
    }


    /**
     * 下单功能
     * @param order
     * @param session
     * @return
     */

    //下单功能实现
    @PostMapping("/order/add")
    public String addOrder(Order order,HttpSession session)  {
        //设置订单状态为已下单
        User user = (User) session.getAttribute("user");
        String orderInfoStr = order.getOrderInfoStr();
        System.err.println(orderInfoStr);
        String userid = user.getId();
        System.err.println(order);
        productFeign.saveOrder(order,userid);
        return "product_checkout";
    }

    /**
     * 商家订单管理功能
     */

    //查询卖家所有订单功能实现
    @GetMapping("/bs/order/findAll")
    @ResponseBody
    public Result findBUserOrder(@RequestParam("page") int page, @RequestParam("limit") int limit, HttpSession session){
        int index = (page-1)*limit;
        User user = (User) session.getAttribute("user");
        String userid = user.getId();
        Result orders = productFeign.findOrderByBuser(index,limit,userid);
        return orders;
    }

    //查询卖家所有订单产品功能实现
    @GetMapping("/orderdetails/findAll/{order_id}")
    @ResponseBody
    public Result findOrderDetails(@RequestParam("page") int page, @RequestParam("limit") int limit,@PathVariable String order_id){
        int index = (page-1)*limit;
        Result details = productFeign.findDetailsByOrder(order_id,index,limit);
        return details;
    }

    //卖家发货
    @GetMapping("/order/deliver")
    @ResponseBody
    public Result deliver(@RequestParam String order_id)  {
        return productFeign.deliver(order_id);
    }

    //用户收货
    @GetMapping("/order/receive")
    @ResponseBody
    public Result receive(@RequestParam String order_id)  {
        return productFeign.receive(order_id);
    }

}
