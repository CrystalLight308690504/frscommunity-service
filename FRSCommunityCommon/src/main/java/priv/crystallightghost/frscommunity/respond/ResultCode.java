package priv.crystallightghost.frscommunity.respond;

/**
 * 公共的返回码
 *    返回码code：
 *      成功：10000
 *      失败：10001
 *      未登录：10002
 *      未授权：10003
 *      抛出异常：99999
 */
public enum ResultCode {

    //---系统操作返回码  1xxxx----
    SUCCESS(true,10000,"(●'◡'●)成功"),
    //---系统错误返回码-----
    FAIL(false,10001,"(ಥ﹏ಥ)失败"),
    UNAUTHENTICATED(false,10002,"(ಥ﹏ಥ)您还未登录"),
    UNAUTHORISE(false,10003,"(ಥ﹏ಥ)权限不足"),
    SERVER_ERROR(false,99999,"服务器(ಥ﹏ಥ)跑路了"),

    //---用户操作返回码  2xxxx----
    MOBILEORPASSWORDERROR(false,20001,"(ಥ﹏ಥ)用户名或密码错误"),
    USERNAMEEXITED(false,20002,"(ಥ﹏ಥ)号码已被注册"),
    USERMAILEXITED(false,20003,"(ಥ﹏ಥ)用户邮箱已被绑定"),
    NOUSERNAMEINPUTED(false,20004,"(ಥ﹏ಥ)请输入用户名"),
    NOUSERPHONENUMBERIPUTED(false,20005,"(ಥ﹏ಥ)请输入手机号"),
    USERNOEXITED(false,20006,"(ಥ﹏ಥ)用户不存在"),
    USER_LOGIN_EXPIRED(false,20007,"(ಥ﹏ಥ)登陆已经失效")
    ;



    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    ResultCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean success() {
        return success;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

}
