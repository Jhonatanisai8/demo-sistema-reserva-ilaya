package com.isai.demosistemacitasillaya.app.repositorys;

import com.isai.demosistemacitasillaya.app.models.Presentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresentacionRepository
        extends JpaRepository<Presentacion, Integer> {

    List<Presentacion> findByTituloContainingIgnoreCase(String titulo);
}
