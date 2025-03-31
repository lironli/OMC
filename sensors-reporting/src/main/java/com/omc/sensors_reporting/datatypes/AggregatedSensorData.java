package com.omc.sensors_reporting.datatypes;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "sensor_data")
public class AggregatedSensorData {
    private int processingHour; 
    private Double averageTemperature;
}