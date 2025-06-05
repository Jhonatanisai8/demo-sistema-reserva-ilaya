package com.isai.demosistemacitasillaya.app.controllers.admin;

import com.isai.demosistemacitasillaya.app.models.Presentacion;
import com.isai.demosistemacitasillaya.app.services.impl.PresentacionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/presentaciones")
public class AdminPresentacionController {

    private final PresentacionServiceImpl presentacionService;

    @GetMapping
    public String listarPresentaciones(Model model,
                                       @RequestParam(value = "terminoBusqueda", required = false) String terminoBusqueda) {
        List<Presentacion> presentacions;
        if (terminoBusqueda != null && !terminoBusqueda.trim().isEmpty()) {
            presentacions = presentacionService.getPresentacionByTerminoBusqueda(terminoBusqueda);
        } else {
            presentacions = presentacionService.getAllPresentaciones();
        }
        model.addAttribute("presentaciones", presentacions);
        model.addAttribute("terminoBusqueda", terminoBusqueda);
        return "admin/presentaciones/list";
    }

//    @GetMapping("/{id}")
//    public String verDetallesReserva(@PathVariable("id") Integer id, Model model) {
//        Reserva reserva = reservaServiceImpl.findReservaById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada con ID: " + id));
//        model.addAttribute("reserva", reserva);
//        return "admin/presentacions/detail";
//    }

}
