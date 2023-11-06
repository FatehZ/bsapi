package com.ktxdevelopment.authorms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class AuthorMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorMsApplication.class, args);
	}

}
