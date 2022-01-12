package priv.crystallightghost.frscommunity.shiro.realm;


import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import priv.crystallightghost.frscommunity.pojo.system.User;
import priv.crystallightghost.frscommunity.respond.ProfileResult;
import priv.crystallightghost.frscommunity.service.PermissionService;
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

    @Autowired
    private PermissionService permissionService;

    //认证方法
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("AuthenticationInfo...........");
        //1.获取用户的手机号和密码
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String phone = upToken.getUsername();
        String password = new String( upToken.getPassword());
        //2.判断用户是否存在，
        boolean isExited = userService.verifyByPhone(phone);
        if(!isExited ) {// 不存在
            return null;
        }else {
            User user = userService.findUserByPhone(phone);
            if (!user.getPassword().equals(password)){
                return null;
            }
            //4.构造安全数据并返回（安全数据：用户基本数据，权限信息 ）
            ProfileResult result = new ProfileResult(user);
            //构造方法：安全数据，密码，realm域名
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(result,user.getPassword(),this.getName());
            return info;
        }

    }
}
