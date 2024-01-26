package com.threethings.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.Generated;

@Generated
@SpringBootApplication
@EnableJpaAuditing
public class ThreethingsApplication {
	public static void main(String[] args) {
		SpringApplication.run(ThreethingsApplication.class, args);
	}
}
