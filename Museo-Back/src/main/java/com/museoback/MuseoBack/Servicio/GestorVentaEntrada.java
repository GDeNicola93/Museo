package com.museoback.MuseoBack.Servicio;

import com.museoback.MuseoBack.Modelo.Usuario;
import com.museoback.MuseoBack.Modelo.Empleado;
import com.museoback.MuseoBack.Modelo.Tarifa;
import com.museoback.MuseoBack.Persistencia.EntradaRepositorio;
import com.museoback.MuseoBack.Persistencia.UsuarioRepositorio;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestorVentaEntrada {
    @Autowired
    private EntradaRepositorio entradaRepositorio;
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    public Empleado buscarEmpleadoLogeado(){
        Optional<Usuario> usuarioLogeado = this.usuarioRepositorio.findById(1);
        return usuarioLogeado.get().getEmpleado();
    }
    
    public LocalDateTime obtenerFechaHoraActual(){
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        return fechaHoraActual;
    }
    
    public List<Tarifa> buscarTarifasSedeEmpleado(){
        return this.buscarEmpleadoLogeado().getSedeDondeTrabaja().mostrarTarifasVigentes(this.obtenerFechaHoraActual().toLocalDate());
    }
    
   
    public LocalTime buscarExposicionVigente(){
        return this.buscarEmpleadoLogeado().getSedeDondeTrabaja().calcularDuracionAExposicionesVigentes(this.obtenerFechaHoraActual().toLocalDate());
    }
    
    public void validarLimiteVisitantes(){
        
    }
    
    public void buscarVisitantesEnSede(){
        
    }
    
    public void buscarReservasParaAsistir(){
        
    }
}
