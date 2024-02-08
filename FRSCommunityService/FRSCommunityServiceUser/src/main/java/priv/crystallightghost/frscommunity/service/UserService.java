package priv.crystallightghost.frscommunity.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import priv.crystallightghost.frscommunity.dao.RoleDao;
import priv.crystallightghost.frscommunity.dao.UserDao;
import priv.crystallightghost.frscommunity.dao.UserFollowerDao;
import priv.crystallightghost.frscommunity.dao.UserFollowerEntityDao;
import priv.crystallightghost.frscommunity.entity.user.Role;
import priv.crystallightghost.frscommunity.entity.user.User;
import priv.crystallightghost.frscommunity.entity.user.UserFollower;
import priv.crystallightghost.frscommunity.entity.user.UserFollowerEntity;
import priv.crystallightghost.frscommunity.respond.PagerResult;
import priv.crystallightghost.frscommunity.respond.Result;
import priv.crystallightghost.frscommunity.respond.ResultCode;
import priv.crystallightghost.frscommunity.until.FRSCIdWorker;
import priv.crystallightghost.frscommunity.until.FRSCPasswordMd5Util;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Optional;

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
    UserFollowerDao userFollowerDao;
    @Autowired
    UserFollowerEntityDao userFollowerEntityDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    FRSCIdWorker FRSCIdWorker;
    @Autowired
    Jedis jedis;
    @Autowired
    HttpServletRequest request;

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

            // 在数据库记录当前sessionid和登录时间
            user.setSessionId(sessionId);
            user.setLoginTime(new Timestamp(System.currentTimeMillis()));
            userDao.save(user);

            // 将用户的sessionId于用户id相影射
            jedis.set(user.getSessionId() + "", user.getUserId() + "");
            jedis.expire(user.getSessionId() + "", 15 * 24 * 60 * 60);

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
        } else {
            return new Result(ResultCode.USER_LOGIN_EXPIRED);
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

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user.setCreatedTime(timestamp);
        user.setUserId(FRSCIdWorker.nextId());
        user.setUserName("FRSC" + FRSCIdWorker.nextId());
        String password = user.getPassword();
        password = FRSCPasswordMd5Util.getPasswordCoded(password);
        user.setPassword(password);
        Role role = new Role();
        role.setRoleId(1L);
        user.setRole(role);
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
            return Result.SUCCESS(true);
        } else {
            return Result.SUCCESS(false);
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
        if (StringUtils.isEmpty(oldPassword)) {
            return new Result(123, "请输入旧密码", false);

        }
        oldPassword = FRSCPasswordMd5Util.getPasswordCoded(oldPassword);
        User userD = this.userDao.findUserByUserId(user.getUserId());
        if (null != userD && userD.getPassword().equals(oldPassword)) {
            String passwordCoded = FRSCPasswordMd5Util.getPasswordCoded(user.getPassword());
            userD.setPassword(passwordCoded);
            userDao.save(userD);
            return Result.SUCCESS();
        } else {
            return new Result(123, "旧密码错误", false);
        }

    }

    public Result modifyUserProfile(User user) {
        User userD = this.userDao.findUserByUserId(user.getUserId());
        if (StringUtils.isEmpty(userD)) {
            return new Result(ResultCode.USERNOEXITED);
        } else {
            userD.setProfile(user.getProfile());
            userDao.save(userD);
            return Result.SUCCESS();
        }
    }

    public Result modifyUserDescription(User user) {
        User userD = this.userDao.findUserByUserId(user.getUserId());
        if (StringUtils.isEmpty(userD)) {
            return new Result(ResultCode.USERNOEXITED);
        } else {
            userD.setDescription(user.getDescription());
            userDao.save(userD);
            return Result.SUCCESS();
        }
    }

    public Result modifyUserGender(User user) {
        User userD = this.userDao.findUserByUserId(user.getUserId());
        if (StringUtils.isEmpty(userD)) {
            return new Result(ResultCode.USERNOEXITED);
        } else {
            userD.setGender(user.getGender());
            userDao.save(userD);
            return Result.SUCCESS();
        }
    }

    public Result findUserByName(String userName, int pagerIndex) {

        Sort sort = Sort.by("createdTime").descending();
        Slice<User> usersSlice = userDao.findUsersByUserNameIsContaining(userName, PageRequest.of(pagerIndex, 10, sort));
        PagerResult pagerResult = new PagerResult(usersSlice.getContent(), usersSlice.hasNext());
        return Result.SUCCESS(pagerResult);
    }

    public Result followUser(UserFollower userFollower) {
        UserFollower userFollowerData = userFollowerDao.findByUserIdAndUserFollowedId(userFollower.getUserId(), userFollower.getUserFollowedId());
        if (null != userFollowerData) {
            return Result.ERROR("已经注");
        }
        userFollower.setFollowerId(FRSCIdWorker.nextId());
        userFollower.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        userFollowerDao.save(userFollower);
        return Result.SUCCESS();
    }

    public Result existFollower(Long userId, Long userFollowedId) {
        boolean exists = userFollowerDao.existsByUserIdAndUserFollowedId(userId, userFollowedId);
        return Result.SUCCESS(exists);
    }

    public Result countFollower(Long userId) {
        long count = userFollowerDao.countByUserFollowedId(userId);
        return Result.SUCCESS(count);
    }

    public Result cancelFollowUser(long userId, long userFollowedId) {
        UserFollower userFollower = userFollowerDao.findByUserIdAndUserFollowedId(userId, userFollowedId);
        if (null != userFollower) {
            userFollowerDao.delete(userFollower);
            return Result.SUCCESS();
        } else {
            return Result.ERROR("未关注");
        }
    }

    public Result countUserFollowCount(Long userId) {
        long count = userFollowerDao.countByUserId(userId);
        return Result.SUCCESS(count);
    }

    public Result findUserByUserId(long userId) {
        Optional<User> byId = userDao.findById(userId);
        if(byId.isEmpty()) {
            return Result.ERROR("不存在此用户");
        }else {
            return Result.SUCCESS(byId.get());
        }
    }

    public Result findUserFollowed(long userId, int pagerIndex) {
        User user = new User();
        user.setUserId(userId);
        Sort sort = Sort.by("createdTime").descending();
        Slice<UserFollowerEntity> userSlice = userFollowerEntityDao.findByUser(user, PageRequest.of(pagerIndex, 10, sort));
        PagerResult pagerResult = new PagerResult(userSlice.getContent(), userSlice.hasNext());
        return Result.SUCCESS(pagerResult);
    }

    public Result findUserFan(long userId, int pagerIndex) {
        User user = new User();
        user.setUserId(userId);
        Sort sort = Sort.by("createdTime").descending();
        Slice<UserFollowerEntity> userSlice = userFollowerEntityDao.findByUserFollowed(user, PageRequest.of(pagerIndex, 10, sort));
        PagerResult pagerResult = new PagerResult(userSlice.getContent(), userSlice.hasNext());
        return Result.SUCCESS(pagerResult);
    }

    public Result findAllUser(int pagerIndex) {
        Sort sort = Sort.by("createdTime").descending();
        Page<User> users = userDao.findAll(PageRequest.of(pagerIndex, 10, sort));
        PagerResult pagerResult = new PagerResult(users.getContent(), users.hasNext());
        return Result.SUCCESS(pagerResult);
    }

    public Result changUserRole(Long userId, Long roleId) {
        Optional<User> userOptional = userDao.findById(userId);
        if (userOptional.isPresent()) {
            Optional<Role> byId = roleDao.findById(roleId);
            if (byId.isPresent()){
                User user = userOptional.get();
                user.setRole(byId.get());
                userDao.save(user);
                return Result.SUCCESS();
            }else {
                return Result.ERROR("不存在此角色");
            }
        }else {
            return Result.ERROR("不存在此用户");
        }

    }
}
