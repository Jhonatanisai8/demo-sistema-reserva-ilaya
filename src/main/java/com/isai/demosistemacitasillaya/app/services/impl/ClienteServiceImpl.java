package com.isai.demosistemacitasillaya.app.services.impl;

import com.isai.demosistemacitasillaya.app.models.Cliente;
import com.isai.demosistemacitasillaya.app.repositorys.ClienteRepository;
import com.isai.demosistemacitasillaya.app.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    @Transactional
    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    @Transactional
    public List<Cliente> searchClientes(String terminoBusqueda) {
        if (terminoBusqueda == null || terminoBusqueda.trim().isEmpty()) {
            return clienteRepository.findAll();
        }
        return clienteRepository.findByNombresApellidosOrEmailContainingIgnoreCase(terminoBusqueda);
    }

    @Override
    @Transactional
    public Optional<Cliente> findClienteById(Integer idBuscado) {
        return clienteRepository.findById(idBuscado);
    }

    @Override
    @Transactional
    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void deleteClienteById(Integer idBuscado) {
        clienteRepository.deleteById(idBuscado);
    }
}
