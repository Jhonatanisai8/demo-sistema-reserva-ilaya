package com.isai.demosistemacitasillaya.app.controllers.cliente;

import com.isai.demosistemacitasillaya.app.models.*;
import com.isai.demosistemacitasillaya.app.services.impl.ClienteServiceImpl;
import com.isai.demosistemacitasillaya.app.services.impl.PresentacionServiceImpl;
import com.isai.demosistemacitasillaya.app.services.impl.ReservaServiceImpl;
import com.isai.demosistemacitasillaya.app.services.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping(path = "/cliente/reservaciones")
@RequiredArgsConstructor
public class ClienteReservaController {

    private final ReservaServiceImpl reservaServiceImpl;
    private final PresentacionServiceImpl presentacionServiceImpl;
    private final ClienteServiceImpl clienteServiceImpl;
    private final UsuarioServiceImpl usuarioServiceImpl;

    private Cliente obtenerClienteConectado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Optional<Usuario> usuarioBD = usuarioServiceImpl.findByNombreUsuario(userName);
        if (usuarioBD.isEmpty()) {
            throw new IllegalStateException("Usuario no encontrado " + userName);
        }
        Optional<Cliente> clienteBD = clienteServiceImpl.getClienteByUsuario(usuarioBD.get());
        if (clienteBD.isEmpty()) {
            throw new IllegalStateException("Cliente no encontrado " + userName);
        }
        return clienteBD.get();
    }

    @GetMapping(path = "")
    public String listarReservas(Model model) {
        Cliente cliente = obtenerClienteConectado();
        model.addAttribute("reservas", reservaServiceImpl.getReservasByCliente(cliente));
        return "cliente/reservaciones/list";
    }

    @GetMapping(path = "/nueva-reserva")
    public String mostrarFormularioNuevaReserva(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("presentacion", new Presentacion());
        model.addAttribute("lugar", new Lugar());
        return "cliente/reservaciones/nueva-reserva";
    }

    @PostMapping(path = "/nueva-reserva")
    public String crearNuevaReserva(@ModelAttribute("reserva") Reserva reserva,
                                    @ModelAttribute("presentacion") Presentacion presentacion,
                                    @ModelAttribute("lugar") Lugar lugar,
                                    RedirectAttributes redirectAttributes) {
        try {
            Cliente cliente = obtenerClienteConectado();
            reserva.setCliente(cliente);
            reserva.setFechaContrato(LocalDate.now());
            reservaServiceImpl.crearReserva(reserva, presentacion, lugar);
            redirectAttributes.addFlashAttribute("successMessage", "La propuesta de presentación, lugar y contrato han sido creados correctamente.");
            return "redirect:/cliente/reservaciones";
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/cliente/reservaciones";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
            ex.printStackTrace();
            return "redirect:/cliente/reservaciones/nueva-reserva";
        }
    }

}
