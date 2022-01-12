package priv.crystallightghost.frscommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import priv.crystallightghost.frscommunity.until.IdWorker;

/**
 * @Author CrystalLightGhost
 * @Date 2020/9/11 16:32
 * @Version 1.0
 * @Descriotion
 */
@SpringBootApplication(scanBasePackages ="priv.crystallightghost.frscommunity")
@EntityScan("priv.crystallightghost.frscommunity")
public class UserApplication {
    public static void main(String[] args) {
            SpringApplication.run(UserApplication.class, args);
    }
    @Bean
    public IdWorker idWorkker() {
        return new IdWorker(1, 1);
    }
}
