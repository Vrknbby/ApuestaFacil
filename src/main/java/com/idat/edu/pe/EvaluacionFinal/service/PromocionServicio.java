package com.idat.edu.pe.EvaluacionFinal.service;

import com.idat.edu.pe.EvaluacionFinal.model.Promocion;
import com.idat.edu.pe.EvaluacionFinal.model.UsuarioPromocion;
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

    @Autowired
    UsuarioPromocionServicio usuarioPromocionServicio;

    public boolean eliminarPromocion(Long id){
        Promocion promocion = promocionRepository.findById(id).orElse(null);
        Optional<UsuarioPromocion> usuarioPromocionOptional = usuarioPromocionServicio.obtenerPorPromocionId(id);
        UsuarioPromocion usuarioPromocion = new UsuarioPromocion();
        if (promocion != null){
            if (usuarioPromocionOptional.isPresent() ){
                usuarioPromocion = usuarioPromocionOptional.get();
                System.out.println(usuarioPromocion.getId());
                usuarioPromocionServicio.eliminarPorPromocionId(usuarioPromocion.getId());
            }
            promocionRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public Promocion actualizarPromocion(Promocion promocionActualizada, Long id){
        Promocion promocion = promocionRepository.findById(id).orElse(null);
        if(promocion != null){
            if(promocion.getCodigoPromocion()!=null){
                promocion.setCodigoPromocion(promocionActualizada.getCodigoPromocion());
            }
            if(promocion.getMontoPromocion()!=null){
                promocion.setMontoPromocion(promocionActualizada.getMontoPromocion());
            }
            if(promocion.getTipoPromocion()!=null){
                promocion.setTipoPromocion(promocionActualizada.getTipoPromocion());
            }
            if(promocion.getNombrePromocion()!=null){
                promocion.setNombrePromocion(promocionActualizada.getNombrePromocion());
            }
            if(promocion.getEstadoPromocion()!=null){
                promocion.setEstadoPromocion(promocionActualizada.getEstadoPromocion());
            }

            return promocionRepository.save(promocion);
        }else{
            return null;
        }
    }


}
