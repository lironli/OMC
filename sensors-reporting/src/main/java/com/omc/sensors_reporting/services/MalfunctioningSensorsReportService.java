package com.omc.sensors_reporting.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mongodb.MongoException;
import com.omc.sensors_reporting.datatypes.MalfunctionSensorsReportResponse;
import com.omc.sensors_reporting.datatypes.MalfunctioningSensorData;
import com.omc.sensors_reporting.interfaces.IReportService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MalfunctioningSensorsReportService implements IReportService {

	@Autowired
    private MongoTemplate mongoTemplate;
	
	@Override
	public ResponseEntity<MalfunctionSensorsReportResponse> getReport() {
		List<MalfunctioningSensorData> sensorDataList = new ArrayList<>();
		
		try {
			Aggregation aggregation = Aggregation.newAggregation(
	            Aggregation.match(Criteria.where("isFaulty").is(true)), 
	            Aggregation.group("sensorId") 
	                .avg("temperature").as("averageValue") 
	        );
	
	        AggregationResults<MalfunctioningSensorData> results = mongoTemplate.aggregate(aggregation, "sensor_data", MalfunctioningSensorData.class);
	
	        List<MalfunctioningSensorData> items = new ArrayList<>(results.getMappedResults());
	        MalfunctionSensorsReportResponse response = new MalfunctionSensorsReportResponse();
	        response.setItems(items);
	        return new ResponseEntity(response, HttpStatus.OK);
	        
		} catch (DataAccessException | MongoException ex) {
			log.error("Database error occurred.");
	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error occurred", ex);
		} catch (Exception ex) {
			log.error("An unexpected error occurred.");
	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", ex);
		}
	}
}
