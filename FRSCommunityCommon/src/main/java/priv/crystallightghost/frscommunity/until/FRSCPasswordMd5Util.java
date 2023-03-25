package priv.crystallightghost.frscommunity.until;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @Date 2022/1/15
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public class FRSCPasswordMd5Util {


    public static String getPasswordCoded(String password) {
        //1.密码，盐，加密次数
        return new Md5Hash(password, password + "FRSC", 2).toString();
    }

}
