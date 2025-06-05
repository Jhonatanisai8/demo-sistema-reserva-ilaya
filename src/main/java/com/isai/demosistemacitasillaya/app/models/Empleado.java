package com.isai.demosistemacitasillaya.app.models;

import com.isai.demosistemacitasillaya.app.models.emuns.EstadoEmpleado;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "empleados")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "nombres", nullable = false, length = 50)
    private String nombres;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "apellidos", nullable = false, length = 50)
    private String apellidos;

    @NotNull
    @Size(max = 8)
    @Column(name = "dni", unique = true, length = 8)
    private String dni;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @NotEmpty
    @Size(max = 9)
    @Column(name = "telefono", length = 9)
    private String telefono;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @NotNull
    @Size(max = 50)
    @Column(name = "cargo", nullable = false, length = 50)
    private String cargo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_contratacion", columnDefinition = "DATE DEFAULT (CURRENT_DATE)")
    private LocalDate fechaContratacion;

    @Column(name = "salario", precision = 10, scale = 2)
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    @NotNull
    private EstadoEmpleado estado;
}
