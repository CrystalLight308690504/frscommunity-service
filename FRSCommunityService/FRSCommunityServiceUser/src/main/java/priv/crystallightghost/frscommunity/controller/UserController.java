package priv.crystallightghost.frscommunity.controller;


import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import priv.crystallightghost.frscommunity.entity.user.User;
import priv.crystallightghost.frscommunity.entity.user.UserFollower;
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
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/followUser", method = RequestMethod.POST)
    public Result followUser(@RequestBody UserFollower userFollower) {
        return userService.followUser(userFollower);
    }

    @RequestMapping(value = "/cancelFollowUser/{userId}/{userFollowedId}", method = RequestMethod.DELETE)
    public Result cancelFollowUser(@PathVariable("userId") Long userId, @PathVariable("userFollowedId") Long userFollowedId) {
        return userService.cancelFollowUser(userId, userFollowedId);
    }

    @RequestMapping(value = "/existFollower/{userId}/{userFollowedId}", method = RequestMethod.GET)
    public Result existFollower(@PathVariable("userId") Long userId, @PathVariable("userFollowedId") Long userFollowedId) {
        return userService.existFollower(userId, userFollowedId);
    }

    /**
     * 获取粉丝数量
     * @param userId
     * @return
     */
    @RequestMapping(value = "/countFollower/{userId}", method = RequestMethod.GET)
    public Result countFollower(@PathVariable("userId") Long userId) {
        return userService.countFollower(userId);
    }

    /**
     * 获取用户关注用户的数量
     * @param userId
     * @return
     */
    @RequestMapping(value = "/countUserFollowCount/{userId}", method = RequestMethod.GET)
    public Result countUserFollowCount(@PathVariable("userId") Long userId) {
        return userService.countUserFollowCount(userId);
    }
 /**
     * 获取用户关注用户的数量
     * @param userId
     * @return
     */
 @RequiresRoles(value = {"superAdm","userAdm"},logical = Logical.OR)
 @RequestMapping(value = "/changUserRole/{userId}/{roleId}", method = RequestMethod.POST)
    public Result changUserRole(@PathVariable("userId") Long userId, @PathVariable("roleId") Long roleId) {
        return userService.changUserRole(userId, roleId);
    }

    /**
     * 注销登入
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    public Result logout(HttpServletRequest request) {
        String id = request.getHeader("Authorization");
        if (StringUtils.isEmpty(id)) {
            return new Result(ResultCode.USERNOEXITED);
        }
        id = id.replaceAll("FRSC", "shiro:session:");
        return userService.logout(id);
    }

    @RequestMapping(value = "/findUserByNameKey/{userName}/{pagerIndex}", method = RequestMethod.GET)
    public Result findUserByName(@PathVariable("userName") String userName, @PathVariable("pagerIndex") int pagerIndex) {
        return userService.findUserByName(userName, pagerIndex);
    }

    @RequiresRoles(value = {"superAdm","userAdm"},logical = Logical.OR)
    @RequestMapping(value = "/findAllUser/{pagerIndex}", method = RequestMethod.GET)
    public Result findAllUser( @PathVariable("pagerIndex") int pagerIndex) {
        return userService.findAllUser(pagerIndex);
    }

    @RequestMapping(value = "/findUserByUserId/{userId}", method = RequestMethod.GET)
    public Result findUserByUserId(@PathVariable("userId") long userId) {
        return userService.findUserByUserId(userId);
    }

    @RequestMapping(value = "/findUserFollowed/{userId}/{pagerIndex}", method = RequestMethod.GET)
    public Result findUserFollowed(@PathVariable("userId") long userId, @PathVariable("pagerIndex") int pagerIndex) {
        return userService.findUserFollowed(userId, pagerIndex);
    }

    @RequestMapping(value = "/findUserFan/{userId}/{pagerIndex}", method = RequestMethod.GET)
    public Result findUserFan(@PathVariable("userId") long userId, @PathVariable("pagerIndex") int pagerIndex) {
        return userService.findUserFan(userId, pagerIndex);
    }

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody Map<String, String> loginMap) {

        String loginIdenty = null;
        System.out.println("login...........");
        String userName = loginMap.get("userName");
        String phoneNumber = loginMap.get("phoneNumber");
        if (!StringUtils.isEmpty(userName)) {
            loginIdenty = userName;
        } else if (!StringUtils.isEmpty(phoneNumber)) { // 手机号登陆
            loginIdenty = phoneNumber;
        } else if (StringUtils.isEmpty(userName)) {
            return new Result(ResultCode.NOUSERNAMEINPUTED);
        } else if (StringUtils.isEmpty(phoneNumber)) {
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

    @RequestMapping(value = "/modifyUserProfile", method = RequestMethod.PUT)
    public Result modifyUserProfile(@RequestBody User user) {
        return userService.modifyUserProfile(user);
    }

    @RequestMapping(value = "/modifyUserGender", method = RequestMethod.PUT)
    public Result modifyUserInformation(@RequestBody User user) {
        return userService.modifyUserGender(user);
    }

    @RequestMapping(value = "/modifyUserDescription", method = RequestMethod.PUT)
    public Result modifyUserDescription(@RequestBody User user) {
        return userService.modifyUserDescription(user);
    }

    /**
     * 用户注册
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @RequestMapping(value = "/verifyUserName/{userName}", method = RequestMethod.GET)
    public Result verifyUserNameExited(@PathVariable String userName) {

        return userService.verifyUserNameExited(userName);
    }

    @RequestMapping(value = "/verifyEmail/{email}", method = RequestMethod.GET)
    public Result verifyEmailExited(@PathVariable String email) {
        System.out.printf(email);
        return userService.verifyEmailExited(email);
    }
    @RequestMapping(value = "/isLogined/{sessionId}", method = RequestMethod.GET)
    public Result isLogined(@PathVariable("sessionId") String sessionId) {
        sessionId = sessionId.replaceAll("FRSC", "shiro:session:");
        System.out.printf("============session===========");
        return userService.isLogined(sessionId);
    }
}