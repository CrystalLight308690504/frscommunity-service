package priv.crystallightghost.frscommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName : FRSCommunityEurekaApllication
 * @Author : CrystallightAir
 * @Version : 1.0
 * @CreateTime : 2023/3/26 16:08
 * @Description :
 */
@SpringBootApplication(scanBasePackages = "priv.crystallightghost.frscommunity")
@EnableEurekaServer //开启 Eureka server,接受其他微服务的注册
public class EurekaApllication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApllication.class, args);
    }

}
