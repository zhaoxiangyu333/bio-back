package cn.blz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author blz
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("cn.blz.dao")
public class BioOrderApplication {
	public static void main(String[] args) {
		SpringApplication.run(BioOrderApplication.class, args);
	}

}
