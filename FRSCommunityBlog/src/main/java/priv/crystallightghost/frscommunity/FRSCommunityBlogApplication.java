package priv.crystallightghost.frscommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import priv.crystallightghost.frscommunity.until.FRSCIdWorker;

@SpringBootApplication(scanBasePackages = "priv.crystallightghost.frscommunity")
@EntityScan("priv.crystallightghost.frscommunity")
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
public class FRSCommunityBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(FRSCommunityBlogApplication.class, args);
    }
    @Bean
    public FRSCIdWorker idWorker() {
        return new FRSCIdWorker(1, 1);
    }
}
