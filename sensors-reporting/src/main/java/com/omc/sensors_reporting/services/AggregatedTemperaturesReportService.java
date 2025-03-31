package com.omc.sensors_reporting.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mongodb.MongoException;
import com.omc.sensors_reporting.datatypes.AggregatedSensorData;
import com.omc.sensors_reporting.datatypes.AggregatedTempReportResponse;
import com.omc.sensors_reporting.interfaces.IReportService;

import lombok.extern.slf4j.Slf4j;




@Service
@Slf4j
public class AggregatedTemperaturesReportService implements IReportService {
	
	@Autowired
    private MongoTemplate mongoTemplate;
		
	@Override
	public ResponseEntity<AggregatedTempReportResponse> getReport() {
		try {
			Instant weekAgo = Instant.now().minus(7, ChronoUnit.DAYS);
			long weekAgoMillis = weekAgo.toEpochMilli();
		    
			Aggregation aggregation = Aggregation.newAggregation(
				    Aggregation.match(Criteria.where("timestamp").gte(weekAgoMillis).and("isFaulty").is(false)),
				    Aggregation.project()
				        .andExpression("{$toDate: '$timestamp'}").as("convertedTimestamp")
				        .and("temperature").as("temperature"),
				    Aggregation.project()
				        .andExpression("{$hour: '$convertedTimestamp'}").as("procHour")
				        .and("temperature").as("temperature"),
				    Aggregation.group("procHour")
				        .avg("temperature").as("averageTemperature")
				);

		    AggregationResults<AggregatedSensorData> results = mongoTemplate.aggregate(aggregation, "sensor_data", AggregatedSensorData.class);   

		    List<AggregatedSensorData> items =  new ArrayList<>(results.getMappedResults());
		    AggregatedTempReportResponse response = new AggregatedTempReportResponse();
		    response.setItems(items);
		    return new ResponseEntity(response, HttpStatus.OK);
		    
		} catch (MongoException e) {
	        log.error("Database error occurred.");
	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error occurred", e);

	    } catch (IllegalArgumentException e) {
	    	log.error("Invalid aggregation query.");
	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid aggregation query", e);

	    } catch (Exception e) {
	    	log.error("An unexpected error occurred.");
	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
	    }
	    
	}
}
