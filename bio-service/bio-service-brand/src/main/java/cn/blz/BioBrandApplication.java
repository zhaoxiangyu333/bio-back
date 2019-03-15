package cn.blz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author blz
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@MapperScan("cn.blz.dao")
public class BioBrandApplication {
    public static void main(String[] args) {
        SpringApplication.run(BioBrandApplication.class, args);
    }

}
