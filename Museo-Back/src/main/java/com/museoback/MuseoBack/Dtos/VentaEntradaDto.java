package com.museoback.MuseoBack.Dtos;

import com.museoback.MuseoBack.Modelo.Tarifa;
import lombok.Data;

@Data
public class VentaEntradaDto {
    private Tarifa tarifa;
    private boolean guia;
    private Integer cantEntradas;
}
