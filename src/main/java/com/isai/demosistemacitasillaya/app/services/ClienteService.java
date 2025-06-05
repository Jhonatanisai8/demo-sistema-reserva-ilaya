package com.isai.demosistemacitasillaya.app.services;

import com.isai.demosistemacitasillaya.app.models.Cliente;

import java.util.List;
import java.util.Optional;

//aqui solo definimos nuestros metodos
public interface ClienteService {
    List<Cliente> findAllClientes();

    List<Cliente> searchClientes(String terminoBusqueda);

    Optional<Cliente> findClienteById(Integer idBuscado);

    Cliente saveCliente(Cliente cliente);

    void deleteClienteById(Integer idBuscado);
}
