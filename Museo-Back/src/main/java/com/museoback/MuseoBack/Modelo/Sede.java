package com.museoback.MuseoBack.Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private List<Tarifa> tarifas = new ArrayList<>();
    
    @OneToMany
    private List<Exposicion> exposiciones = new ArrayList<>();
    
    public List<Tarifa> mostrarTarifasVigentes(LocalDate fechaActual){
        List<Tarifa> tarifasVigentes = new ArrayList<>();
        for(Tarifa t : this.getTarifas()){
            if(t.esVigente(fechaActual)){
                tarifasVigentes.add(t);
            }
        }
        return tarifasVigentes;
    }
    
    public LocalTime calcularDuracionAExposicionesVigentes(LocalDate fechaActual){
        LocalTime duracionTotalExpoVigentes = LocalTime.of(0,0,0);
        for(Exposicion exp : this.getExposiciones()){
            if(exp.esVigente(fechaActual)){
                LocalTime duracionExposicion = exp.calcularDuracionObrasExpuestas();
                duracionTotalExpoVigentes = duracionTotalExpoVigentes.plus(duracionExposicion.getSecond(),ChronoUnit.SECONDS);
                duracionTotalExpoVigentes = duracionTotalExpoVigentes.plus(duracionExposicion.getMinute(),ChronoUnit.MINUTES);
                duracionTotalExpoVigentes = duracionTotalExpoVigentes.plus(duracionExposicion.getHour(),ChronoUnit.HOURS);
            }
        }
        return duracionTotalExpoVigentes;
    }
}
