package com.isai.demosistemacitasillaya.app.services;

import com.isai.demosistemacitasillaya.app.models.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Long getTotalUsuarios();

    Usuario saveUsuario(Usuario usuario);

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    Boolean existsByNombreUsuario(String nombreUsuario);

    Boolean existsByEmail(String email);
}
