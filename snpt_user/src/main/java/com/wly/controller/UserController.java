package com.wly.controller;

import com.wly.entity.User;
import com.wly.repository.UserRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/findAll/{index}/{limit}")
    public List<User> findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
        return userRepository.findAll(index,limit);
    }


    @GetMapping("/login/{username}/{password}/{type}")
    public Result login(@PathVariable("username") String username,@PathVariable("password") String password,@PathVariable("type") String type){
        Object object = null;
        switch (type){
            case "user":
                object = userRepository.login(username,password);
                if (object!=null){
                return new Result(200, "登录成功！", 1, object);
                }
                break;
            case "admin":
//                object = adminRepository.login(username,password);
                break;
        }
        return new Result(500,"用户名或密码错误！",1,object);


    }

    @GetMapping("findById/{id}")
    public User findById(@PathVariable("id") long id){
        return userRepository.findById(id);
    }

    @GetMapping("/count")
    public int count(){
        return userRepository.count();
    }

    @PostMapping("/save")
    public Result save(@RequestBody User user){
        if (userRepository.findByUsername(user.getUsername())==null) {
            user.setRegistdate(new Date());
            userRepository.save(user);
            return new Result(200,"恭喜您，注册成功！",1,"");
        }else{
            return new Result(500,"此用户名已被注册，请更换用户名！",1,"");
        }
    }

    @PutMapping("/update")
    public void update(@RequestBody User user){
        userRepository.update(user);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
        userRepository.deleteById(id);
    }

}
