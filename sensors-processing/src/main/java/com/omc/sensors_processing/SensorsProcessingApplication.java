package com.omc.sensors_processing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SensorsProcessingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorsProcessingApplication.class, args);
	}

}
