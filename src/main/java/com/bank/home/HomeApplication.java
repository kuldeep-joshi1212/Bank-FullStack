package com.bank.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class HomeApplication {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(HomeApplication.class, args);
	}
	
}
