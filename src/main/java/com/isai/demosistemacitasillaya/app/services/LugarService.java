package com.isai.demosistemacitasillaya.app.services;

import com.isai.demosistemacitasillaya.app.models.Lugar;

import java.util.List;

public interface LugarService {
    List<Lugar> searchLugares(String terminoBusqueda);
}
