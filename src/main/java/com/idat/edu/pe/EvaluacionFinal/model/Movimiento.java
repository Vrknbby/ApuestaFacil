package com.idat.edu.pe.EvaluacionFinal.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "movimientos")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "tipo")
    public String tipo;

    @Column(name = "fecha")
    public String fecha;

    @Column(name = "monto")
    public BigDecimal monto;

    @ManyToOne
    @JoinColumn(name = "id_usuario" , referencedColumnName = "id")
    public Usuario idUsuario;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Movimiento(Long id, String tipo, String fecha, BigDecimal monto, Usuario idUsuario) {
        this.id = id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.monto = monto;
        this.idUsuario = idUsuario;
    }

    public Movimiento(String tipo, String fecha, BigDecimal monto, Usuario idUsuario) {
        this.tipo = tipo;
        this.fecha = fecha;
        this.monto = monto;
        this.idUsuario = idUsuario;
    }

    public Movimiento() {}
}
