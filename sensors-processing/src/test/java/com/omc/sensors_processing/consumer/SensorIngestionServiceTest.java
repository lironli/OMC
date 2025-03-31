package com.omc.sensors_processing.consumer;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.omc.sensors_processing.datatypes.Face;
import com.omc.sensors_processing.datatypes.SensorData;
import com.omc.sensors_processing.services.SensorIngestionService;

public class SensorIngestionServiceTest {

//    @Mock
//    private MongoTemplate mongoTemplate;
//
//    @InjectMocks
//    private SensorIngestionService sensorIngestionService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testConsumeSensor() {
//        
//    	long initialTimestamp = System.currentTimeMillis();
//    	
//        SensorData sensorData = new SensorData();
//        sensorData.setSensorId(123);
//        sensorData.setTimestamp(initialTimestamp);
//        sensorData.setFace(Face.SOUTH);
//        sensorData.setTemperature(30.1);
//
//        // When
//        sensorIngestionService.consumeSensor(sensorData);
//
//        // Then
//        Query query = new Query(Criteria.where("sensorData.id").is(sensorData.getSensorId()));
//        Update update = new Update()
//                .set("sensorData", sensorData)
//                .set("status", "active");
//
//        verify(mongoTemplate, times(1)).upsert(query, update, "sensor_data");
//        assertTrue(sensorData.getTimestamp() >= initialTimestamp, "Timestamp should be updated");
//    }
}
