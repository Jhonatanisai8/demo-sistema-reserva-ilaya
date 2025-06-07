package com.isai.demosistemacitasillaya.app.controllers.login;

import com.isai.demosistemacitasillaya.app.models.Cliente;
import com.isai.demosistemacitasillaya.app.models.Usuario;
import com.isai.demosistemacitasillaya.app.models.emuns.RolUsuario;
import com.isai.demosistemacitasillaya.app.services.impl.ClienteServiceImpl;
import com.isai.demosistemacitasillaya.app.services.impl.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class AuthController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private ClienteServiceImpl clienteService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("cliente", new Cliente());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("usuario") Usuario usuario,
                               BindingResult usuarioResult,
                               @Valid @ModelAttribute("cliente") Cliente cliente,
                               BindingResult clienteResult,
                               RedirectAttributes redirectAttributes,
                               Model model) {

        if (usuarioResult.hasErrors() || clienteResult.hasErrors()) {
            model.addAttribute("errorMessage", "Por favor, corrige los errores en el formulario.");
            return "auth/register";
        }

        if (usuarioService.existsByNombreUsuario(usuario.getNombreUsuario())) {
            usuarioResult.rejectValue("nombreUsuario", "error.usuario", "El nombre de usuario ya está en uso.");
        }
        if (usuarioService.existsByEmail(usuario.getEmail())) {
            usuarioResult.rejectValue("email", "error.usuario", "El email ya está registrado.");
        }

        if (usuarioResult.hasErrors() || clienteResult.hasErrors()) {
            model.addAttribute("errorMessage", "Por favor, corrige los errores en el formulario.");
            return "auth/register";
        }

        try {
            //  encriptamos la contraseña antes de guardar en la base de datos
            usuario.setContrasenaHash(passwordEncoder.encode(usuario.getContrasenaHash()));
            usuario.setRol(RolUsuario.ADMIN);
            usuario.setFechaRegistro(LocalDateTime.now());

            Usuario savedUsuario = usuarioService.saveUsuario(usuario);

            // vinculamos el Cliente al Usuario y guardar el Cliente
            cliente.setUsuario(savedUsuario);
            clienteService.saveCliente(cliente);

            redirectAttributes.addFlashAttribute("successMessage", "Registro exitoso. ¡Ahora puedes iniciar sesión!");
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error al registrar el usuario: " + e.getMessage());
            return "auth/register";
        }
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/admin/panel")
    public String adminPanel() {
        return "admin/dashboard/index";
    }
}
