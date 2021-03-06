package cn.blz;

import cn.blz.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author blz
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class BioSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(BioSearchApplication.class, args);
    }

    @Bean
    public IdWorker idWorkker() {
        return new IdWorker(1, 1);
    }
}
