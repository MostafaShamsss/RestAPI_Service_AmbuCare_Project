package com.example.demo;

import com.example.demo.api.model.Driver;
import com.example.demo.api.repository.DriverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringApiApplication {
	private static final Logger log = LoggerFactory.getLogger(SpringApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringApiApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo2(DriverRepository repository) {
		return (args) -> {

			repository.save(new Driver("Ahmed","123 ss","+201011575147","127 minutes",128.0f,129.0f));

			// fetch all animals
			log.info("Drivers found with findAll():");
			log.info("-------------------------------");
			for (Driver driver : repository.findAll()) {
				log.info(driver.toString());
			}
			log.info("");
		};
	}


}
