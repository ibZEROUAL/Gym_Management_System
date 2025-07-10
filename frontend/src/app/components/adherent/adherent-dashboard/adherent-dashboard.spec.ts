import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdherentDashboard } from './adherent-dashboard';

describe('AdherentDashboard', () => {
  let component: AdherentDashboard;
  let fixture: ComponentFixture<AdherentDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdherentDashboard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdherentDashboard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
