package com.omc.sensors_reporting.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mongodb.MongoException;
import com.omc.sensors_reporting.datatypes.SensorData;
import com.omc.sensors_reporting.interfaces.IReportService;
import com.omc.sensors_reporting.interfaces.ISensorDataRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AllSensorsReportService implements IReportService{

	@Autowired
	private ISensorDataRepository repository;
	
	@Override
	public List<SensorData> getReport() {
		try {
			return repository.findAll();
		} catch (MongoException e) {
			log.error("Database error occurred.");
	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database connection error", e);
	
	    } catch (Exception e) {
	    	log.error("An unexpected error occurred.");
	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
	    }
	}
}
