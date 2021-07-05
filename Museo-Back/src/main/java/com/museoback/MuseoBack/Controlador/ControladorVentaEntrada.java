
package com.museoback.MuseoBack.Controlador;

import com.museoback.MuseoBack.Dtos.VentaEntradaDto;
import com.museoback.MuseoBack.Modelo.Entrada;
import com.museoback.MuseoBack.Modelo.Tarifa;
import com.museoback.MuseoBack.Servicio.GestorVentaEntrada;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/venta_entrada")
public class ControladorVentaEntrada {
    @Autowired
    private GestorVentaEntrada gestorVentaEntrada;
    
    @GetMapping("/tarifa")
    public List<Tarifa> buscarTarifasSedeEmpleado(){
        return this.gestorVentaEntrada.buscarTarifasSedeEmpleado();
    }
    
    @GetMapping("/validar-limite-visitantes/{cantEntradas}")
    public boolean validarLimitevisitantes(@PathVariable(value="cantEntradas") Integer cantEntradas){
        return this.gestorVentaEntrada.validarLimiteVisitantes(cantEntradas);
    }
    
    @PostMapping("/generar-entradas")
    public List<Entrada> generarEntradas(@RequestBody VentaEntradaDto ventaEntradaDto){
        return this.gestorVentaEntrada.generarEntradas(ventaEntradaDto);
    }
}
