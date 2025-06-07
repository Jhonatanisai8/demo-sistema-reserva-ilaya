package com.isai.demosistemacitasillaya.app.services.impl;

import com.isai.demosistemacitasillaya.app.models.Lugar;
import com.isai.demosistemacitasillaya.app.repositorys.LugarRepository;
import com.isai.demosistemacitasillaya.app.services.LugarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LugarServiceImpl implements LugarService {

    private final LugarRepository lugarRepository;

    @Override
    @Transactional
    public List<Lugar> searchLugares(String terminoBusqueda) {
        return lugarRepository.searchLugares(terminoBusqueda);
    }

    @Override
    @Transactional
    public List<Lugar> findAllLugares() {
        return lugarRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Lugar> findLugarById(Integer id) {
        return lugarRepository.findById(id);
    }

    @Override
    @Transactional
    public Lugar saveLugar(Lugar lugar) {
        if (lugar.getNombreLugar() == null || lugar.getNombreLugar().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del lugar no puede estar vacío.");
        }
        if (lugar.getDireccion() == null || lugar.getDireccion().trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección del lugar no puede estar vacía.");
        }
        return lugarRepository.save(lugar);
    }

    @Override
    public void deleteLugar(Integer idLugarRequest) {
        lugarRepository.deleteById(idLugarRequest);
    }
}
