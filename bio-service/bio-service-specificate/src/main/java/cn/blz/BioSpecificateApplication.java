package cn.blz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author blz
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("cn.blz.dao")
public class BioSpecificateApplication {
	public static void main(String[] args) {
		SpringApplication.run(BioSpecificateApplication.class, args);
	}

}
