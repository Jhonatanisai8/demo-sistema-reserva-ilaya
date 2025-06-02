package com.isai.demosistemacitasillaya.app.services;

import com.isai.demosistemacitasillaya.app.models.Cliente;

import java.util.List;

//aqui solo definimos nuestros metodos
public interface ClienteService {
    List<Cliente> getClientes();

    Cliente guardarCliente(Cliente cliente);
}
