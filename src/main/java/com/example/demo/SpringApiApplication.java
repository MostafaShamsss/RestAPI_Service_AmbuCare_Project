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

//			repository.save(new Driver("Ahmed","123 ss","+201011575147","6 minutes",7.0f,9.0f));
//			repository.insertData("Omar","259 bas","+201115483248","5 minutes",120.0f,125.0f);
//			repository.insertData("Shehab","259 bas","+201115483248","5 minutes",120.0f,125.0f);

			log.info("Drivers found with findAll():");
			log.info("-------------------------------");
			for (Driver driver : repository.findAll()) {
				log.info(driver.toString());
			}
			log.info("");
		};
	}


}
