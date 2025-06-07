package com.isai.demosistemacitasillaya.app.services.impl;

import com.isai.demosistemacitasillaya.app.models.Presentacion;
import com.isai.demosistemacitasillaya.app.repositorys.PresentacionRepository;
import com.isai.demosistemacitasillaya.app.services.PresentacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PresentacionServiceImpl
        implements PresentacionService {
    private final PresentacionRepository presentacionRepository;

    @Override
    public List<Presentacion> getAllPresentaciones() {
        return presentacionRepository.findAll();
    }

    @Override
    public List<Presentacion> getPresentacionByTerminoBusqueda(String terminoBusqueda) {
        return presentacionRepository.findByTituloShow(terminoBusqueda);
    }

    @Override
    public Long getTotalPresentaciones() {
        return presentacionRepository.count();
    }

    @Override
    public Optional<Presentacion> getPresentacionById(Integer idPresentacionRequest) {
        return presentacionRepository.findById(idPresentacionRequest);
    }

    @Override
    public Presentacion guardarPresentacion(Presentacion presentacion) {
        if (presentacion.getFecha() == null || presentacion.getFecha().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de la presentación debe ser hoy o en el futuro.");
        }
        if (presentacion.getHoraInicio() == null || presentacion.getHoraFin() == null) {
            throw new IllegalArgumentException("La hora de inicio y fin de la presentación son obligatorias.");
        }
        if (presentacion.getHoraInicio().isAfter(presentacion.getHoraFin())) {
            throw new IllegalArgumentException("La hora de inicio de la presentación no puede ser después de la hora de fin.");
        }
        return presentacionRepository.save(presentacion);
    }
}
