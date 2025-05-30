package com.vacation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class VacationSystemApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacationSystemApiApplication.class, args);
	}

}
