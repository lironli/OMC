package com.omc.sensors_reporting.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.omc.sensors_reporting.datatypes.AggregatedSensorData;

class AggregatedTemperaturesReportServiceTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private AggregatedTemperaturesReportService reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getReport_UnexpectedError() {
        // Arrange
        when(mongoTemplate.aggregate(any(), eq("sensor_data"), eq(AggregatedSensorData.class)))
            .thenThrow(new RuntimeException("Unexpected error"));

        // Act & Assert
        ResponseStatusException thrown = 
            org.junit.jupiter.api.Assertions.assertThrows(ResponseStatusException.class, () -> {
                reportService.getReport();
            });

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, thrown.getStatusCode());
        assertEquals("An unexpected error occurred", thrown.getReason());
    }
}
