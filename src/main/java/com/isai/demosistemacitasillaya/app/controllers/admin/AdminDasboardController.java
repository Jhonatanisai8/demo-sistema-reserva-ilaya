package com.isai.demosistemacitasillaya.app.controllers.admin;

import com.isai.demosistemacitasillaya.app.models.emuns.EstadoEmpleado;
import com.isai.demosistemacitasillaya.app.models.emuns.EstadoReserva;
import com.isai.demosistemacitasillaya.app.services.impl.EmpleadoServiceImpl;
import com.isai.demosistemacitasillaya.app.services.impl.PresentacionServiceImpl;
import com.isai.demosistemacitasillaya.app.services.impl.ReservaServiceImpl;
import com.isai.demosistemacitasillaya.app.services.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping(path = "/admin/dashboard")
@RequiredArgsConstructor
public class AdminDasboardController {
    private final PresentacionServiceImpl presentacionService;
    private final UsuarioServiceImpl usuarioService;
    private final EmpleadoServiceImpl empleadoService;
    private final ReservaServiceImpl reservaService;

    @GetMapping
    public String showDashboard(Model model) {

        long totalPresentaciones = presentacionService.getTotalPresentaciones();
        model.addAttribute("totalPresentaciones", totalPresentaciones);


        Map<EstadoReserva, Long> reservasPorEstado = reservaService.getCantidadReservasPorEstado();
        model.addAttribute("reservasPorEstado", reservasPorEstado);


        Map<EstadoEmpleado, Long> empleadosPorEstado = empleadoService.getCantidadEmpleadoPorEstado();
        model.addAttribute("empleadosPorEstado", empleadosPorEstado);
        model.addAttribute("totalEmpleados", empleadoService.getTotalEmpleados());

        long totalUsuarios = usuarioService.getTotalUsuarios();
        model.addAttribute("totalUsuarios", totalUsuarios);

        return "admin/dashboard/index";
    }
}
