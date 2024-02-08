package priv.crystallightghost.frscommunity.respond;

import lombok.Getter;
import lombok.Setter;
import org.crazycake.shiro.AuthCachePrincipal;
import priv.crystallightghost.frscommunity.entity.user.User;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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

        permission.put("role", user.getRole().getCode());
       /* for (Role role : roles) {
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
        }*/

    }

    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
