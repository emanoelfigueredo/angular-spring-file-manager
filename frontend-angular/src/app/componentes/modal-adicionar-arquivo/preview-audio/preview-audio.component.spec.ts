import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviewAudioComponent } from './preview-audio.component';

describe('PreviewAudioComponent', () => {
  let component: PreviewAudioComponent;
  let fixture: ComponentFixture<PreviewAudioComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PreviewAudioComponent]
    });
    fixture = TestBed.createComponent(PreviewAudioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
