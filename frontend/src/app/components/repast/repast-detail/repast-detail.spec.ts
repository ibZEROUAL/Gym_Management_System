import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RepastDetail } from './repast-detail';

describe('RepastDetail', () => {
  let component: RepastDetail;
  let fixture: ComponentFixture<RepastDetail>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RepastDetail]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RepastDetail);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
