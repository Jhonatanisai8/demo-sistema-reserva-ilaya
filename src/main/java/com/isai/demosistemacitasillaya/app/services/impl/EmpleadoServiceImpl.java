package com.isai.demosistemacitasillaya.app.services.impl;

import com.isai.demosistemacitasillaya.app.models.Empleado;
import com.isai.demosistemacitasillaya.app.models.emuns.EstadoEmpleado;
import com.isai.demosistemacitasillaya.app.repositorys.EmpleadoRepository;
import com.isai.demosistemacitasillaya.app.services.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    @Override
    @Transactional
    public List<Empleado> findAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    @Transactional
    public List<Empleado> searchEmpleados(String terminoBusqueda) {
        return empleadoRepository.searchEmpleados(terminoBusqueda);
    }

    @Override
    @Transactional
    public Optional<Empleado> findEmpleadoById(Integer idBuscado) {
        return empleadoRepository.findById(idBuscado);
    }

    @Override
    @Transactional
    public Empleado saveEmpleado(Empleado cliente) {
        return empleadoRepository.save(cliente);
    }

    @Override
    public void deleteEmpleadoById(Integer idBuscado) {
        empleadoRepository.deleteById(idBuscado);
    }

    @Override
    public Long countEmpleadosByEstado(EstadoEmpleado estadoEmpleado) {
        return empleadoRepository.countByEstado(estadoEmpleado);
    }


    @Override
    public Map<EstadoEmpleado, Long> getCantidadEmpleadoPorEstado() {
        Map<EstadoEmpleado, Long> empleadosEstado = new EnumMap<>(EstadoEmpleado.class);
        for (EstadoEmpleado estado : EstadoEmpleado.values()) {
            empleadosEstado.put(estado, empleadoRepository.countByEstado(estado));
        }
        return empleadosEstado;
    }

    @Override
    public Long getTotalEmpleados() {
        return empleadoRepository.count();
    }
}
