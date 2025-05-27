package com.example.security.Springsecuritydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.example.security.Springsecuritydemo.entity")
@ComponentScan(basePackages = { "com.example.security.Springsecuritydemo" })
public class SpringsecuritydemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecuritydemoApplication.class, args);
	}

}
