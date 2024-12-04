package com.idat.edu.pe.EvaluacionFinal.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.idat.edu.pe.EvaluacionFinal.model.UsuarioPromocion;

public class UsuarioPromocionDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("usuario_id")
    private Long usuarioId;

    @JsonProperty("promocion_id")
    private Long promocionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPromocionId() {
        return promocionId;
    }

    public void setPromocionId(Long promocionId) {
        this.promocionId = promocionId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public UsuarioPromocionDTO() {
    }

    public UsuarioPromocionDTO(Long usuarioId, Long promocionId) {
        this.usuarioId = usuarioId;
        this.promocionId = promocionId;
    }

    public UsuarioPromocionDTO(Long id, Long usuarioId, Long promocionId) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.promocionId = promocionId;
    }
}
