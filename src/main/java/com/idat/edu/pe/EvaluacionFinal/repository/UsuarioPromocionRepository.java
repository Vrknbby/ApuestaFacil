package com.idat.edu.pe.EvaluacionFinal.repository;

import com.idat.edu.pe.EvaluacionFinal.model.UsuarioPromocion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioPromocionRepository extends JpaRepository<UsuarioPromocion, Long> {

    Optional<UsuarioPromocion> findByUsuarioIdAndPromocionId(Long usuarioId, Long promocionId);

    Optional<UsuarioPromocion> findByPromocionId(Long promocionId);

}
