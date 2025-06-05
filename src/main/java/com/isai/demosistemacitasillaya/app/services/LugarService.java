package com.isai.demosistemacitasillaya.app.services;

import com.isai.demosistemacitasillaya.app.models.Lugar;

import java.util.List;
import java.util.Optional;

public interface LugarService {

    List<Lugar> searchLugares(String terminoBusqueda);

    List<Lugar> findAllLugares();

    Optional<Lugar> findLugarById(Integer id);
}
