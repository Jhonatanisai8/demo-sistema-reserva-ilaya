package com.isai.demosistemacitasillaya.app.services;

import com.isai.demosistemacitasillaya.app.models.Reserva;

import java.util.List;
import java.util.Optional;

public interface ReservaService {
    List<Reserva> getAllReservas();

    List<Reserva> searchReservas(String terminoBusqueda);

    Optional<Reserva> findReservaById(Integer id);
}
