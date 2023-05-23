import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviewImagemComponent } from './preview-imagem.component';

describe('PreviewImagemComponent', () => {
  let component: PreviewImagemComponent;
  let fixture: ComponentFixture<PreviewImagemComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PreviewImagemComponent]
    });
    fixture = TestBed.createComponent(PreviewImagemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
