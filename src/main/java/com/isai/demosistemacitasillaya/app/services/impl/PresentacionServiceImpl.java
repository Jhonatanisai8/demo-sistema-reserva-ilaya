package com.isai.demosistemacitasillaya.app.services.impl;

import com.isai.demosistemacitasillaya.app.models.Presentacion;
import com.isai.demosistemacitasillaya.app.repositorys.PresentacionRepository;
import com.isai.demosistemacitasillaya.app.services.PresentacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return presentacionRepository.findByTituloContainingIgnoreCase(terminoBusqueda);
    }
}
