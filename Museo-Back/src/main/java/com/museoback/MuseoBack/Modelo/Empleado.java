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
class Empleado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String apellido;
    private String codigoValidacion;
    private String cuit;
    private String dni;
    private String domicilio;
    
    
    private LocalDate fechaIngreso;
    
    private LocalDate fechaNacimiento;
    
    private String mail;
    private String nombre;
    private char sexo;
    private String telefono;
    @ManyToOne
    private Sede sedeDondeTrabaja;
}
