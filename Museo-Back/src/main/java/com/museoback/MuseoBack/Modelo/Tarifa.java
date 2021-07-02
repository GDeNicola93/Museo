package com.museoback.MuseoBack.Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Tarifa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private LocalDate fechaFinVigencia;
    
    private LocalDate fechaInicioVigencia;
    
    private double monto;
    
    private double montoAdicionalGuia;
    
    @ManyToOne
    private TipoEntrada tipoEntrada;
    
    @ManyToOne
    private TipoVisita tipoVisita;
    
    public boolean esVigente(LocalDate fechaActual){
        if(fechaActual.isAfter(this.getFechaInicioVigencia()) && fechaActual.isBefore(this.getFechaFinVigencia())){
            return true;
        }else{
            return false;
        }
    }
}
