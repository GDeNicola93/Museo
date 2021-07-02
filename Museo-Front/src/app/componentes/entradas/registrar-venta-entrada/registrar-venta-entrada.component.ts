import { Component, OnInit } from '@angular/core';
import { Tarifa } from 'src/app/modelo/tarifa';
import { GestorVentaEntradaService } from 'src/app/servicios/gestor-venta-entrada.service';

@Component({
  selector: 'app-registrar-venta-entrada',
  templateUrl: './registrar-venta-entrada.component.html',
  styleUrls: ['./registrar-venta-entrada.component.css']
})
export class RegistrarVentaEntradaComponent implements OnInit {
  tablaTarifas : Tarifa[];
  tarifaSeleccionada : Tarifa;

  constructor(private gestorVentaEntrada : GestorVentaEntradaService) { }

  ngOnInit(): void {
    this.habilitarVentana();
  }

  habilitarVentana() : void{
    this.mostrarTarifasVigenentes();
  }

  mostrarTarifasVigenentes() : void{
    this.gestorVentaEntrada.buscarTarifasSedeEmpleado().subscribe( data =>{
      this.tablaTarifas = data;
    });
  }

  tomarSeleccionTarifa(tarifa : Tarifa) : void{
    this.tarifaSeleccionada = tarifa;
  }

  eliminarSeleccionTarifa() : void{
    this.tarifaSeleccionada = null;
  }

}
