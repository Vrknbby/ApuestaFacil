package com.idat.edu.pe.EvaluacionFinal.service;

import com.idat.edu.pe.EvaluacionFinal.DTO.ApuestaDTO;
import com.idat.edu.pe.EvaluacionFinal.Excepciones.ApuestaExcepcion;
import com.idat.edu.pe.EvaluacionFinal.model.Apuesta;
import com.idat.edu.pe.EvaluacionFinal.model.Partido;
import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.repository.ApuestaRepository;
import com.idat.edu.pe.EvaluacionFinal.repository.UsuarioRepository;
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
    UsuarioRepository usuarioRepository;

    @Autowired
    PartidoServicio partidoServicio;

    @Autowired
    AutenticacionServicio autenticacionServicio;

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

    @Transactional
    public boolean eliminarApuesta(Long idApuesta, Long idusuario){
        try{
            Apuesta apuesta = new Apuesta();
            Usuario usuario = new Usuario();
            Partido partido = new Partido();

            Optional<Apuesta> apuestaOptional = apuestaRepository.findById(idApuesta);
            Optional<Usuario> usuarioOptional = usuarioServicio.obtenerPorId(idusuario);

            if (apuestaOptional.isPresent()) { apuesta = apuestaOptional.get(); }
            if (usuarioOptional.isPresent()) { usuario = usuarioOptional.get(); }

            Optional<Partido> partidoOptional = partidoServicio.obtenerPartidoPorId(apuesta.getIdPartido().getId());
            if (partidoOptional.isPresent()) { partido = partidoOptional.get(); }


            if (partido.getEstado().equals("En vivo")) {
                BigDecimal montoApuesta = apuesta.getMontoApuesta();
                BigDecimal montoDevolucion = montoApuesta.multiply(BigDecimal.valueOf(0.5));
                BigDecimal newMontoUsuario = usuario.getFondos().add(montoDevolucion);
                usuario.setFondos(newMontoUsuario);

                boolean authentic = autenticacionServicio.authenticateDNI(usuario.getDni());
                if (apuesta.getIdUsuario().getId().equals(usuario.getId()) && authentic) {
                    usuarioServicio.actualizarUsuario(usuario.getId(), usuario);
                    apuestaRepository.deleteById(idApuesta);
                    return true;
                }

            } else {
                BigDecimal montoApuesta = apuesta.getMontoApuesta();
                BigDecimal newMontoUsuario = usuario.getFondos().add(montoApuesta);
                usuario.setFondos(newMontoUsuario);

                boolean authentic = autenticacionServicio.authenticateDNI(usuario.getDni());
                if (apuesta.getIdUsuario().getId().equals(usuario.getId()) && authentic) {
                    usuarioServicio.actualizarUsuario(usuario.getId(), usuario);
                    apuestaRepository.deleteById(idApuesta);
                    return true;
                }
            }
            return false;

        } catch (Exception err) {
            return false;
        }
    }

    public ArrayList<Apuesta> obtenerApuestaPorPartido(Partido id){
        return (ArrayList<Apuesta>) apuestaRepository.findByIdPartido(id);
    }

    public ArrayList<Apuesta> obtenerApuestaPorUsuario(Usuario id){
        return (ArrayList<Apuesta>) apuestaRepository.findByIdUsuario(id);
    }

    @Transactional
    public Apuesta actualizarApuesta(Long idApuesta, ApuestaDTO apuestaDTO) {
        Optional<Apuesta> apuestaOptional = apuestaRepository.findById(idApuesta);
        if (!apuestaOptional.isPresent()) {
            throw new ApuestaExcepcion("La apuesta no existe.");
        }

        Apuesta apuestaExistente = apuestaOptional.get();
        BigDecimal montoApuestaOriginal = apuestaExistente.getMontoApuesta();

        Optional<Partido> partidoOptional = partidoServicio.obtenerPartidoPorId(apuestaDTO.getIdPartido());
        if (!partidoOptional.isPresent()) {
            throw new ApuestaExcepcion("El partido especificado no existe.");
        }

        Optional<Usuario> usuarioOptional = usuarioServicio.obtenerPorId(apuestaDTO.getIdUsuario());
        if (!usuarioOptional.isPresent()) {
            throw new ApuestaExcepcion("El usuario especificado no existe.");
        }

        Partido partido = partidoOptional.get();
        Usuario usuario = usuarioOptional.get();

        if (apuestaDTO.getIdEquipoApuesta() != Partido.EMPATE_ID &&
                apuestaDTO.getIdEquipoApuesta() != partido.getIdEquipoLocal().getId() &&
                apuestaDTO.getIdEquipoApuesta() != partido.getIdEquipoVisitante().getId()) {
            throw new ApuestaExcepcion("El equipo apostado no corresponde a ninguno de los equipos participantes en este partido.");
        }

        BigDecimal montoApostado = apuestaDTO.getMontoApuesta();
        BigDecimal fondosUsuario = usuario.getFondos();
        BigDecimal apuestaRegresada = montoApuestaOriginal.add(fondosUsuario);
        BigDecimal nuevoFondos = apuestaRegresada.subtract(montoApostado);
        if (nuevoFondos.compareTo(BigDecimal.ZERO) < 0) {
            throw new ApuestaExcepcion("No tiene fondos suficientes para actualizar la apuesta.");
        }

        // Actualizamos los datos de la apuesta existente
        apuestaExistente.setIdPartido(partido);
        apuestaExistente.setIdUsuario(usuario);
        apuestaExistente.setMontoApuesta(apuestaDTO.getMontoApuesta());
        apuestaExistente.setFecha(apuestaDTO.getFecha());
        apuestaExistente.setResultado(apuestaDTO.getResultado());
        apuestaExistente.setIdEquipoApuesta(apuestaDTO.getIdEquipoApuesta());

        // Ajustamos los fondos del usuario
        BigDecimal diferenciaFondos = montoApostado.subtract(montoApuestaOriginal);
        usuario.setFondos(usuario.getFondos().subtract(diferenciaFondos));
        usuarioServicio.actualizarUsuario(usuario.getId(), usuario);

        // Guardamos los cambios en la base de datos
        return apuestaRepository.save(apuestaExistente);
    }


}
