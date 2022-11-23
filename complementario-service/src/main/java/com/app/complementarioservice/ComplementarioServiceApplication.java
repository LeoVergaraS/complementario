package com.app.complementarioservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ComplementarioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComplementarioServiceApplication.class, args);
	}

}
