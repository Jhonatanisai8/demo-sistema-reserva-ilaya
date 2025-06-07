package com.isai.demosistemacitasillaya.app.controllers.admin;

import com.isai.demosistemacitasillaya.app.models.Usuario;
import com.isai.demosistemacitasillaya.app.services.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/usuarios")
public class AdminUsuarioController {

    private final UsuarioServiceImpl usuarioService;

    @GetMapping
    public String listarUsuarios(Model model,
                                 @RequestParam(value = "terminoBusqueda", required = false) String terminoBusqueda) {
        List<Usuario> usuarios;

        if (terminoBusqueda != null && !terminoBusqueda.trim().isEmpty()) {
            usuarios = usuarioService.searchUsuarios(terminoBusqueda);
        } else {
            usuarios = usuarioService.getAllUsuarios();
        }
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("terminoBusqueda", terminoBusqueda);
        return "admin/usuarios/list";
    }
}
