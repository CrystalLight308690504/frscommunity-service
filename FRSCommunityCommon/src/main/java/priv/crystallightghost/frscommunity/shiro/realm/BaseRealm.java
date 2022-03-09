package priv.crystallightghost.frscommunity.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import priv.crystallightghost.frscommunity.respond.ProfileResult;

import java.util.HashSet;
import java.util.Set;

//公共的realm：获取安全数据，构造权限信息
public class BaseRealm extends AuthorizingRealm {

    public void setName(String name) {
        super.setName("BaseRealm");
    }
    //授权方法
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("doGetAuthorizationInfo...........");
        //1.获取安全数据
        ProfileResult result = (ProfileResult) principalCollection.getPrimaryPrincipal();
        //2.获取权限信息
//        Set<String> apisPerms = (Set<String>) result.getPermission().get("apis");
        String role = (String) result.getPermission().get("role");
        Set<String> roles = new HashSet<>();
        roles.add(role);
        //3.构造权限数据，返回值
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 将用户要验证使用的权限信息传入session
        info.setRoles(roles);
        return info;
    }

    //认证方法
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
