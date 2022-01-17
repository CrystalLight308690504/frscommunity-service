package priv.crystallightghost.frscommunity.respond;

import lombok.Getter;
import lombok.Setter;
import org.crazycake.shiro.AuthCachePrincipal;
import priv.crystallightghost.frscommunity.pojo.system.Permission;
import priv.crystallightghost.frscommunity.pojo.system.Role;
import priv.crystallightghost.frscommunity.pojo.system.User;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Setter
@Getter
public class ProfileResult implements Serializable, AuthCachePrincipal {
    private String userId;
    private String mobile;
    private String username;
    private String email;
    private Map<String,Object> permission = new HashMap<>();

    public ProfileResult(User user) {
        this.mobile = user.getPhoneNumber();
        this.username = user.getUserName();
        this.userId = user.getEmail();
        Set<Role> roles = user.getRoles();
        Set<String> menus = new HashSet<>();
        Set<String> points = new HashSet<>();
        Set<String> apis = new HashSet<>();
        Set<String> role1 = new HashSet<>();
        for (Role role : roles) {
            role1.add(role.getCode());
            Set<Permission> perms = role.getPermissions();
            for (Permission perm : perms) {
                String code = perm.getCode();
                if(perm.getType() == 1) {
                    menus.add(code);
                }else if(perm.getType() == 2) {
                    points.add(code);
                }else {
                    apis.add(code);
                }
            }
        }

        this.permission.put("menus",menus);
        this.permission.put("points",points);
        this.permission.put("apis",apis);
        this.permission.put("roles",role1);
    }

    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
