package com.idat.edu.pe.EvaluacionFinal.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "apuestas")
public class Apuesta {

    public static final Long EMPATE_ID= -1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_partido", referencedColumnName = "id")
    private Partido idPartido;

    @ManyToOne
    @JoinColumn(name = "id_usuario",referencedColumnName = "id")
    private Usuario idUsuario;

    @Column(name="monto_apuesta")
    private BigDecimal montoApuesta;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name="resultado")
    private Boolean resultado;

    @Column(name="id_equipo_apuesta")
    private Long idEquipoApuesta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Partido getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Partido idPartido) {
        this.idPartido = idPartido;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
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

    public Apuesta(Long id, Partido idPartido, Usuario idUsuario, BigDecimal montoApuesta, LocalDate fecha, Boolean resultado, Long idEquipoApuesta) {
        this.id = id;
        this.idPartido = idPartido;
        this.idUsuario = idUsuario;
        this.montoApuesta = montoApuesta;
        this.fecha = fecha;
        this.resultado = resultado;
        this.idEquipoApuesta = idEquipoApuesta;
    }

    public Apuesta(Partido idPartido, Usuario idUsuario, BigDecimal montoApuesta, LocalDate fecha, Boolean resultado, Long idEquipoApuesta) {
        this.idPartido = idPartido;
        this.idUsuario = idUsuario;
        this.montoApuesta = montoApuesta;
        this.fecha = fecha;
        this.resultado = resultado;
        this.idEquipoApuesta = idEquipoApuesta;
    }

    public Apuesta() {
    }
}
