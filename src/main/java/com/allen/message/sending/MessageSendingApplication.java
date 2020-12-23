package com.allen.message.sending;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableEurekaClient
// 要给定包路径，否则对jar里的FeignClient扫描不到
@EnableFeignClients(basePackages = "com.allen")
@EnableHystrix
@EnableCircuitBreaker
// 要给定包路径，否则对jar里的Bean扫描不到
@ComponentScan(basePackages = "com.allen")
public class MessageSendingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageSendingApplication.class, args);
	}

}
