package com.omc.sensors_reporting.datatypes;

import java.util.List;

import lombok.Data;

@Data
public class MalfunctionSensorsReportResponse {
    private List<MalfunctioningSensorData> items;
}