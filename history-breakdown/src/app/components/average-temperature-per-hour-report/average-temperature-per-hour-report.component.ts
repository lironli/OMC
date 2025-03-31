import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {SensorsHistoryService} from "../../services/service-history.service";
import {CommonModule} from "@angular/common";
import {AverageTemperaturePerHourReportTableConfig} from "../../services/average-temperature-per-hour-report.models";

@Component({
  selector: 'app-average-temperature-per-hour-report',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './average-temperature-per-hour-report.component.html',
  styleUrl: './average-temperature-per-hour-report.component.scss'
})
export class AverageTemperaturePerHourReportComponent{

  public reportData: Observable<AverageTemperaturePerHourReportTableConfig> =
    this.sensorsHistoryService.getAverageTemperaturePerHourReport$();

  constructor(private sensorsHistoryService: SensorsHistoryService) { }
}

