package priv.crystallightghost.frscommunity.shiro;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import priv.crystallightghost.frscommunity.shiro.realm.BaseRealm;
import priv.crystallightghost.frscommunity.shiro.realm.UserRealm;
import priv.crystallightghost.frscommunity.shiro.session.UserSessionManager;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    //1.创建realm
    @Bean
    public BaseRealm getRealm() {
        return new UserRealm();
    }

    //2.创建安全管理器
    @Bean
    public SecurityManager getSecurityManager(BaseRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);

        //将自定义的会话管理器注册到安全管理器中
        securityManager.setSessionManager(sessionManager());
        //将自定义的redis缓存管理器注册到安全管理器中
        securityManager.setCacheManager(cacheManager());

        return securityManager;
    }

    //3.配置shiro的过滤器工厂

    /**
     * 再web程序中，shiro进行权限控制全部是通过一组过滤器集合进行控制
     *
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        //1.创建过滤器工厂
        ShiroFilterFactoryBean filterFactory = new ShiroFilterFactoryBean();
        //2.设置安全管理器
        filterFactory.setSecurityManager(securityManager);
        //3.通用配置（跳转登录页面，未授权跳转的页面）
        filterFactory.setLoginUrl("/autherror?code=1");//跳转url地址
        filterFactory.setUnauthorizedUrl("/autherror?code=2");//未授权的url
        //4.设置过滤器集合
        Map<String,String> filterMap = new LinkedHashMap<>();
        //anon -- 匿名访问
        filterMap.put("/user/login","anon");
        filterMap.put("/user/register","anon");
        filterMap.put("/user/isLogined/**","anon");
        filterMap.put("/user/modifyUserPasswordByPhoneNumber","anon");
        // 需要登陆权限 这个必须放在最后面
        filterMap.put("/user/**","authc");
        //注册
        //perms -- 具有某中权限 (使用注解配置授权)
        filterFactory.setFilterChainDefinitionMap(filterMap);
        return filterFactory;
    }


    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;

    /**
     * 1.redis的控制器，操作redis
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * 2.sessionDao
     */
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setRedisManager(redisManager());
        sessionDAO.setExpire(30*24*60*60);
        return sessionDAO;
    }

    /**
     * 3.会话管理器
     */
    public DefaultWebSessionManager sessionManager() {
        UserSessionManager sessionManager = new UserSessionManager();
        sessionManager.setGlobalSessionTimeout(30*24*60*60*1000L);
        sessionManager.setSessionDAO(redisSessionDAO());
        //禁用cookie
        sessionManager.setSessionIdCookieEnabled(false);
        //禁用url重写   url;jsessionid=id
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    /**
     * 4.缓存管理器
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }




    //开启对shior注解的支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
