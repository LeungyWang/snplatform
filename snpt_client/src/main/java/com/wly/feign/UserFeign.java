package com.wly.feign;

import com.wly.entity.User;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("user")
public interface UserFeign {



    @GetMapping("/user/login/{username}/{password}/{type}")
    public Result login(@PathVariable("username") String usernmae, @PathVariable("password") String password, @PathVariable("type") String type);


    @PostMapping("/user/save")
    public Result save(@RequestBody User user);


}