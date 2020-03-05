package com.wly.controller;

import com.wly.entity.Soil;
import com.wly.entity.User;
import com.wly.feign.UserFeign;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

@Controller
@RequestMapping("user")
public class UserController {


    @Autowired
    UserFeign userFeign;


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
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("type") String type,HttpSession session){
        Result result = userFeign.login(username,password,type);
        Object object = result.getData();
        if (object!=null) {
            LinkedHashMap<String, Object> hashMap = (LinkedHashMap) object;
            String id = "";
            switch (type) {
                case "user":
                    User user = new User();
                    id = hashMap.get("id") + "";
                    String name = (String) hashMap.get("name");
                    user.setId(id);
                    user.setName(name);
                    session.setAttribute("user", user);
                    return result;
                case "admin":
//                    Admin admin = new Admin();
//                    idStr = hashMap.get("id")+"";
//                    id = Long.parseLong(idStr);
//                    String adminname = (String) hashMap.get("name");
//                    admin.setId(id);
//                    admin.setUsername(adminname);
//                    session.setAttribute("admin",admin);
//                    result = "main";
                    break;
            }
        }
        return result;
    }

    /**
     *
     * @实现注册功能
     */
    @PostMapping("/save")
    @ResponseBody
    public Result save(User user){
        return userFeign.save(user);
    }

}
