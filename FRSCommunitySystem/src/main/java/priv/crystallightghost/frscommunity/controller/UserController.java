package priv.crystallightghost.frscommunity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import priv.crystallightghost.frscommunity.pojo.system.User;
import priv.crystallightghost.frscommunity.respond.Result;
import priv.crystallightghost.frscommunity.respond.ResultCode;
import priv.crystallightghost.frscommunity.service.UserService;

import javax.servlet.http.HttpServletRequest;
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
     * 注销登入
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    public Result logout(HttpServletRequest request) {
        String id = request.getHeader("Authorization");
        if (StringUtils.isEmpty(id)){
            return new Result(ResultCode.USERNOEXITED);
        }
        id = id.replaceAll("FRSC","shiro:session:");
        return userService.logout(id);
    }



    /**
     * 用户登录
     * 1.通过service根据mobile查询用户
     * 2.比较password
     * 3.生成jwt信息
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody Map<String, String> loginMap) {

        String loginIdenty =  null;
        System.out.println("login...........");
        String userName = loginMap.get("userName");
        String phoneNumber = loginMap.get("phoneNumber");
        if (!StringUtils.isEmpty(userName)){ // 用户名登陆
            loginIdenty = userName;
        }else if(!StringUtils.isEmpty(phoneNumber)){ // 手机号登陆
            loginIdenty = phoneNumber;
        }else if(StringUtils.isEmpty(userName)){
            return new Result(ResultCode.NOUSERNAMEINPUTED);
        }else if(StringUtils.isEmpty(phoneNumber)){
            return new Result(ResultCode.NOUSERPHONENUMBERIPUTED);
        }

        String password = loginMap.get("password");
        return userService.login(loginIdenty, password);
    }

    @RequestMapping(value = "/modifyUserName", method = RequestMethod.PUT)
    public Result modifyUserName(@RequestBody User user) {
        return userService.modifyUserName(user);
    }

    @RequestMapping(value = "/modifyUserEmail", method = RequestMethod.PUT)
    public Result modifyUserEmail(@RequestBody User user) {
        return userService.modifyUserEmail(user);
    }

    @RequestMapping(value = "/modifyUserPasswordByPhoneNumber", method = RequestMethod.PUT)
    public Result modifyUserPasswordByPhoneNumber(@RequestBody User user) {
        return userService.modifyUserPasswordByPhoneNumber(user);
    }
  @RequestMapping(value = "/modifyUserPasswordByOldPassword", method = RequestMethod.PUT)
    public Result modifyUserPasswordByOldPassword(@RequestBody User user) {
        return userService.modifyUserPasswordByOldPassword(user);
    }

    /**
     * 用户注册
     * 1.通过service根据mobile查询用户
     * 2.比较password
     * 3.生成jwt信息
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@RequestBody User user) {

        return userService.registerUser(user);
    }

    @RequestMapping(value = "/verifyUserName/{userName}", method = RequestMethod.GET)
    public Result verifyUserNameExited(@PathVariable String userName) {

        return  userService.verifyUserNameExited(userName);
    }

    @RequestMapping(value = "/verifyEmail/{email}", method = RequestMethod.GET)
    public Result verifyEmailExited(@PathVariable String email) {
        System.out.printf(email);
        return userService.verifyEmailExited(email);
    }

    @RequestMapping(value = "/isLogined",method = RequestMethod.GET)
    public Result isLogined(HttpServletRequest request) {
        String id = request.getHeader("Authorization");
        if (StringUtils.isEmpty(id)) {
            return new Result(ResultCode.USERNOEXITED);
        }
        id = id.replaceAll("FRSC", "shiro:session:");
        return userService.isLogined(id);
    }

}