import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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
}
