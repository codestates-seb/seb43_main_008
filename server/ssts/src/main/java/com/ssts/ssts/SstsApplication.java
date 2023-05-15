package com.ssts.ssts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SstsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SstsApplication.class, args);
	}

}
