package com.museoback.MuseoBack.Modelo;

import java.io.Serializable;
import java.time.LocalDate;
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
public class Obra implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private double alto;
    private double ancho;
    private String codigoSensor;
    private String descripcion;
    private LocalTime duracion;
    private LocalTime duracionExtendida;
    private LocalTime duracionResumida;
    private LocalDate fechaCreacion;
    private LocalDate fechaPrimerIngreso;
    private String nombreObra;
    private double valuacion;
    
    @ManyToOne
    private Empleado empleadoCreo;
    
}
