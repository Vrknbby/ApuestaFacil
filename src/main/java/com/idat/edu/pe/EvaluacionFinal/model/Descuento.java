package com.idat.edu.pe.EvaluacionFinal.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "descuentos")
public class Descuento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    @Column(name = "codigoDescuento")
    private String codigoDescuento;
    @Column(name = "recarga")
    private BigDecimal recarga;
    @Column(name = "cantUsos")
    private int cantUsos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoDescuento() {
        return codigoDescuento;
    }

    public void setCodigoDescuento(String codigoDescuento) {
        this.codigoDescuento = codigoDescuento;
    }

    public BigDecimal getRecarga() {
        return recarga;
    }

    public void setRecarga(BigDecimal recarga) {
        this.recarga = recarga;
    }

    public int getCantUsos() {
        return cantUsos;
    }

    public void setCantUsos(int cantUsos) {
        this.cantUsos = cantUsos;
    }

    public Descuento(Long id, String codigoDescuento, BigDecimal recarga, int cantUsos) {
        this.id = id;
        this.codigoDescuento = codigoDescuento;
        this.recarga = recarga;
        this.cantUsos = cantUsos;
    }

    public Descuento(String codigoDescuento, BigDecimal recarga, int cantUsos) {
        this.codigoDescuento = codigoDescuento;
        this.recarga = recarga;
        this.cantUsos = cantUsos;
    }

    public Descuento(String codigoDescuento) {
        this.codigoDescuento = codigoDescuento;
    }

    public Descuento() {
    }

}
