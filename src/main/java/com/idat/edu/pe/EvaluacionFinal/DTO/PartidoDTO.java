package com.idat.edu.pe.EvaluacionFinal.DTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class PartidoDTO {
    public static final Long EMPATE_ID= -1L;

    @JsonProperty("id")
    private Long id;
    @JsonProperty("idEquipoLocal")
    private Long idEquipoLocal;
    @JsonProperty("idEquipoVisitante")
    private Long idEquipoVisitante;
    @JsonProperty("golesLocal")
    private int golesLocal;
    @JsonProperty("golesVisitante")
    private int golesVisitante;
    @JsonProperty("fechahora")
    private Date fechahora;
    @JsonProperty("estado")
    private String estado;
    @JsonProperty("idEquipoGanador")
    private Long idEquipoGanador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEquipoLocal() {
        return idEquipoLocal;
    }

    public void setIdEquipoLocal(Long idEquipoLocal) {
        this.idEquipoLocal = idEquipoLocal;
    }

    public Long getIdEquipoVisitante() {
        return idEquipoVisitante;
    }

    public void setIdEquipoVisitante(Long idEquipoVisitante) {
        this.idEquipoVisitante = idEquipoVisitante;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdEquipoGanador() {
        return idEquipoGanador;
    }

    public void setIdEquipoGanador(Long idEquipoGanador) {
        if (idEquipoGanador == null) {
            this.idEquipoGanador = null;
        }
        else if (idEquipoGanador != null & idEquipoGanador.equals(EMPATE_ID)){
            this.idEquipoGanador = idEquipoGanador;
        }
        else{
            this.idEquipoGanador = idEquipoGanador;
        }
    }

    public PartidoDTO(Long id, Long idEquipoLocal, Long idEquipoVisitante, int golesLocal, int golesVisitante, Date fechahora, String estado, Long idEquipoGanador) {
        this.id = id;
        this.idEquipoLocal = idEquipoLocal;
        this.idEquipoVisitante = idEquipoVisitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.fechahora = fechahora;
        this.estado = estado;
        this.idEquipoGanador = idEquipoGanador;
    }

    public PartidoDTO(Long idEquipoLocal, Long idEquipoVisitante, int golesLocal, int golesVisitante, Date fechahora, String estado, Long idEquipoGanador) {
        this.idEquipoLocal = idEquipoLocal;
        this.idEquipoVisitante = idEquipoVisitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.fechahora = fechahora;
        this.estado = estado;
        this.idEquipoGanador = idEquipoGanador;
    }

    public PartidoDTO() {
    }
}
