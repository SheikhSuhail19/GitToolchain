import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MSTeamsComponent } from './msteams.component';

describe('MSTeamsComponent', () => {
  let component: MSTeamsComponent;
  let fixture: ComponentFixture<MSTeamsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MSTeamsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MSTeamsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
