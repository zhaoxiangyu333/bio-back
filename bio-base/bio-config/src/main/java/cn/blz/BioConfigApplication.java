package cn.blz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author blz
 */
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class BioConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(BioConfigApplication.class, args);
    }
}
