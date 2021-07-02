package com.museoback.MuseoBack.Modelo;

import java.io.Serializable;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class DetalleExposicion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String lugarAsignado;
    
    @OneToOne
    private Obra obra;
    
    public LocalTime buscarDuracionExpResObra(){
        return this.getObra().getDuracionResumida();
    }
}
