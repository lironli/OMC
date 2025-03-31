package com.omc.sensors_processing.datatypes;

import lombok.Data;

@Data
public class ExtendedSensorData {
	private SensorData sensorData;
	private String status;
	private boolean isFaulty;

}
