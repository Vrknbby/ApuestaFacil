package com.idat.edu.pe.EvaluacionFinal.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "promocion")
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String tipoPromocion;

    @Column
    private Double montoPromocion;

    @Column(nullable = false)
    private String nombrePromocion;

    @Column(unique = true)
    private String codigoPromocion;

    @Column
    private String estadoPromocion;

    public Promocion() {
    }

    public Promocion(String tipoPromocion, Double montoPromocion, String nombrePromocion, String codigoPromocion, String estadoPromocion) {
        this.tipoPromocion = tipoPromocion;
        this.montoPromocion = montoPromocion;
        this.nombrePromocion = nombrePromocion;
        this.codigoPromocion = codigoPromocion;
        this.estadoPromocion = estadoPromocion;
    }

    public Promocion(Long id, String tipoPromocion, Double montoPromocion, String nombrePromocion, String codigoPromocion, String estadoPromocion) {
        this.id = id;
        this.tipoPromocion = tipoPromocion;
        this.montoPromocion = montoPromocion;
        this.nombrePromocion = nombrePromocion;
        this.codigoPromocion = codigoPromocion;
        this.estadoPromocion = estadoPromocion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoPromocion() {
        return tipoPromocion;
    }

    public void setTipoPromocion(String tipoPromocion) {
        this.tipoPromocion = tipoPromocion;
    }

    public Double getMontoPromocion() {
        return montoPromocion;
    }

    public void setMontoPromocion(Double montoPromocion) {
        this.montoPromocion = montoPromocion;
    }

    public String getCodigoPromocion() {
        return codigoPromocion;
    }

    public void setCodigoPromocion(String codigoPromocion) {
        this.codigoPromocion = codigoPromocion;
    }

    public String getNombrePromocion() {
        return nombrePromocion;
    }

    public void setNombrePromocion(String nombrePromocion) {
        this.nombrePromocion = nombrePromocion;
    }

    public String getEstadoPromocion() {
        return estadoPromocion;
    }

    public void setEstadoPromocion(String estadoPromocion) {
        this.estadoPromocion = estadoPromocion;
    }
}
