package com.isai.demosistemacitasillaya.app.repositorys;

import com.isai.demosistemacitasillaya.app.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository
        extends JpaRepository<Reserva, Integer> {
    // obtenemos todas las reservas, cargando el cliente, el usuario del cliente y demas datos
    @Query("SELECT r FROM Reserva r " +
            "JOIN FETCH r.cliente c " +
            "JOIN FETCH c.usuario u " +
            "JOIN FETCH r.presentacion p " +
            "JOIN FETCH p.lugar l")
    List<Reserva> findAllReservasWithDetails();

    // buscamos reservas por términos relacionados con el cliente (nombre/apellido/email)
    @Query("SELECT r FROM Reserva r " +
            "JOIN FETCH r.cliente c " +
            "JOIN FETCH c.usuario u " +
            "JOIN FETCH r.presentacion p " +
            "JOIN FETCH p.lugar l " +
            "WHERE LOWER(c.nombres) LIKE LOWER(CONCAT('%', :terminoBusqueda, '%')) OR " +
            "LOWER(c.apellidos) LIKE LOWER(CONCAT('%', :terminoBusqueda, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :terminoBusqueda, '%')) OR " +
            "LOWER(p.tituloShow) LIKE LOWER(CONCAT('%', :terminoBusqueda, '%')) OR " +
            "LOWER(l.nombreLugar) LIKE LOWER(CONCAT('%', :terminoBusqueda, '%'))")
    List<Reserva> searchReservas(@Param("terminoBusqueda") String terminoBusqueda);

}
