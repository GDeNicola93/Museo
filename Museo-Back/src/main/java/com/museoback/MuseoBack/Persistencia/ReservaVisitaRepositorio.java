
package com.museoback.MuseoBack.Persistencia;

import com.museoback.MuseoBack.Modelo.ReservaVisita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaVisitaRepositorio extends JpaRepository<ReservaVisita,Integer> {
    
}
