package com.wly.feign;

import com.wly.entity.Businesses;
import com.wly.entity.Client;
import com.wly.entity.Farmer;
import com.wly.entity.User;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("user")
public interface UserFeign {


    /**
     *
     * 用户注册登录管理
     *
     */
    //发送短信验证码
    @RequestMapping(value = "/user/sendsms/{mobile}",method = RequestMethod.POST)
    public Result sendMessages(@PathVariable String mobile);

    @GetMapping("/user/findAll/{index}/{limit}")
    public Result findAll(@PathVariable("index") int index, @PathVariable("limit") int limit);

    @GetMapping("/user/login/{username}/{password}/{type}")
    public Result login(@PathVariable("username") String usernmae, @PathVariable("password") String password, @PathVariable("type") String type);


    @PostMapping("/user/regist/{type}/{code}")
    public Result save(@PathVariable String type,@PathVariable String code,@RequestBody User user);

    @PutMapping("/user/updateStatus/{flag}/{id}")
    public Result updateStatus(@PathVariable int flag,@PathVariable String id);


    @PostMapping("/farmer/save")
    public Result saveFarmer(@RequestBody Farmer farmer);

    @PostMapping("/client/save")
    public Result saveClient(@RequestBody Client client);

    @PostMapping("/businesses/save")
    public Result saveBusinesses(@RequestBody Businesses businesses);

    /**
     *
     * 角色管理
     *
     */

    //查找所有的用户角色
    @GetMapping("/role/findAll/{index}/{limit}")
    public Result findAllRoles(@PathVariable("index") int index, @PathVariable("limit") int limit);

    //查找指定的用户角色
    @GetMapping("/role/findById/{id}")
    public Result findRoleById(@PathVariable("id") String id);

    //权限分配
    @PutMapping("/role/updateChecked/{roleid}/{authid}")
    public void updateChecked(@PathVariable String roleid,@PathVariable int authid);

    //取消权限
    @PutMapping("/role/updateCheck/{roleid}/{authid}")
    public void updateCheck(@PathVariable String roleid,@PathVariable int authid);

    /**
     *
     * 权限管理
     *
     */

    //查询所有权限id
    @GetMapping("/auth/findAuthids")
    public List<Integer> findAuthids();

    //查询所有权限
    @GetMapping("/auth/findAll")
    public Result findAllAuth();




}