package priv.crystallightghost.frscommunity.feignIo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import priv.crystallightghost.frscommunity.respond.Result;

import java.util.Map;

/**
 * @ClassName : FeignSystemClient
 * @Author : CrystallightAir
 * @Version : 1.0
 * @CreateTime : 2023/3/26 18:16
 * @Description :
 */
@FeignClient("FRS-USER")
public interface FeignSystemClient {
    @RequestMapping(value = "user/isLogined/{sessionId}", method = RequestMethod.GET)
    public Result isLogined(@PathVariable("sessionId") String sessionId);
}
