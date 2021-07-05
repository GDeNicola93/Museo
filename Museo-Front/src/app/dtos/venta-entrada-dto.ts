import { Tarifa } from "../modelo/tarifa";

export class VentaEntradaDto{
    tarifa : Tarifa;
    guia : boolean;
    cantEntradas : number;
}