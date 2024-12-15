package com.idat.edu.pe.EvaluacionFinal.service;

import com.idat.edu.pe.EvaluacionFinal.DTO.UsuarioPromocionDTO;
import com.idat.edu.pe.EvaluacionFinal.model.Equipo;
import com.idat.edu.pe.EvaluacionFinal.model.Promocion;
import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.model.UsuarioPromocion;
import com.idat.edu.pe.EvaluacionFinal.repository.PromocionRepository;
import com.idat.edu.pe.EvaluacionFinal.repository.UsuarioPromocionRepository;
import com.idat.edu.pe.EvaluacionFinal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioPromocionServicio {
    @Autowired
    UsuarioPromocionRepository usuarioPromocionRepository;

    @Autowired
    UsuarioServicio usuarioServicio;

    @Autowired
    PromocionServicio promocionServicio;

    public UsuarioPromocion guardarUsuarioPromocion(UsuarioPromocionDTO usuarioPromocionDTO){
        if(obtenerPorUsuarioYPromocion(usuarioPromocionDTO) != null){
            Usuario usuario = new Usuario();
            Promocion promocion = new Promocion();
            Optional<Usuario> usuarioOptional = usuarioServicio.obtenerPorId(usuarioPromocionDTO.getUsuarioId());
            Optional<Promocion> promocionOptional = promocionServicio.buscarPromocionPorId(usuarioPromocionDTO.getPromocionId());

            if(usuarioOptional.isPresent() & promocionOptional.isPresent()){
                usuario = usuarioOptional.get();
                promocion= promocionOptional.get();
            }
            UsuarioPromocion usuarioPromocionAux = new UsuarioPromocion(
                    usuario,
                    promocion
            );
            return usuarioPromocionRepository.save(usuarioPromocionAux);
        }
        return null;
    }

    public Optional<UsuarioPromocion> obtenerPorUsuarioYPromocion(UsuarioPromocionDTO usuarioPromocionDTO) {
        return usuarioPromocionRepository.findByUsuarioIdAndPromocionId(usuarioPromocionDTO.getUsuarioId(), usuarioPromocionDTO.getPromocionId());
    }

    public void eliminarPorPromocionId(Long id){
        usuarioPromocionRepository.deleteById(id);
    }
    public Optional<UsuarioPromocion> obtenerPorPromocionId(Long id) {
        return usuarioPromocionRepository.findByPromocionId(id);
    }


}
