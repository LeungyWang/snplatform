package com.wly.controller;

import com.wly.entity.Farmer;
import com.wly.entity.User;
import com.wly.repository.FarmerRepository;
import com.wly.repository.RoleRepository;
import com.wly.repository.UserRepository;
import entity.Result;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/farmer")
public class FarmerController {

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    //查找所有用户
    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
        List<Farmer> users = userRepository.findAll(index,limit);
        for (int i=0;i<users.size();i++){
            User user = users.get(i);
            String userid = user.getId();
            user.setRoles(roleRepository.findRoles(userid));
        }
        return new Result(0,"",userRepository.count(),users);
    }

    /**
     * 登录功能实现
     * @param username
     * @param password
     * @param type
     * @return
     */
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

    //查找用户
    @GetMapping("findById/{id}")
    public User findById(@PathVariable("id") String id){
        return userRepository.findById(id);
    }

    /**
     * 发送短信验证码
     */
    @RequestMapping(value = "/sendsms/{mobile}",method = RequestMethod.POST)
    public Result sendMessages(@PathVariable String mobile){
        sendSms(mobile);
        return new Result(200,"发送成功！",0,"");
    }

    /**
     * 注册功能实现
     * @param user
     * @return
     */
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

    /**
     * 农户信息保存
     * @param user
     */
    @PostMapping("/farmer/save")
    public Result saveFarmer(@RequestBody User user){
        if (userRepository.findByUsername(user.getUsername())==null) {
            user.setCreatetime(new Date());
            userRepository.save(user);
            return new Result(200,"恭喜您，注册成功！",1,"");
        }else{
            return new Result(500,"此用户名已被注册，请更换用户名！",1,"");
        }
    }



    //修改用户信息
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


    //删除用户
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") String id){
        userRepository.deleteById(id);
    }

    public void sendSms(String mobile){
        //生成六位随机数
        String checkcode = RandomStringUtils.randomNumeric(6);
        //向缓存中添加
        redisTemplate.opsForValue().set("checkcode_"+mobile, checkcode,6, TimeUnit.HOURS);
        Map<String,String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("checkcode", checkcode);
        //给用户发一份
		rabbitTemplate.convertAndSend("sms",map);
        //在控制台中显示一份【方便测试】
        System.out.println("验证码为："+checkcode);
    }

}
