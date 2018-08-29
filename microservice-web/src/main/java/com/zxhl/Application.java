package com.zxhl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;

@EnableFeignClients(basePackages = "com.zxhl.*")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.zxhl")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ImportResource(locations = {"classpath:context.xml"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
