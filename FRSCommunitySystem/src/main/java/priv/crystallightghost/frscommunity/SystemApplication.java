package priv.crystallightghost.frscommunity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import priv.crystallightghost.frscommunity.until.IdWorker;
import redis.clients.jedis.Jedis;

/**
 * @Author CrystalLightGhost
 * @Date 2020/9/11 16:32
 * @Version 1.0
 * @Descriotion
 */
@SpringBootApplication(scanBasePackages = "priv.crystallightghost.frscommunity")
@EntityScan("priv.crystallightghost.frscommunity")
public class SystemApplication {


    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

    @Bean
    public IdWorker idWorkker() {
        return new IdWorker(1, 1);
    }

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public Jedis jedis() {

        Jedis jedis = new Jedis(host,port);
        jedis.auth(password);
        return jedis;
    }

}
