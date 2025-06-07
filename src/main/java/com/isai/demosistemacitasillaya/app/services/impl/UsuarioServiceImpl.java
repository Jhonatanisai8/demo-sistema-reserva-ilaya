package com.isai.demosistemacitasillaya.app.services.impl;

import com.isai.demosistemacitasillaya.app.models.Usuario;
import com.isai.demosistemacitasillaya.app.repositorys.UsuarioRepository;
import com.isai.demosistemacitasillaya.app.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl
        implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public Long getTotalUsuarios() {
        return usuarioRepository.count();
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    @Override
    public Boolean existsByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }


    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> searchUsuarios(String terminoBusqueda) {
        if (terminoBusqueda == null || terminoBusqueda.trim().isEmpty()) {
            return usuarioRepository.findAll(); // Si el término está vacío, devuelve todos
        }
        return usuarioRepository.findByNombreUsuarioContainingIgnoreCaseOrEmailContainingIgnoreCase(terminoBusqueda, terminoBusqueda);
    }
    public Optional<Usuario> getUsuarioById(Integer id) {
        return usuarioRepository.findById(id);
    }
}
