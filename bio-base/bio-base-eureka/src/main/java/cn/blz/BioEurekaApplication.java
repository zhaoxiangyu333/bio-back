package cn.blz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author blz
 */
@SpringBootApplication
@EnableEurekaServer
public class BioEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(BioEurekaApplication.class, args);
    }
}
