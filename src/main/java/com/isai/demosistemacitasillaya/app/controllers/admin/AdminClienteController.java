package com.isai.demosistemacitasillaya.app.controllers.admin;

import com.isai.demosistemacitasillaya.app.models.Cliente;
import com.isai.demosistemacitasillaya.app.services.impl.ClienteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/admin/clientes")
public class AdminClienteController {
    private final ClienteServiceImpl clienteService;


    @GetMapping
    public String listarClientes(Model model,
                                 @RequestParam(value = "terminoBusqueda", required = false) String terminoBusqueda) {
        List<Cliente> clientes;
        if (terminoBusqueda != null && !terminoBusqueda.trim().isEmpty()) {
            clientes = clienteService.searchClientes(terminoBusqueda);
        } else {
            clientes = clienteService.findAllClientes();
        }
        model.addAttribute("clientesBD", clientes);
        model.addAttribute("terminoBusqueda", terminoBusqueda);
        return "admin/clientes/list";
    }
}
