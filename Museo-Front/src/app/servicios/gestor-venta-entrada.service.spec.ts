import { TestBed } from '@angular/core/testing';

import { GestorVentaEntradaService } from './gestor-venta-entrada.service';

describe('GestorVentaEntradaService', () => {
  let service: GestorVentaEntradaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GestorVentaEntradaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
