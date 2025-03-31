package com.omc.sensors_processing.fault_detection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.omc.sensors_processing.datatypes.ExtendedSensorData;
import com.omc.sensors_processing.datatypes.Face;
import com.omc.sensors_processing.datatypes.SensorData;
import com.omc.sensors_processing.services.SensorFaultDetectionService;

class SensorFaultDetectionServiceTest {

    @InjectMocks
    private SensorFaultDetectionService sensorFaultDetectionService;

    @Mock
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAlertSensorFault_OnlyOneSensor() {
        ExtendedSensorData sensor1 = createSensorData(1, 25.0, Face.NORTH);

        when(mongoTemplate.find(any(), eq(ExtendedSensorData.class)))
                .thenReturn(Collections.singletonList(sensor1));
        
        sensorFaultDetectionService.alertSensorFault();

        verify(mongoTemplate, never()).updateFirst(any(), any(), eq(ExtendedSensorData.class));
    }

    @Test
    void testCalculateAverageTemperature() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {

    	ExtendedSensorData sensor1 = createSensorData(1, 25.0, Face.NORTH);
        ExtendedSensorData sensor2 = createSensorData(2, 35.0, Face.NORTH);
        List<ExtendedSensorData> sensors = Arrays.asList(sensor1, sensor2);
        
        Method method = SensorFaultDetectionService.class.getDeclaredMethod("calculateAverageTemperature", List.class);
        method.setAccessible(true);
        
        double average = (double) method.invoke(sensorFaultDetectionService, sensors);

        assertEquals(30.0, average);
    }

    @Test
    void testIsFaultyTemperature() {
        SensorData sensorData = new SensorData();
        sensorData.setTemperature(0.3); // Set above the threshold

        boolean result = sensorFaultDetectionService.isFaultyTemperature(sensorData);

        assertTrue(result);
    }

    @Test
    void testIsNotFaultyTemperature() {
        SensorData sensorData = new SensorData();
        sensorData.setTemperature(0.1); // Set below the threshold

        boolean result = sensorFaultDetectionService.isFaultyTemperature(sensorData);

        assertFalse(result);
    }

    private ExtendedSensorData createSensorData(int id, double temperature, Face face) {
        SensorData sensorData = new SensorData();
        sensorData.setSensorId(id);
        sensorData.setTemperature(temperature);
        sensorData.setFace(face);

        ExtendedSensorData extendedSensorData = new ExtendedSensorData();
        extendedSensorData.setSensorData(sensorData);
        return extendedSensorData;
    }
}
