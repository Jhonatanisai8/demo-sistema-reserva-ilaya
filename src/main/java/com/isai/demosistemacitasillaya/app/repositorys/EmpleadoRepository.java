package com.isai.demosistemacitasillaya.app.repositorys;

import com.isai.demosistemacitasillaya.app.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository
        extends JpaRepository<Empleado, Integer> {

    @Query("SELECT e FROM Empleado e WHERE LOWER(e.nombres)" +
            " LIKE LOWER(CONCAT('%', :terminoBusqueda, '%')) " +
            "OR LOWER(e.apellidos) LIKE LOWER(CONCAT('%', :terminoBusqueda, '%'))")
    List<Empleado> searchEmpleados(@Param("terminoBusqueda") String terminoBusqueda);

}
