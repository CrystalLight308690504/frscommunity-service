package priv.crystallightghost.frscommunity;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import priv.crystallightghost.frscommunity.until.FRSCIdWorker;
import redis.clients.jedis.Jedis;

/**
 * @Author CrystalLightGhost
 * @Date 2020/9/11 16:32
 * @Version 1.0
 * @Descriotion
 */
@SpringBootApplication(scanBasePackages = "priv.crystallightghost.frscommunity")
@EntityScan("priv.crystallightghost.frscommunity")
@EnableEurekaClient
@EnableCircuitBreaker
public class SystemApplication {


    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");//访问路径
        registrationBean.setName("hystrix.stream");
        return registrationBean;
    }

    @Bean
    public FRSCIdWorker idWorkker() {
        return new FRSCIdWorker(1, 1);
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
