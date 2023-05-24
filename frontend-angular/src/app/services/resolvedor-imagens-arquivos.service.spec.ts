import { TestBed } from '@angular/core/testing';

import { ResolvedorImagensArquivosService } from './resolvedor-imagens-arquivos.service';

describe('ResolvedorImagensArquivosService', () => {
  let service: ResolvedorImagensArquivosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResolvedorImagensArquivosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
