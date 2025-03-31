package com.omc.sensors_processing.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.omc.sensors_processing.datatypes.ExtendedSensorData;
import com.omc.sensors_processing.interfaces.SensorRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SensorDeactivationService {

	@Autowired
    private SensorRepository sensorRepository;
	
	@Scheduled(fixedRate = 3600000) // every hour
    public void deactivateSensors() {
		long currentTime = System.currentTimeMillis();
		long dayAgo = currentTime - (24 * 60 * 60 * 1000);
		
		try {
			List<ExtendedSensorData> inactiveSensors = sensorRepository.findInactiveSensors(dayAgo); 
		
			for (ExtendedSensorData sensor : inactiveSensors) {
				try {
					sensor.setStatus("inactive");
					sensorRepository.save(sensor);
                    log.info("Sensor {} deactivated successfully", sensor.getSensorData().getSensorId());
				} catch (Exception e) {
                    log.error("Failed to deactivate sensor {}: {}", sensor.getSensorData().getSensorId(), e.getMessage(), e);
				}
			}
		} catch (Exception e) {
            log.error("Error retrieving inactive sensors for deactivation: {}", e.getMessage(), e);
		}
	}
}