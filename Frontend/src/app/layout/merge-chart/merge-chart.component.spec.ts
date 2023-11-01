import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MergeChartComponent } from './merge-chart.component';

describe('MergeChartComponent', () => {
  let component: MergeChartComponent;
  let fixture: ComponentFixture<MergeChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MergeChartComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MergeChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
