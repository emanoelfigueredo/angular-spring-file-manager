import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviewVideoComponent } from './preview-video.component';

describe('PreviewVideoComponent', () => {
  let component: PreviewVideoComponent;
  let fixture: ComponentFixture<PreviewVideoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PreviewVideoComponent]
    });
    fixture = TestBed.createComponent(PreviewVideoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
