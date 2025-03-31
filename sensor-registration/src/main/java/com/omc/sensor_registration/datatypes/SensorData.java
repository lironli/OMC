package com.omc.sensor_registration.datatypes;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SensorData {

	@NotNull
	@Min(1)
	private Integer sensorId;
	
	@NotNull
    @Min(0)
	private long timestamp;
	
	@NotNull
	private Face face;
	
	@NotNull
	private double temperature;
}
