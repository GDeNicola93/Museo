import { Component, OnInit } from '@angular/core';
import { VentaEntradaDto } from 'src/app/dtos/venta-entrada-dto';
import { Entrada } from 'src/app/modelo/entrada';
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
  limiteVisitantesSuperado : boolean = true;
  form : any = {};
  entradasGeneradas : Entrada[];

  constructor(private gestorVentaEntrada : GestorVentaEntradaService) { }

  ngOnInit(): void {
    this.habilitarVentana();
  }

  habilitarVentana() : void{
    this.mostrarTarifasVigenentes();
    this.tomarCantidadEntradas();
    this.tarifaSeleccionada = null;
    this.limiteVisitantesSuperado = true;
    this.form.cantEntradas = 1;
    this.form.guia = false;
  }

  mostrarTarifasVigenentes() : void{
    this.gestorVentaEntrada.buscarTarifasSedeEmpleado().subscribe( data =>{
      this.tablaTarifas = data;
    });
  }

  
  tomarCantidadEntradas() : void{
    this.gestorVentaEntrada.validarLimiteVisitantes(this.form.cantEntradas).subscribe(data =>{
      this.limiteVisitantesSuperado = data;
    });
  }

  tomarSeleccionTarifa(tarifa : Tarifa) : void{
    this.tarifaSeleccionada = tarifa;
  }

  eliminarSeleccionTarifa() : void{
    this.tarifaSeleccionada = null;
  }

  tomarConfirmacionVta() : void{
    this.form.tarifa = this.tarifaSeleccionada;
    this.gestorVentaEntrada.generarEntradas(this.form).subscribe(data =>{
      this.entradasGeneradas = data;
      alert("Imprimiendo entradas...");
      this.habilitarVentana();
    });
  }

}
