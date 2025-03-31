package com.omc.sensors_processing.interfaces;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.omc.sensors_processing.datatypes.ExtendedSensorData;

public interface SensorRepository extends MongoRepository<ExtendedSensorData, Integer> {
	
	@Query("{ 'timestamp' : { $lt: ?0 } }")
    List<ExtendedSensorData> findInactiveSensors(long timestampThreshold);

}
