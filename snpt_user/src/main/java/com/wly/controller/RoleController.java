package com.wly.controller;

import com.wly.entity.Auth;
import com.wly.entity.Role;
import com.wly.entity.User;
import com.wly.repository.AuthRepository;
import com.wly.repository.RoleRepository;
import com.wly.repository.UserRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthRepository authRepository;

    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
        List<Role> roles = roleRepository.findAll(index,limit);
        for (int i=0;i<roles.size();i++){
            Role role = roles.get(i);
            String roleid = role.getId();
            role.setAuths(authRepository.findAuths(roleid));
        }
        return new Result(0,"",roleRepository.count(),roles);
    }



    @GetMapping("findById/{id}")
    public Result findById(@PathVariable("id") String id){
        Role role = roleRepository.findById(id);
        List<Auth> auths = authRepository.findAuths(role.getId());
        for (int i = 0;i<auths.size();i++){
            Auth auth = auths.get(i);
            auth.setChecked(roleRepository.findChecked(role.getId(),auth.getId()));
        }
        role.setAuths(auths);
        return new Result(200,"查询成功！",1,role);
    }

//    @GetMapping("/count")
//    public int count(){
//        return userRepository.count();
//    }

    @PostMapping("/save")
    public Result save(@RequestBody Role role){
        roleRepository.save(role);
        return new Result(200,"保存成功！",1,"");
    }

    @PutMapping("/update")
    public void update(@RequestBody Role role){
        roleRepository.update(role);
    }

    //分配权限
    @PutMapping("/updateChecked/{roleid}/{authid}")
    public void updateChecked(@PathVariable String roleid,@PathVariable int authid){
        roleRepository.updateChecked(roleid,authid);
    }

    //取消权限
    @PutMapping("/updateCheck/{roleid}/{authid}")
    public void updateCheck(@PathVariable String roleid,@PathVariable int authid){
        roleRepository.updateCheck(roleid,authid);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") String id){
        roleRepository.deleteById(id);
    }

}
