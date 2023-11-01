import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IssueChartComponent } from './issue-chart.component';

describe('IssueChartComponent', () => {
  let component: IssueChartComponent;
  let fixture: ComponentFixture<IssueChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IssueChartComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IssueChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
