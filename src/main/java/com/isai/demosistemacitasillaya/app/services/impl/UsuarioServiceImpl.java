package com.isai.demosistemacitasillaya.app.services.impl;

import com.isai.demosistemacitasillaya.app.repositorys.UsuarioRepository;
import com.isai.demosistemacitasillaya.app.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl
        implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public Long getTotalUsuarios() {
        return usuarioRepository.count();
    }
}
