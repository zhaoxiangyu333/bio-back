package cn.blz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import java.util.ArrayList;
import java.util.List;

@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class BioGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BioGatewayApplication.class, args);
	}

}
