package com.idat.edu.pe.EvaluacionFinal.repository;

import com.idat.edu.pe.EvaluacionFinal.model.Apuesta;
import com.idat.edu.pe.EvaluacionFinal.model.Partido;
import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface ApuestaRepository extends JpaRepository<Apuesta, Long> {
    public abstract ArrayList<Apuesta> findByIdPartido(Partido id);

    public abstract ArrayList<Apuesta> findByIdUsuario(Usuario id);
}
