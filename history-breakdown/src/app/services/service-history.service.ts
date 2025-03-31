import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {BehaviorSubject, map, Observable, of, take} from 'rxjs';
import {
  generateRandomAverageTemperaturePerHourReportData,
  generateRandomSensorHistoryData
} from "./sensors-history-mock.helper";
import {
  SensorMailFunctionReport,
  SensorMailFunctionReportResponse,
  SensorMailFunctionReportTableConfig
} from "./sensor-mail-function-report.models";
import {
  AverageTemperaturePerHourReport,
  AverageTemperaturePerHourReportResponse, AverageTemperaturePerHourReportTableConfig
} from "./average-temperature-per-hour-report.models";

const AverageTemperaturePerHourReportColumns  = {
  processingHour: 'Processing Hour',
  averageTemperature: 'Average Temperature',
}

const SensorsMailFunctionReportColumns  = {
  sensorId: 'Sensor ID',
  averageTemperature: 'Average Temperature',
}


@Injectable({
  providedIn: 'root'
})
export class SensorsHistoryService {

  private sensorMailFunctionReportApiUrl = '/api/api/reports/malfunction-sensors';
  private averageTemperaturePerHourReportApiUrl = '/api/api/reports/aggregated-temperatures';

  private sensorsMailFunctionReportData = new BehaviorSubject<SensorMailFunctionReportTableConfig>({
    columns: [],
    data: []
  });

  private averageTemperaturePerHourReportData = new BehaviorSubject<AverageTemperaturePerHourReportTableConfig>({
    columns: [],
    data: []
  })

  constructor(private http: HttpClient) { }

  public fetchSensorsMailFunctionReport(): void {
    this.http.get<SensorMailFunctionReportResponse>(this.sensorMailFunctionReportApiUrl)
    //of(this.getMailFunctionReportMockData(100))
       .pipe(map(response => response.items),
       take(1),
     ).subscribe(data => this.updateMailFunctionReportData(
       data,
       SensorsMailFunctionReportColumns
     ))
  }

  public getSensorsMailFunctionReportData$(): Observable<SensorMailFunctionReportTableConfig> {
    return this.sensorsMailFunctionReportData.asObservable();
  }

  private updateMailFunctionReportData(data: SensorMailFunctionReport[], columns: Record<string, string>): void {
    this.sensorsMailFunctionReportData.next({
      data,
      columns: Object.values(columns)
    });
  }

  private getMailFunctionReportMockData(items: number): SensorMailFunctionReportResponse {
    return {
      items: generateRandomSensorHistoryData(items)
    };
  }

  // start region

  public fetchAverageTemperaturePerHourReport(): void {
    this.http.get<AverageTemperaturePerHourReportResponse>(this.averageTemperaturePerHourReportApiUrl)
    //of(this.getAverageTemperaturePerHourReportMockData(100))
      .pipe(map(response => response.items),
        take(1),
      ).subscribe(data => this.updateAverageTemperaturePerHourReportData(
      data,
      AverageTemperaturePerHourReportColumns
    ))
  }

  public getAverageTemperaturePerHourReport$(): Observable<AverageTemperaturePerHourReportTableConfig> {
    return this.averageTemperaturePerHourReportData.asObservable();
  }

  private updateAverageTemperaturePerHourReportData(data: AverageTemperaturePerHourReport[], columns: Record<string, string>): void {
    this.averageTemperaturePerHourReportData.next({
      data,
      columns: Object.values(columns)
    });
  }

  private getAverageTemperaturePerHourReportMockData(items: number): AverageTemperaturePerHourReportResponse {
    return {
      items: generateRandomAverageTemperaturePerHourReportData(items)
    };
  }

}
