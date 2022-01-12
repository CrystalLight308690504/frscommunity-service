package priv.crystallightghost.frscommunity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priv.crystallightghost.frscommunity.pojo.system.User;
import priv.crystallightghost.frscommunity.respond.Result;
import priv.crystallightghost.frscommunity.service.UserService;

import java.util.Map;

/**
 * @Author CrystalLightGhost
 * @Date 2020/9/11 18:38
 * @Version 1.0
 * @Descriotion
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    /**
     * 用户登录
     * 1.通过service根据mobile查询用户
     * 2.比较password
     * 3.生成jwt信息
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody Map<String, String> loginMap) {

        System.out.println("login...........");
        String phone = loginMap.get("phone");
        String password = loginMap.get("password");
       return userService.login(phone,password);
    }


    /**
     * 用户注册
     * 1.通过service根据mobile查询用户
     * 2.比较password
     * 3.生成jwt信息
     */

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@RequestBody User user) {

        return  userService.save(user);
    }

    @RequestMapping(value = "/verifyCount/{count}", method = RequestMethod.GET)
    public  Result verifyCount(@PathVariable String count){
        System.out.printf(count);
        return null;
    }

    @RequestMapping(value = "/{id}")
    public String cc(@PathVariable(value = "id") Long id){
        return  id + "";
    }


}