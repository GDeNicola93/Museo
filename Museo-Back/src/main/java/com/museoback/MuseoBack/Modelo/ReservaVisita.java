package com.museoback.MuseoBack.Modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
public class ReservaVisita implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Integer cantidadAlumnos;
    private Integer cantidadAlumnosConfirmada;
    private LocalTime duracionEstimada;
    private LocalDateTime fechaHoraCreacion;
    private LocalDateTime fechaHoraReserva;
    private LocalTime horaFinReal;
    private LocalTime horaInicioReal;
    private Long numeroReserva;
    
    @ManyToOne
    private Sede sede;
    
    public Integer sonParaFechaHoraYSede(LocalDateTime fechaHoraActual,Sede sede){
        if(this.getSede().equals(sede)){
            if(this.getFechaHoraReserva().toLocalDate().equals(fechaHoraActual.toLocalDate())){
                if(this.getHoraInicioReal().isBefore(fechaHoraActual.toLocalTime()) && this.getHoraFinReal().isAfter(fechaHoraActual.toLocalTime())){
                    return this.getCantidadAlumnosConfirmada();
                }else{
                    return 0;
                }
            }else{
                return 0;
            }
        }else{
            return 0;
        }
    }
}
