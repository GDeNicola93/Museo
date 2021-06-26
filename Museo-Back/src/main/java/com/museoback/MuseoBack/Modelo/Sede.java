package com.museoback.MuseoBack.Modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Sede implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Integer cantMaximaVisitantes;
    private Integer cantMaxPorGuia;
    private String nombre;
    
    @ManyToMany
    private Set<Tarifa> tarifas = new HashSet<>();
    
    @OneToMany
    private Set<Exposicion> exposiciones = new HashSet<>();
}
