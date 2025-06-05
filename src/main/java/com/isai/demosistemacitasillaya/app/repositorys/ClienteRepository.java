package com.isai.demosistemacitasillaya.app.repositorys;

import com.isai.demosistemacitasillaya.app.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    //  JOIN FETCH para cargar el Usuario asociado en la misma consulta y evitar el problema N+1
    @Query("SELECT c FROM Cliente c JOIN FETCH c.usuario u WHERE " +
            "LOWER(c.nombres) LIKE LOWER(CONCAT('%', :terminoBusqueda, '%')) OR " +
            "LOWER(c.apellidos) LIKE LOWER(CONCAT('%', :terminoBusqueda, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :terminoBusqueda, '%'))")
    List<Cliente> findByNombresApellidosOrEmailContainingIgnoreCase(@Param("terminoBusqueda") String terminoBusqueda);

    @Query("SELECT c FROM Cliente c JOIN FETCH c.usuario")
    List<Cliente> findAllWithUsuario();
}
