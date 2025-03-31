
export interface AverageTemperaturePerHourReport {
  processingHour: number;
  averageTemperature: number;
}

export interface AverageTemperaturePerHourReportTableConfig {
  columns: string[];
  data: AverageTemperaturePerHourReport[]
}

export interface AverageTemperaturePerHourReportResponse {
  items: AverageTemperaturePerHourReport[];
}
