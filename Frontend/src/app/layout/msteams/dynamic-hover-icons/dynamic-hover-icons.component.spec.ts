import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DynamicHoverIconsComponent } from './dynamic-hover-icons.component';

describe('DynamicIconsComponent', () => {
  let component: DynamicHoverIconsComponent;
  let fixture: ComponentFixture<DynamicHoverIconsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DynamicHoverIconsComponent],
    });
    fixture = TestBed.createComponent(DynamicHoverIconsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should generate a dynamic link with email and message parameters', () => {
    fixture.detectChanges();

    const linkElement: HTMLAnchorElement =
      fixture.nativeElement.querySelector('a');

    // Ensure the href contains the email and message parameters
    expect(linkElement.href).toContain(
      'users=test@email.com?message=Test+message'
    );

    // Ensure the link styles are applied
    const linkStyles = window.getComputedStyle(linkElement);
  });
});
