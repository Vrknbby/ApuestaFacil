package com.idat.edu.pe.EvaluacionFinal.service;

import com.idat.edu.pe.EvaluacionFinal.model.Promocion;
import com.idat.edu.pe.EvaluacionFinal.repository.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PromocionServicio {
    @Autowired
    PromocionRepository promocionRepository;

    public List<Promocion> obtenerPromociones(){
        return promocionRepository.findAll();
    }

    public Promocion guardarPromocion(Promocion promocion){
        return  promocionRepository.save(promocion);
    }

    public Optional<Promocion> buscarPromocionPorId(Long id){
        return  promocionRepository.findById(id);
    }

    public Promocion actualizarPromocion(Promocion promocion, Long id){
        Promocion promocionActual = promocionRepository.findById(id).orElse(null);
        if (promocionActual != null){
            promocionActual.setTipoPromocion(promocion.getTipoPromocion());
            promocionActual.setNombrePromocion(promocion.getNombrePromocion());
            promocionActual.setMontoPromocion(promocion.getMontoPromocion());
            promocionActual.setCodigoPromocion(promocion.getCodigoPromocion());
            promocionActual.setEstadoPromocion(promocion.getEstadoPromocion());

            return promocionRepository.save(promocionActual);
        }
        return null;
    }

    public void actualizarEstado(Long id){
        Promocion promocionActual = promocionRepository.findById(id).orElse(null);
        if (promocionActual != null){
            if (promocionActual.getEstadoPromocion().equals("Activo")){
                promocionActual.setEstadoPromocion("Inactivo");
            }
            else {
                promocionActual.setEstadoPromocion("Activo");
            }
            promocionRepository.save(promocionActual);
        }
    }


    public Promocion buscarPorCodigo(String codigo){
        return promocionRepository.findByCodigoPromocion(codigo);
    }

}
