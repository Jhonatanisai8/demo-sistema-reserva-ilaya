package com.isai.demosistemacitasillaya.app.repositorys;

import com.isai.demosistemacitasillaya.app.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository
        extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    List<Usuario> findByNombreUsuarioContainingIgnoreCaseOrEmailContainingIgnoreCase(String nombreUsuario, String email);

    Optional<Usuario> findByEmail(String email);

    boolean existsByNombreUsuario(String nombreUsuario);

    boolean existsByEmail(String email);
}
