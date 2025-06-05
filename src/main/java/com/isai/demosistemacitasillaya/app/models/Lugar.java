package com.isai.demosistemacitasillaya.app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lugares")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lugar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lugar")
    private Integer idLugar;

    @Column(name = "nombre_lugar", nullable = false, length = 100)
    private String nombreLugar;

    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;

    @Column(name = "capacidad_total", nullable = false)
    private Integer capacidadTotal;

    @Column(name = "contacto_telefono", length = 15)
    private String contactoTelefono;

    // Relación OneToMany con Presentaciones
    @OneToMany(mappedBy = "lugar", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Presentacion> presentaciones = new HashSet<>();
}
