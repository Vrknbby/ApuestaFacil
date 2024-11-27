package com.idat.edu.pe.EvaluacionFinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "fondos")
    private BigDecimal fondos;

    @Column(nullable = false, unique = true) // En la base de datos, debe ser único
    @Size(min = 8, max = 9, message = "El DNI debe tener entre 8 y 9 caracteres.")
    private String dni;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id")
    )
    private Collection<Rol> roles;

    @OneToMany(mappedBy = "idUsuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Apuesta> idApuestas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public BigDecimal getFondos() {
        return fondos;
    }

    public void setFondos(BigDecimal fondos) {
        this.fondos = fondos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Collection<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Rol> roles) {
        this.roles = roles;
    }

    public List<Apuesta> getIdApuestas() {
        return idApuestas;
    }

    public void setIdApuestas(List<Apuesta> idApuestas) {
        this.idApuestas = idApuestas;
    }


    public Usuario(Long id, String nombre, String email, String password, LocalDate fechaNacimiento, BigDecimal fondos, String dni, Collection<Rol> roles, List<Apuesta> idApuestas) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.fondos = fondos;
        this.dni = dni;
        this.roles = roles;
        this.idApuestas = idApuestas;
    }

    public Usuario(String nombre, String email, String password, LocalDate fechaNacimiento, BigDecimal fondos, String dni, Collection<Rol> roles, List<Apuesta> idApuestas) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.fondos = fondos;
        this.dni = dni;
        this.roles = roles;
        this.idApuestas = idApuestas;
    }

    public Usuario(String nombre, String email, String password, LocalDate fechaNacimiento, BigDecimal fondos, String dni) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.fondos = fondos;
        this.dni = dni;
    }

    public Usuario() {
    }
}
