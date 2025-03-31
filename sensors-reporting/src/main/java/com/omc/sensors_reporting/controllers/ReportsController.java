package com.omc.sensors_reporting.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omc.sensors_reporting.datatypes.AggregatedSensorData;
import com.omc.sensors_reporting.datatypes.AggregatedTempReportResponse;
import com.omc.sensors_reporting.datatypes.MalfunctionSensorsReportResponse;
import com.omc.sensors_reporting.datatypes.MalfunctioningSensorData;
import com.omc.sensors_reporting.datatypes.SensorData;
import com.omc.sensors_reporting.services.AggregatedTemperaturesReportService;
import com.omc.sensors_reporting.services.AllSensorsReportService;
import com.omc.sensors_reporting.services.MalfunctioningSensorsReportService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/reports")
@Tag(name = "Sensor Reporting", description = "Endpoints for sensors reports.")
public class ReportsController {

	@Autowired
	private MalfunctioningSensorsReportService malfunctioningSensorsReportService;
	
	@Autowired
	private AggregatedTemperaturesReportService aggregatedTemperaturesReportService;
	
	@Autowired
	private AllSensorsReportService allSensorsReportService;
	
	@GetMapping("/malfunction-sensors")
	@Operation(summary = "Get all malfunctioning sensors")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Successfully retrieved data for reports"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<MalfunctionSensorsReportResponse> malfunctionSensorsReport () {
		return malfunctioningSensorsReportService.getReport();
	}
	
	@GetMapping("/aggregated-temperatures")
	@Operation(summary = "Get aggregated hourly temperatures for the past week.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Successfully retrieved data for reports"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public ResponseEntity<AggregatedTempReportResponse> aggregatedTempReport() {
		return aggregatedTemperaturesReportService.getReport();
	}
	
	@GetMapping("/all")
	@Operation(summary = "Get all registered sensors.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Successfully retrieved data for reports"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	})
	ResponseEntity<List<SensorData>> all() {
		return ResponseEntity.ok(allSensorsReportService.getReport());
	}
}
