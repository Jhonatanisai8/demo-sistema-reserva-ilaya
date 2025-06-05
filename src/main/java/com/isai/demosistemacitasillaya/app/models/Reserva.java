package com.isai.demosistemacitasillaya.app.models;

import com.isai.demosistemacitasillaya.app.models.emuns.EstadoReserva;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoReserva estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(name = "fecha_contradto")
    private LocalDate fechaContradto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_presentacion", nullable = false)
    private Presentacion presentacion;

    @Column(name = "cantidad_entradas", nullable = false)
    private Integer cantidadEntradas;

    @Column(name = "fecha_reserva", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaReserva;


    @Column(name = "monto_acordado", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoAcordado;

    @NotNull
    @Size(max = 500)
    @Column(name = "notas_contrato")
    private String notasContrato;

}
