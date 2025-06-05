package com.isai.demosistemacitasillaya.app.controllers.admin;

import com.isai.demosistemacitasillaya.app.models.Empleado;
import com.isai.demosistemacitasillaya.app.services.impl.EmpleadoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "/admin/empleados")
@RequiredArgsConstructor
public class AdminEmpleadoController {

    private final EmpleadoServiceImpl empleadoService;

    @GetMapping(path = "")
    public String listarEmpleados(
            Model model,
            @RequestParam(value = "terminoBusqueda", required = false) String terminoBusqueda) {
        List<Empleado> empleadoList;
        if (terminoBusqueda != null && !terminoBusqueda.isEmpty()) {
            empleadoList = empleadoService.searchEmpleados(terminoBusqueda);
        } else {
            empleadoList = empleadoService.findAllEmpleados();
        }
        model.addAttribute("empleadoList", empleadoList);
        model.addAttribute("terminoBusqueda", terminoBusqueda);
        return "admin/empleados/list";
    }
}
