package com.idat.edu.pe.EvaluacionFinal.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.idat.edu.pe.EvaluacionFinal.model.Usuario;


import java.math.BigDecimal;

public class MovimientoDTO {

   @JsonProperty("id")
    public Long id;

    @JsonProperty("tipo")
    public String tipo;

    @JsonProperty("fecha")
    public String fecha;

    @JsonProperty("monto")
    public BigDecimal monto;

    @JsonProperty("idUsuario")
    public Long idUsuario;

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

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public MovimientoDTO(Long id, String tipo, String fecha, BigDecimal monto, Long idUsuario) {
        this.id = id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.monto = monto;
        this.idUsuario = idUsuario;
    }

    public MovimientoDTO(String tipo, String fecha, BigDecimal monto, Long idUsuario) {
        this.tipo = tipo;
        this.fecha = fecha;
        this.monto = monto;
        this.idUsuario = idUsuario;
    }

    public MovimientoDTO() {}
}
