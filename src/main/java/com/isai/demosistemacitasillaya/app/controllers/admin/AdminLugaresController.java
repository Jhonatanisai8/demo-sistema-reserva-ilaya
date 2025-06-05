package com.isai.demosistemacitasillaya.app.controllers.admin;

import com.isai.demosistemacitasillaya.app.models.Lugar;
import com.isai.demosistemacitasillaya.app.services.impl.LugarServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/lugares")
public class AdminLugaresController {

    private final LugarServiceImpl lugarService;

    @GetMapping
    public String listarLugares(Model model,
                                @RequestParam(value = "terminoBusqueda", required = false) String terminoBusqueda) {
        List<Lugar> lugares;
        if (terminoBusqueda != null && !terminoBusqueda.trim().isEmpty()) {
            lugares = lugarService.searchLugares(terminoBusqueda);
        } else {
            lugares = lugarService.findAllLugares();
        }
        model.addAttribute("lugaresBD", lugares);
        model.addAttribute("terminoBusqueda", terminoBusqueda);
        return "admin/lugares/list";
    }

    // Opcional: Método para ver detalles de un lugar específico
    @GetMapping("/{id}")
    public String verDetallesLugar(@PathVariable("id") Integer id, Model model) {
        Lugar lugar = lugarService.findLugarById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lugar no encontrado con ID: " + id));
        model.addAttribute("lugar", lugar);
        return "admin/lugares/details";
    }
}
