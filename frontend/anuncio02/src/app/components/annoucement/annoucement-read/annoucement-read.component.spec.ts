import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnnoucementReadComponent } from './annoucement-read.component';

describe('AnnoucementReadComponent', () => {
  let component: AnnoucementReadComponent;
  let fixture: ComponentFixture<AnnoucementReadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnnoucementReadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnnoucementReadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
