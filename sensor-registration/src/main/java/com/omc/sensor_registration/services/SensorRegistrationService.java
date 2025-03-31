package com.omc.sensor_registration.services;

import org.apache.kafka.common.KafkaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.omc.sensor_registration.datatypes.SensorData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SensorRegistrationService {

	@Autowired
	private KafkaTemplate<String, SensorData> kafkaTemplate;
	
	public void register(SensorData sensorData) {
		try {
			kafkaTemplate.send("sensor-topic", sensorData);
		} catch (KafkaException e) {
			log.error("Failed to produce data to Kafka");
			log.error("logged Kafka error: " + e);
	        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Failed to produce data to Kafka", e);
		} catch (Exception e) {
			log.error("An unexpected error occurred");
	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
		}
	}
}
