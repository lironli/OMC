import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {SensorsHistoryService} from "../../services/service-history.service";
import {CommonModule} from "@angular/common";
import {SensorMailFunctionReportTableConfig} from "../../services/sensor-mail-function-report.models";

@Component({
  selector: 'app-sensors-mail-function-report',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './sensors-mail-function-report.component.html',
  styleUrl: './sensors-mail-function-report.component.scss'
})
export class SensorsMailFunctionReportComponent {

  public sensorHistoryData: Observable<SensorMailFunctionReportTableConfig> =
    this.sensorsHistoryService.getSensorsMailFunctionReportData$();

  constructor(private sensorsHistoryService: SensorsHistoryService) { }
}
