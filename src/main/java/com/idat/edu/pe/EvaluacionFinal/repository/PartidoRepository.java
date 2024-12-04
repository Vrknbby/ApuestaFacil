package com.idat.edu.pe.EvaluacionFinal.repository;

import com.idat.edu.pe.EvaluacionFinal.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {
    //public abstract ArrayList<Partido> findByFechahora(Date fecha_hora);

    public abstract ArrayList<Partido> findByEstado(String estado);
}
