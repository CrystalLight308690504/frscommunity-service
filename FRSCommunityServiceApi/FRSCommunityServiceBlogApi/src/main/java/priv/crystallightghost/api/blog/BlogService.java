package priv.crystallightghost.api.blog;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @ClassName : UserService
 * @Author : CrystallightAir
 * @Version : 1.0
 * @CreateTime : 2024/2/8 19:27
 * @Description :
 */
@Service
@Component
//@FeignClient:微服务客户端注解,value:指定微服务的名字,这样就可以使Feign客户端直接找到对应的微服务
@FeignClient(value = "FRS-Blog")
public interface BlogService {
}
