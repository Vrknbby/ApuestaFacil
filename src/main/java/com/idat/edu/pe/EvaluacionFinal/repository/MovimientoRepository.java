package com.idat.edu.pe.EvaluacionFinal.repository;

import com.idat.edu.pe.EvaluacionFinal.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
}
