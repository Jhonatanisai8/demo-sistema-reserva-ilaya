package com.isai.demosistemacitasillaya.app.controllers.admin;

import com.isai.demosistemacitasillaya.app.models.Empleado;
import com.isai.demosistemacitasillaya.app.models.emuns.EstadoEmpleado;
import com.isai.demosistemacitasillaya.app.services.impl.EmpleadoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        model.addAttribute("empleados", empleadoList);
        model.addAttribute("terminoBusqueda", terminoBusqueda);
        return "admin/empleados/list";
    }

    @GetMapping(path = "/registrar")
    public String mostrarFormularioRegistroEmpleado(
            Model model) {
        //si no hay un objeto de la tipo empleado osea en la vista no se cre
        if (!model.containsAttribute("empleado")) {
            model.addAttribute("empleado", new Empleado());
        }

        //agremos cargos a la vista
        model.addAttribute("cargos", Arrays.asList("Manager", "DJ Oficial", "Hype Man", "Ingeniero de Sonido", "Asistente de Gira", "Merchandising"));
        model.addAttribute("estadosEmpleado", EstadoEmpleado.values());
        return "admin/empleados/create";
    }

    @PostMapping("/registrar")
    public String registrarEmpleado(@Valid @ModelAttribute("empleado") Empleado empleado,
                                    BindingResult result,
                                    RedirectAttributes redirectAttributes,
                                    Model model) {
        if (result.hasErrors()) {
            model.addAttribute("estadosEmpleado", EstadoEmpleado.values());
            model.addAttribute("cargos", Arrays.asList("Manager", "DJ Oficial", "Hype Man", "Ingeniero de Sonido", "Asistente de Gira", "Merchandising"));
            model.addAttribute("errorMessage", "Por favor, corrige los errores en el formulario.");
            return "admin/empleados/create";
        }

        try {
            empleadoService.saveEmpleado(empleado);
            redirectAttributes.addFlashAttribute("successMessage", "Empleado registrado exitosamente!");
            return "redirect:/admin/empleados";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error al registrar el empleado: " + e.getMessage());
            model.addAttribute("estadosEmpleado", EstadoEmpleado.values());
            model.addAttribute("cargos", Arrays.asList("Manager", "DJ Oficial", "Hype Man", "Ingeniero de Sonido", "Asistente de Gira", "Merchandising"));
            return "admin/empleados/create";
        }
    }


    @GetMapping(path = "/{idEmpleado}")
    public String verDetallesEmpleado(@PathVariable Integer idEmpleado, Model model) {
        Empleado empleado = empleadoService.findEmpleadoById(idEmpleado)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado con ID: " + idEmpleado));
        model.addAttribute("empleado", empleado);
        return "admin/empleados/detail";
    }

    @GetMapping(path = "/editar/{idEmpleado}")
    public String mostrarFormularioEditarEmpleado(@PathVariable Integer idEmpleado, Model model, RedirectAttributes redirectAttributes) {
        Optional<Empleado> empleadoOptional = empleadoService.findEmpleadoById(idEmpleado);
        if (empleadoOptional.isPresent()) {
            model.addAttribute("empleado", empleadoOptional.get());
            model.addAttribute("estadosEmpleado", EstadoEmpleado.values());
            model.addAttribute("cargos", Arrays.asList("Manager", "DJ Oficial", "Hype Man", "Ingeniero de Sonido", "Asistente de Gira", "Merchandising"));
            return "admin/empleados/edit";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Error: Empleado no encontrado.");
            return "redirect:/admin/empleados";
        }
    }

    @PostMapping(path = "/editar/{idEmpleado}")
    public String editarEmpleado(@PathVariable("idEmpleado") Integer idEmpleado,
                                 @Valid @ModelAttribute("empleado") Empleado empleado,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("estadosEmpleado", EstadoEmpleado.values());
            model.addAttribute("cargos", Arrays.asList("Manager", "DJ Oficial", "Hype Man", "Ingeniero de Sonido", "Asistente de Gira", "Merchandising"));
            model.addAttribute("errorMessage", "Por favor, corrige los errores en el formulario.");
            return "admin/empleados/edit";
        }
        try {
            empleado.setIdEmpleado(idEmpleado);
            empleadoService.saveEmpleado(empleado);
            redirectAttributes.addFlashAttribute("successMessage", "Empleado actualizado exitosamente!");
            return "redirect:/admin/empleados";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error al actualizar el empleado: " + e.getMessage());
            model.addAttribute("estadosEmpleado", EstadoEmpleado.values());
            model.addAttribute("cargos", Arrays.asList("Manager", "DJ Oficial", "Hype Man", "Ingeniero de Sonido", "Asistente de Gira", "Merchandising"));
            return "admin/empleados/edit";
        }
    }
}
