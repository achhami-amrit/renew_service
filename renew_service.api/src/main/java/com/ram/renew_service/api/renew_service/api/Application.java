package com.ram.renew_service.api.renew_service.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication


@EnableTransactionManagement

 @EntityScan("com.ram.renew_service.entity")

 @ComponentScan("com.ram.renew_service")

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
