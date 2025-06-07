package com.isai.demosistemacitasillaya.app.services;

import com.isai.demosistemacitasillaya.app.models.Cliente;
import com.isai.demosistemacitasillaya.app.models.Lugar;
import com.isai.demosistemacitasillaya.app.models.Presentacion;
import com.isai.demosistemacitasillaya.app.models.Reserva;
import com.isai.demosistemacitasillaya.app.models.emuns.EstadoReserva;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ReservaService {
    List<Reserva> getAllReservas();

    List<Reserva> searchReservas(String terminoBusqueda);

    Optional<Reserva> findReservaById(Integer id);

    Long countReservasSegunEstado(EstadoReserva estado);

    Map<EstadoReserva, Long> getCantidadReservasPorEstado();

    Reserva crearReserva(Reserva reserva, Presentacion presentacion, Lugar lugar);

    List<Reserva> getReservasByCliente(Cliente cliente);

    void cancelarReserva(Integer idReserva);
}
