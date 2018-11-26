package com.hystrix.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableCircuitBreaker
@EnableEurekaClient
@EnableDiscoveryClient
@EnableHystrixDashboard
@EnableHystrix
@EnableTurbine
@SpringBootApplication
public class Ass4HystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ass4HystrixApplication.class, args);
	}
}
