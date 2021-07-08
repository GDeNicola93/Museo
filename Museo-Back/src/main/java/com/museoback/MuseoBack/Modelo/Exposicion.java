package com.museoback.MuseoBack.Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Exposicion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private LocalDate fechaFin;
    private LocalDate fechaFinReplanificada;
    private LocalDate fechaInicio;
    private LocalDate fechaInicioReplanificada;
    private LocalTime horaApertura;
    private LocalTime horaCierre;
    private String nombre;
    @ManyToOne
    private Empleado empleadoCreo;  
    @OneToMany
    private List<DetalleExposicion> detalleExposicion = new ArrayList<>();
    
    public boolean esVigente(LocalDate fechaActual){
        LocalDate inicio = this.getFechaInicio();
        LocalDate fin = this.getFechaFin();
        
        if(this.getFechaInicioReplanificada() != null){
            inicio = this.getFechaInicioReplanificada();
        }
        if(this.getFechaFinReplanificada() != null){
            fin = this.getFechaFinReplanificada();
        }
        
        if(fechaActual.isAfter(inicio) && fechaActual.isBefore(fin)){
            return true;
        }else{
            return false;
        }  
    }
    
    public LocalTime calcularDuracionObrasExpuestas(){
        LocalTime duracionTotal = LocalTime.of(0,0,0);
        for(DetalleExposicion dt : this.getDetalleExposicion()){
            LocalTime duracionObra = dt.buscarDuracionExpResObra();
            duracionTotal = duracionTotal.plus(duracionObra.getSecond(),ChronoUnit.SECONDS);
            duracionTotal = duracionTotal.plus(duracionObra.getMinute(),ChronoUnit.MINUTES);
            duracionTotal = duracionTotal.plus(duracionObra.getHour(),ChronoUnit.HOURS);
        }
        return duracionTotal;
    }
}
