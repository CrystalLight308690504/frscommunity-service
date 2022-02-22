package priv.crystallightghost.frscommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FrsCommunityEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrsCommunityEurekaApplication.class, args);
    }

}
