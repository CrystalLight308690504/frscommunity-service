package priv.crystallightghost.frscommunity.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import priv.crystallightghost.frscommunity.dao.UserDao;
import priv.crystallightghost.frscommunity.pojo.system.User;
import priv.crystallightghost.frscommunity.respond.Result;
import priv.crystallightghost.frscommunity.respond.ResultCode;
import priv.crystallightghost.frscommunity.until.FRSCIdWorker;
import priv.crystallightghost.frscommunity.until.FRSCPasswordMd5Util;
import redis.clients.jedis.Jedis;

/**
 * @Author CrystalLightGhost
 * @Date 2020/9/11 18:29
 * @Version 1.0
 * @Descriotion
 */

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    FRSCIdWorker FRSCIdWorker;

    @Autowired
    Jedis jedis;


    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    public User findUserByPhoneNum(String userName) {
        return userDao.findUserByPhoneNumber(userName);
    }


    public Result login(String loginIdentity, String password) {
        try {
            //1.构造登录令牌 UsernamePasswordToken
            //加密密码
            password = FRSCPasswordMd5Util.getPasswordCoded(password);
            ;  //1.密码，盐，加密次数
            UsernamePasswordToken upToken = new UsernamePasswordToken(loginIdentity, password);

            //2.获取subject
            Subject subject = SecurityUtils.getSubject();
            //3.调用login方法，进入UserRealm完成认证
            subject.login(upToken);
            //4.获取sessionId
            String sessionId = (String) subject.getSession().getId();
            //5.构造返回结果
            User user = findUserByUserName(loginIdentity);
            if (StringUtils.isEmpty(user)) {// 手机号登陆
                user = findUserByPhoneNum(loginIdentity);
            }

            user.setSessionId(sessionId);
            return new Result(ResultCode.SUCCESS, user);
        } catch (Exception e) {
            return new Result(ResultCode.MOBILEORPASSWORDERROR);
        }
    }

    public Result logout(String authorization) {

        String s = jedis.get(authorization);
        if (!StringUtils.isEmpty(s)) {
            jedis.del(authorization);
            return Result.SUCCESS();
        }else {
            return  new Result(ResultCode.USER_LOGIN_EXPIRED);
        }
    }

    public boolean existedByPhoneNum(String phone) {
        User user = userDao.findUserByPhoneNumber(phone);
        if (StringUtils.isEmpty(user)) {
            return false;
        } else {
            return true;
        }
    }

    public Result verifyUserNameExited(String userName) {
        User user = userDao.findUserByUserName(userName);
        if (StringUtils.isEmpty(user)) {
            return new Result(ResultCode.SUCCESS, false);
        } else {
            return new Result(ResultCode.SUCCESS, true);
        }
    }

    /**
     * 注册用户 使用手机号码注册
     */
    public Result registerUser(User user) {
        User userInD = null;
        userInD = userDao.findUserByPhoneNumber(user.getPhoneNumber());
        if (userInD != null) {
            return new Result(ResultCode.USERNAMEEXITED, "");
        }

        user.setUserId(FRSCIdWorker.nextId());
        user.setUserName("FRSC" + FRSCIdWorker.nextId());
        String password = user.getPassword();
        password = FRSCPasswordMd5Util.getPasswordCoded(password);
        user.setPassword(password);
        userDao.save(user);

        return new Result(ResultCode.SUCCESS);
    }

    public Result verifyEmailExited(String email) {
        User user = userDao.findUserByEmail(email);
        if (StringUtils.isEmpty(user)) {
            return new Result(ResultCode.SUCCESS, false);
        } else {
            return new Result(ResultCode.SUCCESS, true);
        }
    }

    public Result modifyUserName(User user) {
        User userDt = userDao.findUserByUserId(user.getUserId());
        if (StringUtils.isEmpty(userDt)) {
            return new Result(ResultCode.USERNOEXITED);
        } else {
            userDt.setUserName(user.getUserName());
            userDao.save(userDt);
            return Result.SUCCESS();
        }
    }

    public Result modifyUserPasswordByPhoneNumber(User user) {
        User userDt = userDao.findUserByUserId(user.getUserId());
        if (StringUtils.isEmpty(userDt)) {
            return new Result(ResultCode.USERNOEXITED);
        } else {
            String password = FRSCPasswordMd5Util.getPasswordCoded(user.getPassword());
            userDt.setPassword(password);
            userDao.save(userDt);
            return Result.SUCCESS();
        }
    }

    public Result isLogined(String id) {
        String s = jedis.get(id);
        if (!StringUtils.isEmpty(s)) {
            return Result.SUCCESS();
        }else {
            return  new Result(ResultCode.USER_LOGIN_EXPIRED);
        }
    }

    public Result modifyUserEmail(User user) {
        User userDt = userDao.findUserByUserId(user.getUserId());
        if (StringUtils.isEmpty(userDt)) {
            return new Result(ResultCode.USERNOEXITED);
        } else {
            userDt.setEmail(user.getEmail());
            userDao.save(userDt);
            return Result.SUCCESS();
        }
    }

    public Result modifyUserPasswordByOldPassword(User user) {
        // 检查旧密码是否是错的
        String oldPassword = user.getOldPassword();
        oldPassword = FRSCPasswordMd5Util.getPasswordCoded(oldPassword);
        User userD = this.userDao.findUserByUserId(user.getUserId());
        if (null != userD && userD.getPassword().equals(oldPassword)) {
            String passwordCoded = FRSCPasswordMd5Util.getPasswordCoded(user.getPassword());
            userD.setPassword(passwordCoded);
            userDao.save(userD);
            return Result.SUCCESS();
        } else {
            return new Result (123, "旧密码错误",false);
        }

    }
}
