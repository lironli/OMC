package com.omc.sensors_processing.datatypes;

import lombok.Data;

@Data
public class SensorData {

	private Integer sensorId;
	
	private long timestamp;
	
	private Face face;
	
	private double temperature;
}
