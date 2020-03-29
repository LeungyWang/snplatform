package com.wly.controller;

import com.wly.entity.User;
import com.wly.repository.RoleRepository;
import com.wly.repository.UserRepository;
import entity.Result;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public IdWorker idWorker;


    //查找所有用户
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
    @PostMapping("/regist/{type}/{code}")
    public Result regist(@PathVariable String type,@PathVariable String code,@RequestBody User user){
        //先得到缓存中的验证码
        String checkcodeRedis = (String) redisTemplate.opsForValue().get("checkcode_"+user.getPhone());
        if (checkcodeRedis.isEmpty()){
            return new Result(500,"请先获取验证码",1,"");
        }
        if (!checkcodeRedis.equals(code)){
            return new Result(500,"验证码有误",1,"");
        }
        String roleid = "";
        if (userRepository.findByUsername(user.getUsername())==null) {
            user.setId("U"+idWorker.nextId());
            user.setStatus(0);
            user.setCreatetime(new Date());
            if (type.equals("farmer")){
                roleid = roleRepository.findIdByCode("farmer");
            }else if (type.equals("businesses")){
                roleid = roleRepository.findIdByCode("businesses");
            }else if (type.equals("client")){
                roleid = roleRepository.findIdByCode("client");
            }
            String userid = user.getId();
            String id = "UR"+idWorker.nextId();
            userRepository.save(user);
            roleRepository.saveUserRole(id,userid,roleid);
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

    //发送短信验证码
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
