import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AverageTemperaturePerHourReportComponent } from './average-temperature-per-hour-report.component';

describe('AverageTemperaturePerHourReportComponent', () => {
  let component: AverageTemperaturePerHourReportComponent;
  let fixture: ComponentFixture<AverageTemperaturePerHourReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AverageTemperaturePerHourReportComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AverageTemperaturePerHourReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
