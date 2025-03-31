import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SensorsMailFunctionReportComponent } from './sensors-mail-function-report.component';

describe('SensorsMailFunctionReportComponent', () => {
  let component: SensorsMailFunctionReportComponent;
  let fixture: ComponentFixture<SensorsMailFunctionReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SensorsMailFunctionReportComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SensorsMailFunctionReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
