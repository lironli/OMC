package com.omc.sensor_registration.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omc.sensor_registration.datatypes.SensorData;

import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

@Slf4j
public class SensorSerializer implements Serializer<SensorData> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Configuration can be done here if needed
    }

    @Override
    public byte[] serialize(String topic, SensorData sensorData) {
        try {
        	log.debug("serialize " + sensorData.getSensorId());
            return objectMapper.writeValueAsBytes(sensorData);
            
        } catch (JsonProcessingException e) {
            // Handle the error appropriately
        	log.debug("serialize failed");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void close() {
        // Cleanup resources if needed
    }
}
