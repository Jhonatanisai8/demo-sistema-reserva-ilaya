package com.isai.demosistemacitasillaya.app.controllers;

import com.isai.demosistemacitasillaya.app.models.Cliente;
import com.isai.demosistemacitasillaya.app.services.impl.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/admin/clientes")
public class AdminClienteController {

    private final ClienteServiceImpl clienteServiceImpl;

    @Autowired
    public AdminClienteController(ClienteServiceImpl clienteServiceImpl) {
        this.clienteServiceImpl = clienteServiceImpl;
    }

    @GetMapping(path = "")
    public String mostrarClientes(Model model) {
        //llamamos al metodo que lista los clientes de la base de datos
        List<Cliente> clientesBD = clienteServiceImpl.getClientes();
        model.addAttribute("clientesBD", clientesBD);
        return "admin/lista-clientes";
    }

    @GetMapping(path = "/nuevo")
    public String guardarCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "admin/registrar-cliente";
    }


}
