package com.isai.demosistemacitasillaya.app.services;

import com.isai.demosistemacitasillaya.app.models.Usuario;
import com.isai.demosistemacitasillaya.app.repositorys.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNombreUsuario(username);

        return usuarioOptional.map(usuario -> {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(usuario.getNombreUsuario())
                    .password(usuario.getContrasenaHash())
                    .roles(usuario.getRol().name()) // Asigna el rol (ej. "ADMIN", "CLIENTE")
                    .build();
        }).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }
}
