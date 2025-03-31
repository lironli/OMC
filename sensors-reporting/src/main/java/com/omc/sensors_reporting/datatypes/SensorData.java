package com.omc.sensors_reporting.datatypes;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "sensor_data")
public class SensorData {
	
	
	private int sensorId;
	private String face;
	private double temperature;
	private long timestamp;
	private boolean isFaulty;
	private int processingHour;
	private String status;
	
}
