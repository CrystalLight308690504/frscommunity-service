package priv.crystallightghost.frscommunity.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;
import priv.crystallightghost.frscommunity.respond.ProfileResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    private String userId;


    //使用shiro获取
    @ModelAttribute
    public void setResAnReq(HttpServletRequest request,HttpServletResponse response) {
        this.request = request;
        this.response = response;
        //获取session中的安全数据
        Subject subject = SecurityUtils.getSubject();
        //1.subject获取所有的安全数据集合
        PrincipalCollection principals = subject.getPrincipals();
        if(principals != null && !principals.isEmpty()){
            //2.获取安全数据
            ProfileResult result = (ProfileResult)principals.getPrimaryPrincipal();
            this.userId = result.getUserId();
        }
    }

}
