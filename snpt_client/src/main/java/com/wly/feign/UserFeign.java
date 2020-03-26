package com.wly.feign;

import com.wly.entity.User;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("user")
public interface UserFeign {

    @GetMapping("/user/findAll/{index}/{limit}")
    public Result findAll(@PathVariable("index") int index, @PathVariable("limit") int limit);

    @GetMapping("/user/login/{username}/{password}/{type}")
    public Result login(@PathVariable("username") String usernmae, @PathVariable("password") String password, @PathVariable("type") String type);


    @PostMapping("/user/save")
    public Result save(@RequestBody User user);

    @PutMapping("/user/updateStatus/{flag}/{id}")
    public Result updateStatus(@PathVariable int flag,@PathVariable String id);

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

    @GetMapping("/auth/findAuthids")
    public List<Integer> findAuthids();

}