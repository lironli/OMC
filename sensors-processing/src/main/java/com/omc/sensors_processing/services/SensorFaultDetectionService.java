package com.omc.sensors_processing.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.omc.sensors_processing.datatypes.ExtendedSensorData;
import com.omc.sensors_processing.datatypes.Face;
import com.omc.sensors_processing.datatypes.SensorData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SensorFaultDetectionService {
	
	@Autowired
    private MongoTemplate mongoTemplate;
	
	private static final double MALFUNCTION_THRESHOLD = 0.2; // 20%
	
	@Scheduled(fixedRate = 3600000)
	public void alertSensorFault() {
		for (Face face : Face.values()) {
			try {
				Query query = new Query(Criteria.where("sensorData.face").is(face).and("status").is("active"));

				List<ExtendedSensorData> sensors = mongoTemplate.find(query, ExtendedSensorData.class);
							
				if (sensors.size() < 2) continue;
				
				double averageTemperature = calculateAverageTemperature(sensors);
				for (ExtendedSensorData sensor : sensors) {
					try{
						double deviation = Math.abs(sensor.getSensorData().getTemperature() - averageTemperature) / averageTemperature;
					
						if (deviation > MALFUNCTION_THRESHOLD) {
							sensor.setFaulty(true);
						    Update update = new Update().set("isFaulty", true);
						    mongoTemplate.updateFirst(query, update, ExtendedSensorData.class);
							log.warn("Malfunction detected for sensor: {} on face {} - Temperature {} deviates more than 20% from average {}",
	                                sensor.getSensorData().getSensorId(),
	                                sensor.getSensorData().getFace(),
	                                sensor.getSensorData().getTemperature(),
	                                averageTemperature);
						} else {
							sensor.setFaulty(false);
						    Update update = new Update().set("isFaulty", false);
						    mongoTemplate.updateFirst(query, update, ExtendedSensorData.class);
						}
					} catch (ArithmeticException | NullPointerException e) {
						log.error("Error calculating deviation for sensor {} on face {}: {}", 
	                              sensor.getSensorData().getSensorId(),
	                              face, 
	                              e.getMessage(), e);
					}
				}
			} catch (Exception e) {
				log.error("Error fetching sensors for face {}: {}", face, e.getMessage(), e);
			}
						
		}
	}

	private double calculateAverageTemperature(List<ExtendedSensorData> sensors) {
		try {
			return sensors.stream().
					mapToDouble(sensor -> sensor.getSensorData().getTemperature()).
					average().
					orElse(0.0);
		} catch (Exception e) {
			log.error("Error calculating average temperature: {}", e.getMessage(), e);
	        return 0.0;
		}
	}
	
	public boolean isFaultyTemperature(SensorData sensorData) {
	    return sensorData.getTemperature() > MALFUNCTION_THRESHOLD;
	}

}
