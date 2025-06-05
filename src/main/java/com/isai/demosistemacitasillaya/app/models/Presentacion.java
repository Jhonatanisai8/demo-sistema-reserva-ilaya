package com.isai.demosistemacitasillaya.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "presentaciones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Presentacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presentacion")
    private Integer idPresentacion;

    @NotBlank(message = "El título del show no puede estar vacío.")
    @Size(max = 100, message = "El título del show no puede exceder los 100 caracteres.")
    @Column(name = "titulo_show", nullable = false, length = 100)
    private String tituloShow;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres.")
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @NotNull(message = "La fecha de la presentación es obligatoria.")
    @FutureOrPresent(message = "La fecha de la presentación no puede ser en el pasado.")
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @NotNull(message = "La hora de inicio es obligatoria.")
    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria.")
    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lugar", nullable = false)
    @NotNull(message = "El lugar de la presentación es obligatorio.")
    private Lugar lugar;

}
