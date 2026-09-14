package com.cognizant.tradingcompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class TradingCompanyServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(TradingCompanyServiceApplication.class, args);
	}

}
