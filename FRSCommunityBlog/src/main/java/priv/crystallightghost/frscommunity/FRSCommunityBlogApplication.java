package priv.crystallightghost.frscommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import priv.crystallightghost.frscommunity.until.FRSCIdWorker;

@SpringBootApplication(scanBasePackages = "priv.crystallightghost.frscommunity")
@EntityScan("priv.crystallightghost.frscommunity")
public class FRSCommunityBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(FRSCommunityBlogApplication.class, args);
    }
    @Bean
    public FRSCIdWorker idWorker() {
        return new FRSCIdWorker(1, 1);
    }
}
