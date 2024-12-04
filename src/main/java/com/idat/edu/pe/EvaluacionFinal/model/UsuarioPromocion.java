package com.idat.edu.pe.EvaluacionFinal.model;


import jakarta.persistence.*;

@Entity
@Table(name = "usuarioPromocion")

public class UsuarioPromocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuarioId", nullable = false) // Mapea a la columna "usuario_id"
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "promocionId", nullable = false) // Mapea a la columna "promocion_id"
    private Promocion promocion;

    public UsuarioPromocion() {
    }

    public UsuarioPromocion(Usuario usuario, Promocion promocion) {
        this.usuario = usuario;
        this.promocion = promocion;
    }

    public UsuarioPromocion(Long id, Usuario usuario, Promocion promocion) {
        this.id = id;
        this.usuario = usuario;
        this.promocion = promocion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }
}
