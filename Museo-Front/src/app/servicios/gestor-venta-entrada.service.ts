import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { VentaEntradaDto } from '../dtos/venta-entrada-dto';
import { Entrada } from '../modelo/entrada';
import { Tarifa } from '../modelo/tarifa';

const cabecera = {headers: new HttpHeaders({'Content-TYpe': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class GestorVentaEntradaService {
  urlBase : String = 'http://localhost:8080/venta_entrada/';

  constructor(private httpClient: HttpClient) { }

  public buscarTarifasSedeEmpleado() : Observable<Tarifa[]>{
    return this.httpClient.get<Tarifa[]>(this.urlBase + 'tarifa',cabecera);
  }

  public validarLimiteVisitantes(cantEntradas : number) : Observable<boolean>{
    if(cantEntradas == null || cantEntradas==0){
      cantEntradas = 1;
    }
    return this.httpClient.get<boolean>(this.urlBase + `validar-limite-visitantes/${cantEntradas}`,cabecera);
  }

  public generarEntradas(ventaEntradaDto : VentaEntradaDto) : Observable<Entrada[]>{
    return this.httpClient.post<Entrada[]>(this.urlBase + `generar-entradas`,ventaEntradaDto,cabecera);
  }
}
