package com.isai.demosistemacitasillaya.app.services;

import com.isai.demosistemacitasillaya.app.models.Empleado;
import com.isai.demosistemacitasillaya.app.models.emuns.EstadoEmpleado;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmpleadoService {
    List<Empleado> findAllEmpleados();

    List<Empleado> searchEmpleados(String terminoBusqueda);

    Optional<Empleado> findEmpleadoById(Integer idBuscado);

    Empleado saveEmpleado(Empleado cliente);

    void deleteEmpleadoById(Integer idBuscado);

    Long countEmpleadosByEstado(EstadoEmpleado estadoEmpleado);

    Long getTotalEmpleados();

    Map<EstadoEmpleado, Long> getCantidadEmpleadoPorEstado();
}
