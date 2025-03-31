package com.omc.sensor_registration.producer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Service;

import com.omc.sensor_registration.datatypes.SensorData;

@Service
public class SimpleSensorDataHandler {

    private final BlockingQueue<SensorData> processedDataQueue = new LinkedBlockingQueue<>();

    public void handleSensorData(SensorData sensorData) {
        // Simulate processing of the sensor data
        System.out.println("Received sensor data: " + sensorData);
        processedDataQueue.offer(sensorData); 
    }

    public BlockingQueue<SensorData> getProcessedDataQueue() {
        return processedDataQueue;
    }
}
