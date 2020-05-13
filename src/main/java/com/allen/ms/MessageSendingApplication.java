package com.allen.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
// 要给定包路径，否则对jar里的FeignClient扫描不到
@EnableFeignClients(basePackages = "com.allen")
// 要给定包路径，否则对jar里的Bean扫描不到
@ComponentScan(basePackages = "com.allen")
public class MessageSendingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageSendingApplication.class, args);
	}

}
