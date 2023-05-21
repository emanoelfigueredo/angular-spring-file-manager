import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalAdicionarDiretorioComponent } from './modal-adicionar-diretorio.component';

describe('ModalAdicionarDiretorioComponent', () => {
  let component: ModalAdicionarDiretorioComponent;
  let fixture: ComponentFixture<ModalAdicionarDiretorioComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModalAdicionarDiretorioComponent]
    });
    fixture = TestBed.createComponent(ModalAdicionarDiretorioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
