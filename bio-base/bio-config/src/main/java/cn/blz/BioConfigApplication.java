package cn.blz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author blz
 */
@EnableEurekaClient
@EnableConfigServer
@SpringBootApplication
public class BioConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(BioConfigApplication.class, args);
    }
}
