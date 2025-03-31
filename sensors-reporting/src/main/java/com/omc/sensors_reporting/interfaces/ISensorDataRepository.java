package com.omc.sensors_reporting.interfaces;

import com.omc.sensors_reporting.datatypes.SensorData;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISensorDataRepository extends MongoRepository<SensorData, String> {

	List<SensorData> findByTimestampGreaterThanEqual(long weekAgoMillis);
}
