import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalAdicionarArquivoComponent } from './modal-adicionar-arquivo.component';

describe('ModalAdicionarArquivoComponent', () => {
  let component: ModalAdicionarArquivoComponent;
  let fixture: ComponentFixture<ModalAdicionarArquivoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModalAdicionarArquivoComponent]
    });
    fixture = TestBed.createComponent(ModalAdicionarArquivoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
