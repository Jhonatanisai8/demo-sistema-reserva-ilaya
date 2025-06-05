package com.isai.demosistemacitasillaya.app.services;

import com.isai.demosistemacitasillaya.app.models.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    List<Empleado> findAllEmpleados();

    List<Empleado> searchEmpleados(String terminoBusqueda);

    Optional<Empleado> findEmpleadoById(Integer idBuscado);

    Empleado saveEmpleado(Empleado cliente);

    void deleteEmpleadoById(Integer idBuscado);
}
