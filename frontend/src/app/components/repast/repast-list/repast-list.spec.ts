import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RepastList } from './repast-list';

describe('RepastList', () => {
  let component: RepastList;
  let fixture: ComponentFixture<RepastList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RepastList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RepastList);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
