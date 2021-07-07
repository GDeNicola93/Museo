package com.museoback.MuseoBack.Servicio;

import com.museoback.MuseoBack.Dtos.VentaEntradaDto;
import com.museoback.MuseoBack.Modelo.Usuario;
import com.museoback.MuseoBack.Modelo.Empleado;
import com.museoback.MuseoBack.Modelo.Entrada;
import com.museoback.MuseoBack.Modelo.ReservaVisita;
import com.museoback.MuseoBack.Modelo.Sede;
import com.museoback.MuseoBack.Modelo.Tarifa;
import com.museoback.MuseoBack.Persistencia.EntradaRepositorio;
import com.museoback.MuseoBack.Persistencia.ReservaVisitaRepositorio;
import com.museoback.MuseoBack.Persistencia.UsuarioRepositorio;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
    
    @Autowired
    private ReservaVisitaRepositorio reservaVisitaRepositorio;
    
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
    
    public boolean validarLimiteVisitantes(Integer cantidadEntradas){
        Integer cantVisitantesactuales;
        LocalDateTime fechaHoraActual = this.obtenerFechaHoraActual();
        Sede sedeEmpleado = this.buscarEmpleadoLogeado().getSedeDondeTrabaja();
        cantVisitantesactuales = this.buscarVisitantesEnSede(fechaHoraActual, sedeEmpleado,this.buscarExposicionVigente());
        cantVisitantesactuales = cantVisitantesactuales + this.buscarReservasParaAsistir(fechaHoraActual, sedeEmpleado);
        Integer visitantesDisponibles = sedeEmpleado.getCantMaximaVisitantes() - cantVisitantesactuales;       
        if(cantidadEntradas <= visitantesDisponibles){
            return true;
        }else{
            return false;
        }
    }
    
    public Integer buscarVisitantesEnSede(LocalDateTime fechaHoraActual,Sede sede,LocalTime duracionVisita){
        Integer cantidadEntradas = 0;
        List<Entrada> entradas = entradaRepositorio.findAll();
        for(Entrada e : entradas){
            if(e.sonDeFechaHoraYSede(fechaHoraActual,sede,duracionVisita)){
                cantidadEntradas = cantidadEntradas + 1;
            }
        }
        return cantidadEntradas;
        
    }
    
    public Integer buscarReservasParaAsistir(LocalDateTime fechaHoraActual,Sede sede){
        Integer cantidadReservasConfirmadas = 0;
        List<ReservaVisita> reservas = reservaVisitaRepositorio.findAll();
        for(ReservaVisita rv : reservas){
            cantidadReservasConfirmadas = cantidadReservasConfirmadas + rv.sonParaFechaHoraYSede(fechaHoraActual, sede);
        }
        return cantidadReservasConfirmadas;
    }
    
    public List<Entrada> generarEntradas(VentaEntradaDto ventaEntradaDto){
        List<Entrada> entradasGeneradas = new ArrayList<>();
        LocalDateTime fechaHoraActual = this.obtenerFechaHoraActual();
        Sede sedeEmpleadoLogeado = this.buscarEmpleadoLogeado().getSedeDondeTrabaja();
        Integer cantEntradasAGenerar = ventaEntradaDto.getCantEntradas();
        for(int i = 0;i<cantEntradasAGenerar;i++){
             if(ventaEntradaDto.isGuia()){
                 Entrada entradaGenerada = new Entrada(fechaHoraActual.toLocalDate(),fechaHoraActual.toLocalTime(),sedeEmpleadoLogeado,ventaEntradaDto.getTarifa(),ventaEntradaDto.getTarifa().getMonto() + ventaEntradaDto.getTarifa().getMontoAdicionalGuia(),generarNroEntrada());
                 entradaRepositorio.save(entradaGenerada);
             }else{
                 Entrada entradaGenerada = new Entrada(fechaHoraActual.toLocalDate(),fechaHoraActual.toLocalTime(),sedeEmpleadoLogeado,ventaEntradaDto.getTarifa(),ventaEntradaDto.getTarifa().getMonto(),generarNroEntrada());
                 entradaRepositorio.save(entradaGenerada);
             }
        }
        return entradasGeneradas;
    }
    
    public long generarNroEntrada(){
        long ultimoNro = 0;
        if(!entradaRepositorio.findFirstByOrderByNumeroDesc().isEmpty()){
            ultimoNro = entradaRepositorio.findFirstByOrderByNumeroDesc().get().getNumero();
        }
        ultimoNro = ultimoNro + 1;
        return ultimoNro;
    }
}
