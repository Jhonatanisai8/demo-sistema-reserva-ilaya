package com.isai.demosistemacitasillaya.app.services.impl;

import com.isai.demosistemacitasillaya.app.models.Cliente;
import com.isai.demosistemacitasillaya.app.models.Lugar;
import com.isai.demosistemacitasillaya.app.models.Presentacion;
import com.isai.demosistemacitasillaya.app.models.Reserva;
import com.isai.demosistemacitasillaya.app.models.emuns.EstadoReserva;
import com.isai.demosistemacitasillaya.app.repositorys.ReservaRepository;
import com.isai.demosistemacitasillaya.app.services.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;

    private final PresentacionServiceImpl presentacionServiceImpl;

    private final LugarServiceImpl lugarServiceImpl;

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

    @Transactional
    @Override
    public Reserva crearReserva(Reserva reserva, Presentacion presentacion, Lugar lugar) {
        Lugar lugarGuardado = lugarServiceImpl.saveLugar(lugar);
        presentacion.setLugar(lugarGuardado);
        Presentacion presentacionGuardada = presentacionServiceImpl.guardarPresentacion(presentacion);
        reserva.setPresentacion(presentacionGuardada);
        reserva.setFechaContrato(LocalDate.now());
        if (reserva.getEstado() == null) {
            reserva.setEstado(EstadoReserva.PENDIENTE);
        }

        if (reserva.getFechaContrato() == null) {
            throw new IllegalArgumentException("La fecha del contrato es obligatoria.");
        }
        if (reserva.getFechaContrato().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha del contrato no puede ser en el futuro.");
        }
        return reservaRepository.save(reserva);
    }

    @Override
    @Transactional
    public List<Reserva> getReservasByCliente(Cliente cliente) {
        return reservaRepository.findByClienteOrderByFechaContratoAsc(cliente);
    }

    @Override
    @Transactional
    public void cancelarReserva(Integer idReserva) {
        Reserva reservaBD = reservaRepository.findById(idReserva).
                orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada"));
        if (reservaBD.getEstado() == EstadoReserva.CONFIRMADA || reservaBD.getEstado() == EstadoReserva.PENDIENTE) {
            reservaBD.setEstado(EstadoReserva.CANCELADA);
            reservaRepository.save(reservaBD);
        } else {
            throw new IllegalArgumentException("La reserva no se puede cancelar en su estado actual: " + reservaBD.getEstado().displayName());
        }

    }
}
