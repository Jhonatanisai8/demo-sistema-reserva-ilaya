package com.isai.demosistemacitasillaya.app.controllers.admin;

import com.isai.demosistemacitasillaya.app.models.Reserva;
import com.isai.demosistemacitasillaya.app.services.impl.ReservaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/reservas")
public class AdminReservaController {
    private final ReservaServiceImpl reservaServiceImpl;

    @GetMapping
    public String listarReservas(Model model,
                                 @RequestParam(value = "terminoBusqueda", required = false) String terminoBusqueda) {
        List<Reserva> reservas;
        if (terminoBusqueda != null && !terminoBusqueda.trim().isEmpty()) {
            reservas = reservaServiceImpl.searchReservas(terminoBusqueda);
        } else {
            reservas = reservaServiceImpl.getAllReservas();
        }
        model.addAttribute("reservasBD", reservas);
        model.addAttribute("terminoBusqueda", terminoBusqueda);
        return "admin/reservas/list";
    }

}
