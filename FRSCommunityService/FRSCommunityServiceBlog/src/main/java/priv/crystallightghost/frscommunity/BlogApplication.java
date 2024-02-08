package priv.crystallightghost.frscommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import priv.crystallightghost.frscommunity.until.FRSCIdWorker;

@SpringBootApplication(scanBasePackages = "priv.crystallightghost.frscommunity")
@EntityScan("priv.crystallightghost.frscommunity")
@EnableEurekaClient
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
    @Bean
    public FRSCIdWorker idWorker() {
        return new FRSCIdWorker(1, 1);
    }
}
