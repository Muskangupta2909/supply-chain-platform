package com.tracking.tracking_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
    Main Application Class
*/
@SpringBootApplication
@EnableJpaAuditing
public class TrackingServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(TrackingServiceApplication.class, args);
	}
}