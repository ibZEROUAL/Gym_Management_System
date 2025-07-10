import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdherentDetail } from './adherent-detail';

describe('AdherentDetail', () => {
  let component: AdherentDetail;
  let fixture: ComponentFixture<AdherentDetail>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdherentDetail]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdherentDetail);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
