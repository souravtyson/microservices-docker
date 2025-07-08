package com.sourav.kafka.springkafkaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class SpringKafkaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaServiceApplication.class, args);
	}

}
