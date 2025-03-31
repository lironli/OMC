import {Component, OnInit} from '@angular/core';
import {SensorsHistoryService} from "../../services/service-history.service";
import {CommonModule} from "@angular/common";
import {
  SensorsMailFunctionReportComponent
} from "../sensors-mail-function-report/sensors-mail-function-report.component";
import {
  AverageTemperaturePerHourReportComponent
} from "../average-temperature-per-hour-report/average-temperature-per-hour-report.component";

@Component({
  selector: 'app-sensors-reports-page',
  standalone: true,
  imports: [CommonModule, SensorsMailFunctionReportComponent, AverageTemperaturePerHourReportComponent],
  providers: [SensorsHistoryService],
  templateUrl: './sensors-reports-page.component.html',
  styleUrl: './sensors-reports-page.component.scss'
})
export class SensorsReportsPageComponent{

  public selectedTab = 1;

  constructor(private sensorsHistoryService: SensorsHistoryService) { }

  onSelectedTab(tabId: number): void {
    this.selectedTab = tabId;
  }

  onRefreshSensorsMailFunctionReport(): void {
    this.sensorsHistoryService.fetchSensorsMailFunctionReport();
  }

  onRefreshAverageTemperaturePerHourReport(): void {
    this.sensorsHistoryService.fetchAverageTemperaturePerHourReport();
  }
}
