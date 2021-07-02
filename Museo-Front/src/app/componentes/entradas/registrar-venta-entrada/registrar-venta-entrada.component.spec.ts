import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrarVentaEntradaComponent } from './registrar-venta-entrada.component';

describe('RegistrarVentaEntradaComponent', () => {
  let component: RegistrarVentaEntradaComponent;
  let fixture: ComponentFixture<RegistrarVentaEntradaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrarVentaEntradaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrarVentaEntradaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
