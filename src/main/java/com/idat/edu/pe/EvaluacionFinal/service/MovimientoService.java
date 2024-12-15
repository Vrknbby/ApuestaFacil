package com.idat.edu.pe.EvaluacionFinal.service;

import com.idat.edu.pe.EvaluacionFinal.DTO.MovimientoDTO;
import com.idat.edu.pe.EvaluacionFinal.model.Movimiento;
import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class MovimientoService {

    @Autowired
    MovimientoRepository movimientoRepository;
    @Autowired
    private UsuarioServicio usuarioServicio;

    public ArrayList<Movimiento> obtenerMovimiento(){
        return (ArrayList<Movimiento>) movimientoRepository.findAll();
    }

    public Movimiento obtenerMovimientoPorId(Long id){
        return movimientoRepository.findById(id).orElse(null);
    }

    public Movimiento guardarMovimiento(MovimientoDTO movimientoDTO){

        Usuario usuario = new Usuario();

        Optional<Usuario> usuarioOptional = usuarioServicio.obtenerPorId(movimientoDTO.getIdUsuario());

        if(usuarioOptional.isPresent()){
            usuario = usuarioOptional.get();
        }

        Movimiento movimiento = new Movimiento(
                movimientoDTO.getTipo(),
                movimientoDTO.getFecha(),
                movimientoDTO.getMonto(),
                usuario
        );

        return movimientoRepository.save(movimiento);
    }

    public Movimiento actualizarMovimiento(Long id, MovimientoDTO movimientoDTO){

        Usuario usuario = new Usuario();

        if (movimientoDTO.getIdUsuario() != null){
            Optional<Usuario> usuarioOptional = usuarioServicio.obtenerPorId(movimientoDTO.getIdUsuario());
            if(usuarioOptional.isPresent()){
                usuario = usuarioOptional.get();
            }
        }

        Optional<Movimiento> movimientoOptional = movimientoRepository.findById(id);

        if (movimientoOptional.isPresent()){

            Movimiento movimientoupdate = movimientoOptional.get();

            if (movimientoDTO.getTipo() != null){
                movimientoupdate.setTipo(movimientoDTO.getTipo());
            }
            if (movimientoDTO.getFecha() != null){
                movimientoupdate.setFecha(movimientoDTO.getFecha());
            }
            if (movimientoDTO.getMonto() != null){
                movimientoupdate.setMonto(movimientoDTO.getMonto());
            }
            if (movimientoDTO.getIdUsuario() != null){
                movimientoupdate.setIdUsuario(usuario);
            }

            return movimientoRepository.save(movimientoupdate);
        }
        else {
            return null;
        }

    }

    public boolean eliminarMovimiento(Long id){
        try{
            movimientoRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false;
        }
    }
}
