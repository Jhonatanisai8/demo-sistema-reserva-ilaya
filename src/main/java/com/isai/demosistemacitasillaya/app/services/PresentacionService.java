package com.isai.demosistemacitasillaya.app.services;

import com.isai.demosistemacitasillaya.app.models.Presentacion;

import java.util.List;
import java.util.Optional;

public interface PresentacionService {
    List<Presentacion> getAllPresentaciones();

    List<Presentacion> getPresentacionByTerminoBusqueda(String terminoBusqueda);

    Long getTotalPresentaciones();

    Optional<Presentacion> getPresentacionById(Integer idPresentacionRequest);
}
