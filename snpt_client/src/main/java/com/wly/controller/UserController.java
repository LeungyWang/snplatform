package com.wly.controller;

import com.wly.entity.*;
import com.wly.feign.UserFeign;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {


    @Autowired
    UserFeign userFeign;

    @GetMapping("/findAll")
    @ResponseBody
    public Result findAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        Result users = userFeign.findAll(index,limit);
        return users;
    }

    //查找所有角色
    @GetMapping("/role/findAll")
    @ResponseBody
    public Result findAllRoles(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        Result roles = userFeign.findAllRoles(index,limit);
        return roles;
    }

    //查找指定角色
    @GetMapping("/role/findById")
    @ResponseBody
    public Result findRoleById(@RequestParam("id") String id){
        return userFeign.findRoleById(id);
    }



    //页面跳转
    @GetMapping("/redirect/{localtion}")
    public String redirect(@PathVariable("localtion") String localtion){
        return localtion;
    }

    //分配角色权限
    @GetMapping("/role/updateChecked")
    @ResponseBody
    public Result updateChecked(@RequestParam String roleid,@RequestParam String authids){
        String[] auths  = authids.split(",");
        List<Integer> aids = userFeign.findAuthids();
        for (int i=0;i<aids.size();i++){
            userFeign.updateCheck(roleid,aids.get(i));
        }
        for (int i=0;i<auths.length;i++){
            int authid = Integer.parseInt(auths[i]);
            userFeign.updateChecked(roleid,authid);
        }
        return new Result(200,"保存成功",0,"");
    }

    //更改用户账户状态
    @GetMapping("/updateStatus")
    @ResponseBody
    public Result updateStatus(@RequestParam int status,@RequestParam String id){
        return userFeign.updateStatus(status,id);
    }

    //查找所有权限
    @GetMapping("/auth/findAll")
    @ResponseBody
    public Result findAllAuth(){
        Result auths = userFeign.findAllAuth();
        return auths;
    }

//    @PostMapping("/login")
//    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("type") String type,HttpSession session){
//        Result result = userFeign.login(username,password,type);
//        Object object = result.getData();
//        if (object!=null) {
//            LinkedHashMap<String, Object> hashMap = (LinkedHashMap) object;
//            String id = "";
//            switch (type) {
//                case "user":
//                    User user = new User();
//                    id = hashMap.get("id") + "";
//                    String name = (String) hashMap.get("name");
//                    user.setId(id);
//                    user.setName(name);
//                    session.setAttribute("user", user);
//                    return "client_index";
//                case "admin":
////                    Admin admin = new Admin();
////                    idStr = hashMap.get("id")+"";
////                    id = Long.parseLong(idStr);
////                    String adminname = (String) hashMap.get("name");
////                    admin.setId(id);
////                    admin.setUsername(adminname);
////                    session.setAttribute("admin",admin);
////                    result = "main";
//                    break;
//            }
//        }
//        return "login";
//    }

    /**
     *
     * @param password
     * @param type
     * @实现登录功能
     */

    @GetMapping("/login")
    @ResponseBody
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("type") String type, HttpSession session){
        Result result = userFeign.login(username,password,type);
        Object object = result.getData();
        if (object!=null) {
            LinkedHashMap<String, Object> hashMap = (LinkedHashMap) object;
            String id = "";
            switch (type) {
                case "user":
                    User user = new User();
                    id = hashMap.get("id") + "";
                    String nickname = (String) hashMap.get("nickname");
                    user.setId(id);
                    user.setNickname(nickname);
                    session.setAttribute("user", user);
                    session.setAttribute("roles",userFeign.findRoles(id));
                    return result;
                case "admin":
                    Admin admin = new Admin();
                    id = hashMap.get("id")+"";
                    String adminname = (String) hashMap.get("name");
                    admin.setId(id);
                    admin.setName(adminname);
                    session.setAttribute("admin",admin);
                    return result;
            }
        }
        return result;
    }

    //登出功能
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "client_index";
    }


    /**
     *
     * @实现注册功能
     */
    //发送短信验证码功能
    @PostMapping("/sendSms")
    @ResponseBody
    public Result sendSms(@RequestParam String mobile){
        return userFeign.sendMessages(mobile);
    }


    //注册时保存系统用户信息
    @PostMapping("/regist/{type}/{code}")
    @ResponseBody
    public Result save(@PathVariable String type, @PathVariable String code,User user){
        return userFeign.save(type,code,user);
    }


    //注册时保存农户信息
    @PostMapping("/farmer/save")
    @ResponseBody
    public Result saveFarmer(Farmer farmer){
        return userFeign.saveFarmer(farmer);
    }

    //注册时保存普通用户信息
    @PostMapping("/client/save")
    @ResponseBody
    public Result saveClient(Client client){
        return userFeign.saveClient(client);
    }

    //注册时保存农户信息
    @PostMapping("/businesses/save")
    @ResponseBody
    public Result saveBusinesses(Businesses businesses){
        return userFeign.saveBusinesses(businesses);
    }


}
