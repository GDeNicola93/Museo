package com.museoback.MuseoBack.Persistencia;

import com.museoback.MuseoBack.Modelo.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaRepositorio extends JpaRepository<Entrada,Integer> {
    
}
