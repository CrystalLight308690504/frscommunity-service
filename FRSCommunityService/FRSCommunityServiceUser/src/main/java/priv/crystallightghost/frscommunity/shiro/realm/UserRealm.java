package priv.crystallightghost.frscommunity.shiro.realm;


import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import priv.crystallightghost.frscommunity.entity.user.User;
import priv.crystallightghost.frscommunity.respond.ProfileResult;
import priv.crystallightghost.frscommunity.service.UserService;

/**
 * @Author CrystalLightGhost
 * @Date 2020/9/11 19:42
 * @Version 1.0
 * @Descriotion
 */
public class UserRealm extends BaseRealm {
    @Autowired
    private UserService userService;

    //认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //1.获取用户的手机号和密码
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String loginIdentity = upToken.getUsername();
        String password = new String(upToken.getPassword());

        //2.判断用户是否存在，
        // 默认用用户名登陆
        User user = userService.findUserByUserName(loginIdentity);
        if (StringUtils.isEmpty(user)) {// 手机号登陆
            user = userService.findUserByPhoneNum(loginIdentity);
        }
        if (StringUtils.isEmpty(user) || !user.getPassword().equals(password)) {
            return null;
        }

        //4.构造安全数据并返回（安全数据：用户基本数据，权限信息 ）
        ProfileResult result = new ProfileResult(user);
        //构造方法：安全数据，密码，realm域名
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(result, user.getPassword(), this.getName());
        return info;
    }
}
