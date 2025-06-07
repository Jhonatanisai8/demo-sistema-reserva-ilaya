package com.isai.demosistemacitasillaya.app.controllers.admin;

import com.isai.demosistemacitasillaya.app.models.Reserva;
import com.isai.demosistemacitasillaya.app.models.emuns.EstadoReserva;
import com.isai.demosistemacitasillaya.app.services.impl.ReservaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("estadosReserva", EstadoReserva.values());
        return "admin/reservas/list";
    }

    @GetMapping("/{id}")
    public String verDetallesReserva(@PathVariable("id") Integer id, Model model) {
        Reserva reserva = reservaServiceImpl.findReservaById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada con ID: " + id));
        model.addAttribute("reserva", reserva);
        return "admin/reservas/detail";
    }


    @PostMapping("/confirmar/{id}")
    public String confirmarReserva(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            reservaServiceImpl.confirmarReserva(id);
            redirectAttributes.addFlashAttribute("successMessage", "Reserva #" + id + " ha sido confirmada exitosamente.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al confirmar reserva: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ocurrió un error inesperado al confirmar la reserva #" + id);
            e.printStackTrace();
        }
        return "redirect:/admin/reservas";
    }

    @PostMapping("/cancelar/{id}")
    public String cancelarReserva(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            reservaServiceImpl.cancelarReservaAdmin(id);
            redirectAttributes.addFlashAttribute("successMessage", "Reserva #" + id + " ha sido cancelada.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al cancelar reserva: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ocurrió un error inesperado al cancelar la reserva #" + id);
            e.printStackTrace();
        }
        return "redirect:/admin/reservas";
    }

    @PostMapping("/realizada/{id}")
    public String marcarComoRealizada(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            reservaServiceImpl.marcarComoRealizada(id);
            redirectAttributes.addFlashAttribute("successMessage", "Reserva #" + id + " ha sido marcada como REALIZADA.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al marcar como realizada: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ocurrió un error inesperado al marcar la reserva #" + id + " como realizada.");
            e.printStackTrace();
        }
        return "redirect:/admin/reservas";
    }

}
