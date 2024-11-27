package com.idat.edu.pe.EvaluacionFinal.repository;


import com.idat.edu.pe.EvaluacionFinal.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
}
