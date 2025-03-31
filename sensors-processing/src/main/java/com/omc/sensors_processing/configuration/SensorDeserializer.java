package com.omc.sensors_processing.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omc.sensors_processing.datatypes.SensorData;

import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class SensorDeserializer implements Deserializer<SensorData> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Configuration can be done here if needed
    }

    @Override
    public SensorData deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, SensorData.class);
        } catch (Exception e) {
            // Handle the error appropriately
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void close() {
        // Cleanup resources if needed
    }
}
