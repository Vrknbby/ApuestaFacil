package com.idat.edu.pe.EvaluacionFinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "partidos")
public class Partido {
    public static final Long EMPATE_ID= -1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_equipo_local", referencedColumnName = "id")
    private Equipo idEquipoLocal;

    @ManyToOne
    @JoinColumn(name = "id_equipo_visitante", referencedColumnName = "id")
    private Equipo idEquipoVisitante;

    @OneToMany(mappedBy = "idPartido")
    @JsonIgnore
    private List<Apuesta> idApuestas;

    @Column(name = "goles_equipo_local")
    private int golesLocal;

    @Column(name = "goles_equipo_visitante")
    private int golesVisitante;

    @Column(name = "fecha_hora")
    private Date fechahora;

    @Column(name = "estado")
    private String estado;

    @Column(name = "id_equipo_ganador")
    private Long idEquipoGanador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Equipo getIdEquipoLocal() {
        return idEquipoLocal;
    }

    public void setIdEquipoLocal(Equipo idEquipoLocal) {
        this.idEquipoLocal = idEquipoLocal;
    }

    public Equipo getIdEquipoVisitante() {
        return idEquipoVisitante;
    }

    public void setIdEquipoVisitante(Equipo idEquipoVisitante) {
        this.idEquipoVisitante = idEquipoVisitante;
    }

    public List<Apuesta> getIdApuestas() {
        return idApuestas;
    }

    public void setIdApuestas(List<Apuesta> idApuestas) {
        this.idApuestas = idApuestas;
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
        if (idEquipoGanador != null & idEquipoGanador.equals(EMPATE_ID)){
            this.idEquipoGanador = idEquipoGanador;
        }else{
            this.idEquipoGanador = idEquipoGanador;
        }

    }

    public Partido(Long id, Equipo idEquipoLocal, Equipo idEquipoVisitante, List<Apuesta> idApuestas, int golesLocal, int golesVisitante, Date fechahora, String estado, Long idEquipoGanador) {
        this.id = id;
        this.idEquipoLocal = idEquipoLocal;
        this.idEquipoVisitante = idEquipoVisitante;
        this.idApuestas = idApuestas;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.fechahora = fechahora;
        this.estado = estado;
        this.idEquipoGanador = idEquipoGanador;
    }

    public Partido(Equipo idEquipoLocal, Equipo idEquipoVisitante, List<Apuesta> idApuestas, int golesLocal, int golesVisitante, Date fechahora, String estado, Long idEquipoGanador) {
        this.idEquipoLocal = idEquipoLocal;
        this.idEquipoVisitante = idEquipoVisitante;
        this.idApuestas = idApuestas;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.fechahora = fechahora;
        this.estado = estado;
        this.idEquipoGanador = idEquipoGanador;
    }

    public Partido(Equipo idEquipoLocal, Equipo idEquipoVisitante, int golesLocal, int golesVisitante, Date fechahora, String estado, Long idEquipoGanador) {
        this.idEquipoLocal = idEquipoLocal;
        this.idEquipoVisitante = idEquipoVisitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.fechahora = fechahora;
        this.estado = estado;
        this.idEquipoGanador = idEquipoGanador;
    }

    public Partido() {
    }
}
