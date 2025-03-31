package com.omc.sensors_reporting.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.mongodb.MongoException;
import com.omc.sensors_reporting.datatypes.SensorData;
import com.omc.sensors_reporting.interfaces.ISensorDataRepository;

@ExtendWith(MockitoExtension.class)
public class AllSensorsReportServiceTest {

    @InjectMocks
    private AllSensorsReportService reportService;

    @Mock
    private ISensorDataRepository repository;

    @BeforeEach
    void setUp() {
        // Any common setup can go here
    }

    @Test
    void getReport_Success() {
        // Arrange
        SensorData sensor1 = new SensorData();
        sensor1.setProcessingHour(16);
        sensor1.setTemperature(25.0);

        SensorData sensor2 = new SensorData();
        sensor2.setProcessingHour(16);
        sensor2.setTemperature(26.0);

        List<SensorData> expectedSensors = Arrays.asList(sensor1, sensor2);
        when(repository.findAll()).thenReturn(expectedSensors);

        // Act
        List<SensorData> actualSensors = reportService.getReport();

        // Assert
        assertNotNull(actualSensors);
        assertEquals(2, actualSensors.size());
        assertEquals(sensor1.getProcessingHour(), actualSensors.get(0).getProcessingHour());
        assertEquals(sensor2.getProcessingHour(), actualSensors.get(1).getProcessingHour());
    }

    @Test
    void getReport_DatabaseError() {
        // Arrange
        when(repository.findAll()).thenThrow(new MongoException("Database error"));

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            reportService.getReport();
        });

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
        assertEquals("Database connection error", exception.getReason());
    }

    @Test
    void getReport_UnexpectedError() {
        // Arrange
        when(repository.findAll()).thenThrow(new RuntimeException("Unexpected error"));

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            reportService.getReport();
        });

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
        assertEquals("An unexpected error occurred", exception.getReason());
    }
}
