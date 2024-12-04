package com.idat.edu.pe.EvaluacionFinal.service;

import com.idat.edu.pe.EvaluacionFinal.DTO.PartidoDTO;
import com.idat.edu.pe.EvaluacionFinal.Excepciones.ApuestaExcepcion;
import com.idat.edu.pe.EvaluacionFinal.model.Apuesta;
import com.idat.edu.pe.EvaluacionFinal.model.Equipo;
import com.idat.edu.pe.EvaluacionFinal.model.Partido;
import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.repository.PartidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class PartidoServicio {

    public static final Long EMPATE_ID = -1L;

    @Autowired
    PartidoRepository partidoRepository;

    @Autowired
    UsuarioServicio usuarioServicio;

    @Autowired
    EquipoServicio equipoServicio;

    @Autowired
    ApuestaServicio apuestaServicio;
    public ArrayList<Partido> obtenerPartidos(){ return (ArrayList<Partido>) partidoRepository.findAll();}

    public Partido guardarPartido(PartidoDTO partidoDTO){
        Equipo Local = new Equipo();
        Equipo Visitante = new Equipo();
        Optional<Equipo> localOptional = equipoServicio.obtenerEquipoPorId(partidoDTO.getIdEquipoLocal());
        Optional<Equipo> visitanteOptional = equipoServicio.obtenerEquipoPorId(partidoDTO.getIdEquipoVisitante());

        if(localOptional.isPresent() & visitanteOptional.isPresent()){
            Local= localOptional.get();
            Visitante= visitanteOptional.get();
        }

        Partido partidoAux = new Partido(
                Local,
                Visitante,
                partidoDTO.getGolesLocal(),
                partidoDTO.getGolesVisitante(),
                partidoDTO.getFechahora(),
                partidoDTO.getEstado(),
                partidoDTO.getIdEquipoGanador()
        );
        return partidoRepository.save(partidoAux);
    }

    public Optional<Partido> obtenerPartidoPorId(Long id){
        return partidoRepository.findById(id);
    }

    public boolean eliminarPartido(Long id){
        try{
            partidoRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false;
        }
    }

    public ArrayList<Partido> obtenerPartidosPorEstado(String estado){
        return (ArrayList<Partido>) partidoRepository.findByEstado(estado);
    }

    public Partido actualizarPartido (Long id, PartidoDTO partidoDTO){
        System.out.println("ID "+ id);
        Equipo Local = new Equipo();
        Equipo Visitante = new Equipo();


        if(partidoDTO.getIdEquipoLocal() !=null ){
            Optional<Equipo> localOptional = equipoServicio.obtenerEquipoPorId(partidoDTO.getIdEquipoLocal());
            if(localOptional.isPresent()){
                Local= localOptional.get();
            }
        }

        if( partidoDTO.getIdEquipoVisitante() !=null){
            Optional<Equipo> visitanteOptional = equipoServicio.obtenerEquipoPorId(partidoDTO.getIdEquipoVisitante());
            if(visitanteOptional.isPresent()){
                Visitante= visitanteOptional.get();
            }
        }
        Optional<Partido> partidoAntiguo = partidoRepository.findById(id);
        if (partidoAntiguo.isPresent()){
            Partido partidoActualizado = partidoAntiguo.get();

            if(partidoDTO.getIdEquipoVisitante() != null){
                partidoActualizado.setIdEquipoVisitante(Visitante);
            }
            if(partidoDTO.getIdEquipoLocal() != null){
                partidoActualizado.setIdEquipoLocal(Local);
            }
            if(partidoDTO.getEstado() != null){
                partidoActualizado.setEstado(partidoDTO.getEstado());
            }
            if(partidoDTO.getFechahora() != null){
                partidoActualizado.setFechahora(partidoDTO.getFechahora());
            }
            if(partidoDTO.getGolesLocal() != partidoActualizado.getGolesLocal()){
                partidoActualizado.setGolesLocal(partidoDTO.getGolesLocal());
            }
            if(partidoDTO.getGolesVisitante() != partidoActualizado.getGolesVisitante()){
                partidoActualizado.setGolesVisitante(partidoDTO.getGolesVisitante());
            }
            if(partidoDTO.getIdEquipoGanador() != null){
                partidoActualizado.setIdEquipoGanador(partidoDTO.getIdEquipoGanador());
            }
            return partidoRepository.save(partidoActualizado);
        }else {

            System.out.println("entre en el null");
            return null;
        }

    }

    @Transactional
    public Partido definirGanadorPartido(Long id, PartidoDTO partidoDTO){
        Partido partido = new Partido();
        Optional<Partido> partidoOptional = this.obtenerPartidoPorId(id);
        if (partidoOptional.isPresent()) partido = partidoOptional.get();

        if (partido.getEstado().equals("Terminado")){
            throw new ApuestaExcepcion("El partido ya fue definido como TERMINADO.");
        }
        partidoDTO.setEstado("Terminado");

        if(partidoDTO.getGolesVisitante() > partidoDTO.getGolesLocal()){
            partidoDTO.setIdEquipoGanador(partido.getIdEquipoVisitante().getId());
        }else if(partidoDTO.getGolesLocal() > partidoDTO.getGolesVisitante()){
            partidoDTO.setIdEquipoGanador(partido.getIdEquipoLocal().getId());
        }else{
            partidoDTO.setIdEquipoGanador(EMPATE_ID);
        }

        //señal para cambiar las apuestas
        ArrayList<Apuesta> apuestas = apuestaServicio.obtenerApuestaPorPartido(partido);
        for(Apuesta apuesta : apuestas){
            if(apuesta.getIdEquipoApuesta() == partidoDTO.getIdEquipoGanador()){
                apuesta.setResultado(true);
                Usuario usuario = new Usuario();
                Optional<Usuario> usuarioOptional = usuarioServicio.obtenerPorId(apuesta.getIdUsuario().getId());
                if(usuarioOptional.isPresent()) usuario = usuarioOptional.get();

                BigDecimal montoApuesta = apuesta.getMontoApuesta();
                BigDecimal ganacia = montoApuesta.multiply(BigDecimal.valueOf(1.5));
                BigDecimal nuevosFondos = usuario.getFondos().add(ganacia);

                usuario.setFondos(nuevosFondos);
                usuarioServicio.actualizarUsuario(usuario.getId(), usuario);
            }else{
                apuesta.setResultado(false);
            }
        }
        return this.actualizarPartido(id, partidoDTO);
    }


    public Partido cambiarAEnVivo(PartidoDTO partidoDTO){
        Partido partido = new Partido();
        Optional<Partido> partidoOptional = obtenerPartidoPorId(partidoDTO.getId());
        if(partidoOptional.isPresent()){
            partido = partidoOptional.get();
        }

        partido.setEstado("En vivo");
        return partidoRepository.save(partido);
    }
}
