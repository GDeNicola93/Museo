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
}
