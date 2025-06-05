package com.isai.demosistemacitasillaya.app.repositorys;

import com.isai.demosistemacitasillaya.app.models.Presentacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresentacionRepository
        extends JpaRepository<Presentacion, Integer> {

    List<Presentacion> findByTituloShow(@NotBlank(message = "El título del show no puede estar vacío.") @Size(max = 100, message = "El título del show no puede exceder los 100 caracteres.") String tituloShow);
}
