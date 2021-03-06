package com.wly.controller;

import com.wly.entity.Auth;
import com.wly.entity.Role;
import com.wly.repository.AuthRepository;
import com.wly.repository.RoleRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthRepository authRepository;

    @GetMapping("/findAll")
    public Result findAll(){
        return new Result(0,"",authRepository.count(),authRepository.findAll());
    }

    @GetMapping("/findAuthids")
    public List<Integer> findAuthids(){
        return authRepository.Authids();
    }



    @GetMapping("findById/{id}")
    public Result findById(@PathVariable("id") int id){
        return new Result(200,"查询成功！",1,authRepository.findById(id));
    }

//    @GetMapping("/count")
//    public int count(){
//        return userRepository.count();
//    }

    @PostMapping("/save")
    public Result save(@RequestBody Auth auth){
        authRepository.save(auth);
        return new Result(200,"保存成功！",1,"");
    }

    @PutMapping("/update")
    public void update(@RequestBody Auth auth){
        authRepository.update(auth);
    }



    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") int id){
        authRepository.deleteById(id);
    }

}
