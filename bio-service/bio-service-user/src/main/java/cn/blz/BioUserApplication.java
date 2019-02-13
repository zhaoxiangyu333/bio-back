package cn.blz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author blz
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("cn.blz.dao")
public class BioUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(BioUserApplication.class, args);
    }

    /**
     * 哈希密码加密
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
