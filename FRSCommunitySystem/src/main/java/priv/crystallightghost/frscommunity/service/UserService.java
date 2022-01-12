package priv.crystallightghost.frscommunity.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import priv.crystallightghost.frscommunity.dao.UserDao;
import priv.crystallightghost.frscommunity.dao.UserInformationDao;
import priv.crystallightghost.frscommunity.pojo.system.User;
import priv.crystallightghost.frscommunity.pojo.system.UserInformation;
import priv.crystallightghost.frscommunity.respond.Result;
import priv.crystallightghost.frscommunity.respond.ResultCode;
import priv.crystallightghost.frscommunity.until.IdWorker;

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
    UserInformationDao userInformationDao;
    @Autowired
    IdWorker idWorker;



    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    public User findUserByPhone(String userName) {
        return userDao.findUserByPhone(userName);
    }


    public Result login(String userName, String password) {
        try {
            //1.构造登录令牌 UsernamePasswordToken
            //加密密码
            password = new Md5Hash(password, password + "FRS", 2).toString();  //1.密码，盐，加密次数
            UsernamePasswordToken upToken = new UsernamePasswordToken(userName, password);

            //2.获取subject
            Subject subject = SecurityUtils.getSubject();
            //3.调用login方法，进入UserRealm完成认证
            subject.login(upToken);
            //4.获取sessionId
            String sessionId = (String) subject.getSession().getId();
            //5.构造返回结果
            return new Result(ResultCode.SUCCESS, sessionId);
        } catch (Exception e) {
            return new Result(ResultCode.MOBILEORPASSWORDERROR);
        }
    }
    public boolean verifyByPhone(String phone) {
        User user = userDao.findUserByPhone(phone);
        if (StringUtils.isEmpty(user)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean verifyByUserName(String userName) {
        User user = userDao.findUserByUserName(userName);
        if (StringUtils.isEmpty(user)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 注册用户
     */
    public Result save(User user) {
        User userInD = null;
        userInD = userDao.findUserByPhone(user.getPhone());
        if (userInD != null ){
            return new Result(ResultCode.FAIL, "该号码注册");
        }

        user.setUserId(idWorker.nextId());
        user.setUserName("FRSC" + idWorker.nextId());
        String password = user.getPassword();
        password = new Md5Hash(password, password + "FRS", 2).toString();  //1.密码，盐，加密次数
        user.setPassword(password);

        UserInformation userInformation = new UserInformation();
        userInformation.setUserInfoId(idWorker.nextId());
        userInformationDao.save(userInformation);

        user.setUserInfo(userInformation);
        userDao.save(user);

        return new Result(ResultCode.SUCCESS);
    }

}
