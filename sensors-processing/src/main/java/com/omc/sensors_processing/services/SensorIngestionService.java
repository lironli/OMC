package com.omc.sensors_processing.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;
import com.omc.sensors_processing.datatypes.SensorData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SensorIngestionService {
	
	@Autowired
	private MongoTemplate mongoTemplate;	
	
	@KafkaListener(topics = "sensor-topic", groupId = "sensor-group")
	public void consumeSensor(SensorData sensorData) {
		try {
			Query query = new Query(Criteria.where("sensorId").is(sensorData.getSensorId()));

			sensorData.setTimestamp(System.currentTimeMillis());
			int processingHour = getProcessingHour();

	        Update update = new Update()
	                .set("sensorId", sensorData.getSensorId())
	                .set("temperature", sensorData.getTemperature())
	                .set("timestamp", sensorData.getTimestamp())
	                .set("face", sensorData.getFace())
	                .set("status", "active")
	                .set("isFaulty", false)
	                .set("processingHour", processingHour);
	        
	        mongoTemplate.upsert(query, update, "sensor_data"); 
	        log.info("Sensor data ingested for sensor ID: {}", sensorData.getSensorId());
		} catch (MongoException e) {
	        log.error("Failed to upsert sensor data for sensor ID {}: {}", sensorData.getSensorId(), e.getMessage());
		}
	}

	private int getProcessingHour() {
		Instant instant = Instant.ofEpochMilli(System.currentTimeMillis());
		
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC")); 
		
		return localDateTime.getHour();
	}
}
