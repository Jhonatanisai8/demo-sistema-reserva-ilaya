package com.isai.demosistemacitasillaya.app.models;

import com.isai.demosistemacitasillaya.app.models.emuns.EstadoReserva;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "reservas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Integer idReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    @NotNull(message = "El cliente que contrata es obligatorio.")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_presentacion", nullable = false)
    @NotNull(message = "La presentación a contratar es obligatoria.")
    private Presentacion presentacion;

    @NotNull(message = "La fecha del contrato es obligatoria.")
    @PastOrPresent(message = "La fecha del contrato no puede ser en el futuro.")
    @Column(name = "fecha_contrato", nullable = false)
    private LocalDate fechaContrato;

    @Column(name = "fecha_creacion_registro", nullable = false, updatable = false)
    private LocalDateTime fechaCreacionRegistro; // El tipo de dato para fecha y hora

    @NotNull(message = "El monto acordado es obligatorio.")
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto acordado debe ser un valor positivo.")
    @Digits(integer = 10, fraction = 2, message = "El monto acordado debe tener hasta 10 dígitos enteros y 2 decimales.")
    @Column(name = "monto_acordado", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoAcordado;

    @NotNull(message = "El estado del contrato es obligatorio.")
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_reserva", nullable = false)
    private EstadoReserva estado;

    @Size(max = 500, message = "Las notas del contrato no pueden exceder los 500 caracteres.")
    @Column(name = "notas_contrato", length = 500)
    private String notasContrato;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacionRegistro = LocalDateTime.now();
        if (this.estado == null) {
            this.estado = EstadoReserva.PENDIENTE;
        }
    }
}