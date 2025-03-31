package com.omc.sensor_registration.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omc.sensor_registration.datatypes.SensorData;
import com.omc.sensor_registration.services.SensorRegistrationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/sensor")
@Tag(name = "Sensor Registration", description = "Endpoints for registering sensors")
public class SensorRegistrationController {
	
	@Autowired
	private SensorRegistrationService registrationService;
	
	@PostMapping("/register")
    @Operation(summary = "Register a new sensor")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sensor registered successfully"),
	        @ApiResponse(responseCode = "400", description = "Invalid sensor data"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<String> registerSensor(
			@Parameter(description = "Sensor data to register")
			@RequestBody SensorData sensorData) {
		
		try {
			registrationService.register(sensorData);
			return ResponseEntity.ok("sensor has registered");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Failed to register sensor: " + e.getMessage());
		}
	}
}
