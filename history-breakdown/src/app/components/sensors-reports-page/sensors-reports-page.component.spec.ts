import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SensorsReportsPageComponent } from './sensors-reports-page.component';

describe('SensorsReportsPageComponent', () => {
  let component: SensorsReportsPageComponent;
  let fixture: ComponentFixture<SensorsReportsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SensorsReportsPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SensorsReportsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
