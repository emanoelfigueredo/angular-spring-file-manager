import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiretoriosCardComponent } from './diretorios-card.component';

describe('DiretoriosCardComponent', () => {
  let component: DiretoriosCardComponent;
  let fixture: ComponentFixture<DiretoriosCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DiretoriosCardComponent]
    });
    fixture = TestBed.createComponent(DiretoriosCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
