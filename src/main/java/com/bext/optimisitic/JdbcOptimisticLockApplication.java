package com.bext.optimisitic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcOptimisticLockApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdbcOptimisticLockApplication.class, args);
	}

}
