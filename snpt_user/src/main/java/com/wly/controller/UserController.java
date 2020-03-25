package com.wly.controller;

import com.wly.entity.User;
import com.wly.repository.RoleRepository;
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

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
        List<User> users = userRepository.findAll(index,limit);
        for (int i=0;i<users.size();i++){
            User user = users.get(i);
            String userid = user.getId();
            user.setRoles(roleRepository.findRoles(userid));
        }
        return new Result(0,"",userRepository.count(),users);
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
    public User findById(@PathVariable("id") String id){
        return userRepository.findById(id);
    }

//    @GetMapping("/count")
//    public int count(){
//        return userRepository.count();
//    }

    @PostMapping("/save")
    public Result save(@RequestBody User user){
        if (userRepository.findByUsername(user.getUsername())==null) {
            user.setCreatetime(new Date());
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

    //更改用户状态
    @PutMapping("/updateStatus/{flag}/{id}")
    public Result updateStatus(@PathVariable int flag,@PathVariable String id){
        userRepository.updateStatus(flag,id);
        return new Result(200,"修改成功！",0,"");
    }


    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") String id){
        userRepository.deleteById(id);
    }

}
