package com.idat.edu.pe.EvaluacionFinal.DTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ApuestaDTO {
    public static final Long EMPATE_ID= -1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("idPartido")
    private Long idPartido;

    @JsonProperty("idUsuario")
    private Long idUsuario;

    @JsonProperty("montoApuesta")
    private BigDecimal montoApuesta;

    @JsonProperty("fecha")
    private LocalDate fecha;

    @JsonProperty("resultado")
    private Boolean resultado;

    @JsonProperty("idEquipoApuesta")
    private Long idEquipoApuesta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Long idPartido) {
        this.idPartido = idPartido;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public BigDecimal getMontoApuesta() {
        return montoApuesta;
    }

    public void setMontoApuesta(BigDecimal montoApuesta) {
        this.montoApuesta = montoApuesta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Boolean getResultado() {
        return resultado;
    }

    public void setResultado(Boolean resultado) {
        this.resultado = resultado;
    }

    public Long getIdEquipoApuesta() {
        return idEquipoApuesta;
    }

    public void setIdEquipoApuesta(Long idEquipoApuesta) {
        if(idEquipoApuesta != null && idEquipoApuesta.equals(EMPATE_ID)){
            this.idEquipoApuesta = EMPATE_ID;
        }else {
            this.idEquipoApuesta= idEquipoApuesta;
        }
    }

    public ApuestaDTO(Long id, Long idPartido, Long idUsuario, BigDecimal montoApuesta, LocalDate fecha, Boolean resultado, Long idEquipoApuesta) {
        this.id = id;
        this.idPartido = idPartido;
        this.idUsuario = idUsuario;
        this.montoApuesta = montoApuesta;
        this.fecha = fecha;
        this.resultado = resultado;
        this.idEquipoApuesta = idEquipoApuesta;
    }

    public ApuestaDTO(Long idPartido, Long idUsuario, BigDecimal montoApuesta, LocalDate fecha, Boolean resultado, Long idEquipoApuesta) {
        this.idPartido = idPartido;
        this.idUsuario = idUsuario;
        this.montoApuesta = montoApuesta;
        this.fecha = fecha;
        this.resultado = resultado;
        this.idEquipoApuesta = idEquipoApuesta;
    }

    public ApuestaDTO() {
    }
}
