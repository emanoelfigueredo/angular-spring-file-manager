import { TestBed } from '@angular/core/testing';

import { ResolvedorExtencoesService } from './resolvedor-extencoes.service';

describe('ResolvedorExtencoesService', () => {
  let service: ResolvedorExtencoesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResolvedorExtencoesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
