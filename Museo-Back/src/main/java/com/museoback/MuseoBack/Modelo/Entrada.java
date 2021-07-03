package com.museoback.MuseoBack.Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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
public class Entrada implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private LocalDate fechaVta;
    private LocalTime horaVta;
    private double monto;
    private Long numero;
    @ManyToOne
    private Tarifa tarifa;
    @ManyToOne
    private Sede sede;
    
    public boolean sonDeFechaHoraYSede(LocalDateTime fechaHoraActual,Sede sede,LocalTime duracionVisita){
        if(this.getSede().equals(sede)){
            if(this.getFechaVta().equals(fechaHoraActual.toLocalDate())){
               LocalTime sumaHoraVentaDuracionVisita = this.getHoraVta();
               
               sumaHoraVentaDuracionVisita = sumaHoraVentaDuracionVisita.plus(duracionVisita.getSecond(),ChronoUnit.SECONDS);
               sumaHoraVentaDuracionVisita = sumaHoraVentaDuracionVisita.plus(duracionVisita.getMinute(),ChronoUnit.MINUTES);
               sumaHoraVentaDuracionVisita = sumaHoraVentaDuracionVisita.plus(duracionVisita.getHour(),ChronoUnit.HOURS);
              
       
               if(sumaHoraVentaDuracionVisita.isAfter(fechaHoraActual.toLocalTime())){
                   
                   return true;
               }else{
                   return false;
               }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
