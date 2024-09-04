package com.loadbalancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = {
		"com.loadbalancer.web.rest",
		"com.loadbalancer.service",
		"com.loadbalancer.config"})
public class LoadbalancerApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LoadbalancerApplication.class, args);
	}

}
