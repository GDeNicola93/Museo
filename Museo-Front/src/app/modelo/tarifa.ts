import { MonoTypeOperatorFunction } from "rxjs";
import { TipoEntrada } from "./tipo-entrada";
import { TipoVisita } from "./tipo-visita";

export class Tarifa{
    id : number;
    fechaFinVigencia : any;
    fechaInicioVigencia : any;
    monto : number;
    montoAdicionalGuia : number;
    tipoEntrada : TipoEntrada;
    tipoVisita : TipoVisita;
}