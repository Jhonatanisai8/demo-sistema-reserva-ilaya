package com.isai.demosistemacitasillaya.app.repositorys;

import com.isai.demosistemacitasillaya.app.models.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LugarRepository extends JpaRepository<Lugar, Integer> {
    @Query("SELECT l FROM Lugar l WHERE LOWER(l.nombreLugar) LIKE LOWER(CONCAT('%', :terminoBusqueda, '%')) OR LOWER(l.direccion) LIKE LOWER(CONCAT('%', :terminoBusqueda, '%'))")
    List<Lugar> searchLugares(@Param("terminoBusqueda") String terminoBusqueda);

}
