import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProgramList } from './program-list';

describe('ProgramList', () => {
  let component: ProgramList;
  let fixture: ComponentFixture<ProgramList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProgramList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProgramList);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
