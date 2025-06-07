package com.isai.demosistemacitasillaya.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank; // Importa estas validaciones
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", unique = true, nullable = false)
    private Usuario usuario;

    @NotBlank(message = "Los nombres no pueden estar vacíos.")
    @Size(max = 50, message = "Los nombres no pueden exceder los 50 caracteres.")
    @Column(name = "nombres", nullable = false, length = 50)
    private String nombres;

    @NotBlank(message = "Los apellidos no pueden estar vacíos.")
    @Size(max = 50, message = "Los apellidos no pueden exceder los 50 caracteres.")
    @Column(name = "apellidos", nullable = false, length = 50)
    private String apellidos;

    @Size(max = 15, message = "El teléfono no puede exceder los 15 caracteres.")
    @Column(name = "telefono", length = 15)
    private String telefono;

    // Relación OneToMany con Reservas
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Reserva> reservas = new HashSet<>();
}