package com.isai.demosistemacitasillaya.app.services.impl;

import com.isai.demosistemacitasillaya.app.models.Reserva;
import com.isai.demosistemacitasillaya.app.models.emuns.EstadoReserva;
import com.isai.demosistemacitasillaya.app.repositorys.ReservaRepository;
import com.isai.demosistemacitasillaya.app.services.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;

    @Override
    @Transactional
    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    @Override
    @Transactional
    public List<Reserva> searchReservas(String terminoBusqueda) {
        return reservaRepository.searchReservas(terminoBusqueda);
    }

    @Override
    @Transactional
    public Optional<Reserva> findReservaById(Integer id) {
        return reservaRepository.findById(id);
    }

    @Override
    public Long countReservasSegunEstado(EstadoReserva estado) {
        return reservaRepository.countByEstado(estado);
    }

    @Override
    public Map<EstadoReserva, Long> getCantidadReservasPorEstado() {
        Map<EstadoReserva, Long> reservasPorEstado = new EnumMap<>(EstadoReserva.class);
        for (EstadoReserva estado : EstadoReserva.values()) {
            reservasPorEstado.put(estado, reservaRepository.countByEstado(estado));
        }
        return reservasPorEstado;
    }
}
