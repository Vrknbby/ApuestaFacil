package com.idat.edu.pe.EvaluacionFinal.service;

import com.idat.edu.pe.EvaluacionFinal.DTO.ApuestaDTO;
import com.idat.edu.pe.EvaluacionFinal.Excepciones.ApuestaExcepcion;
import com.idat.edu.pe.EvaluacionFinal.model.Apuesta;
import com.idat.edu.pe.EvaluacionFinal.model.Partido;
import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.repository.ApuestaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ApuestaServicio {

    @Autowired
    ApuestaRepository apuestaRepository;

    @Autowired
    PartidoServicio partidoServicio;

    @Autowired
    UsuarioServicio usuarioServicio;

    public ArrayList<Apuesta> obtenerApuesta(){ return (ArrayList<Apuesta>) apuestaRepository.findAll();}

    @Transactional
    public Apuesta guardarApuesta(ApuestaDTO apuestaDTO){
        Partido partido = new Partido();
        Usuario usuario = new Usuario();

        Optional<Partido> partidoOptional = partidoServicio.obtenerPartidoPorId(apuestaDTO.getIdPartido());
        Optional<Usuario> usuarioOptional = usuarioServicio.obtenerPorId(apuestaDTO.getIdUsuario());

        if(partidoOptional.isPresent() & usuarioOptional.isPresent()){
            partido = partidoOptional.get();
            usuario = usuarioOptional.get();
        }
        if (apuestaDTO.getIdEquipoApuesta() != Partido.EMPATE_ID &&
                apuestaDTO.getIdEquipoApuesta() != partido.getIdEquipoLocal().getId() &&
                apuestaDTO.getIdEquipoApuesta() != partido.getIdEquipoVisitante().getId()){
            throw new ApuestaExcepcion("El equipo apostado no corresponde a ninguno de los equipos participantes" +
                    " en este partido.");
        }
        BigDecimal montoApostado = apuestaDTO.getMontoApuesta();
        BigDecimal fondosUsuario = usuario.getFondos();
        if (montoApostado.compareTo(fondosUsuario) < 0){
            usuario.setFondos(fondosUsuario.subtract(montoApostado));
            this.usuarioServicio.actualizarUsuario(usuario.getId(), usuario);
            Apuesta apuesta = new Apuesta(
                    partido,
                    usuario,
                    apuestaDTO.getMontoApuesta(),
                    apuestaDTO.getFecha(),
                    apuestaDTO.getResultado(),
                    apuestaDTO.getIdEquipoApuesta()
            );
            return apuestaRepository.save(apuesta);
        }else{
            throw new ApuestaExcepcion("No tiene fondos suficientes para realizar la apuesta.");
        }
    }

    public Optional<Apuesta> obtenerApuestaPorId(Long id){
        return apuestaRepository.findById(id);
    }

    public boolean eliminarApuesta(Long id){
        try{
            apuestaRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false;
        }
    }

    public ArrayList<Apuesta> obtenerApuestaPorPartido(Partido id){
        return (ArrayList<Apuesta>) apuestaRepository.findByIdPartido(id);
    }

    public ArrayList<Apuesta> obtenerApuestaPorUsuario(Usuario id){
        return (ArrayList<Apuesta>) apuestaRepository.findByIdUsuario(id);
    }
}
