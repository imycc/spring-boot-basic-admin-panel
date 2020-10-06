package com.imyc.SBAP;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbapApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbapApplication.class, args);
		
		Flyway flyway = Flyway.configure().dataSource("jdbc:mysql://localhost:8889/sbap", "root", "root").load();
		flyway.migrate();
	}

}
