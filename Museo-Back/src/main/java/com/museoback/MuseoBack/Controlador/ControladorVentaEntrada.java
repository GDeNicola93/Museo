
package com.museoback.MuseoBack.Controlador;

import com.museoback.MuseoBack.Modelo.Tarifa;
import com.museoback.MuseoBack.Servicio.GestorVentaEntrada;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*") //Para poder acceder desde cualquier lado a mi backend
@RestController
@RequestMapping("/venta_entrada")
public class ControladorVentaEntrada {
    @Autowired
    private GestorVentaEntrada gestorVentaEntrada;
    
    @GetMapping("/tarifa")
    public List<Tarifa> buscarTarifasSedeEmpleado(){
        return this.gestorVentaEntrada.buscarTarifasSedeEmpleado();
    }
    
    @GetMapping("/duracion-total-guia")
    public LocalTime buscarExposicionVigente(){
        return this.gestorVentaEntrada.buscarExposicionVigente();
    }
}
