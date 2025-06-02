package com.isai.demosistemacitasillaya.app.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id // esta anotacion define la llave primaria de una clase
    @GeneratedValue(strategy = GenerationType.IDENTITY) // esta anotacion define como se va a generar esa llave
    private Integer idCliente;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(nullable = false, length = 20)
    private String apellido;

    @Column(nullable = false, length = 40)
    private String email;

    @Column(nullable = false, length = 9)
    private String telefono;

    @Column(name = "fecha_creacion", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaCreacion;

    @OneToOne(fetch = FetchType.LAZY) // es indica que un usuario esta relacionado con un cliente
    @JoinColumn(name = "id_usuario", unique = true, nullable = false)
    private Usuario usuario;

    public Integer getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }


}
