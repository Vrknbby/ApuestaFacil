package com.idat.edu.pe.EvaluacionFinal.repository;

import com.idat.edu.pe.EvaluacionFinal.model.Descuento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DescuentoRepository extends JpaRepository<Descuento, Long> {
    Optional<Descuento> findByCodigoDescuento(String codigoDescuento);
}
