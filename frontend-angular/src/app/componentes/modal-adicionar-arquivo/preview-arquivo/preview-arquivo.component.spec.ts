import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviewArquivoComponent } from './preview-arquivo.component';

describe('PreviewArquivoComponent', () => {
  let component: PreviewArquivoComponent;
  let fixture: ComponentFixture<PreviewArquivoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PreviewArquivoComponent]
    });
    fixture = TestBed.createComponent(PreviewArquivoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
