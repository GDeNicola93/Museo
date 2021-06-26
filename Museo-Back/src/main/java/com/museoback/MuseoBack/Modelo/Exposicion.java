package com.museoback.MuseoBack.Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
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
    private Set<DetalleExposicion> detalleExposicion = new HashSet<>();
}
