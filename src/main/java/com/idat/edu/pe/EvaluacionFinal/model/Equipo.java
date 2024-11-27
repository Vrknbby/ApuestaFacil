package com.idat.edu.pe.EvaluacionFinal.model;

import jakarta.persistence.*;

@Entity
@Table (name="equipos")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "pais")
    private String pais;

    @Column(name = "fecha_fundacion")
    private String fechaFundacion;

    @Column(name = "presidente")
    private String presidente;

    //@Column(name = "logo")
    //private String logo;

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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(String fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }

    public String getPresidente() {
        return presidente;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }

    public Equipo(Long id, String nombre, String pais, String fechaFundacion, String presidente) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.fechaFundacion = fechaFundacion;
        this.presidente = presidente;
    }

    public Equipo(String nombre, String pais, String fechaFundacion, String presidente) {
        this.nombre = nombre;
        this.pais = pais;
        this.fechaFundacion = fechaFundacion;
        this.presidente = presidente;
    }

    public Equipo() {
    }
}
