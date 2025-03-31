export interface SensorMailFunctionReportTableConfig {
  columns: string[];
  data: SensorMailFunctionReport[]
}

export interface SensorMailFunctionReportResponse {
  items: SensorMailFunctionReport[];
}

export interface SensorMailFunctionReport {
  sensorId: string;
  averageTemperature: number;
}
