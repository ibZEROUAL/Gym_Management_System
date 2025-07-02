import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdherentList } from './adherent-list';

describe('AdherentList', () => {
  let component: AdherentList;
  let fixture: ComponentFixture<AdherentList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdherentList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdherentList);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
