package com.idat.edu.pe.EvaluacionFinal.controller;

import com.idat.edu.pe.EvaluacionFinal.DTO.PartidoDTO;
import com.idat.edu.pe.EvaluacionFinal.DTO.UsuarioPromocionDTO;
import com.idat.edu.pe.EvaluacionFinal.model.Equipo;
import com.idat.edu.pe.EvaluacionFinal.model.Partido;
import com.idat.edu.pe.EvaluacionFinal.model.UsuarioPromocion;
import com.idat.edu.pe.EvaluacionFinal.service.UsuarioPromocionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuariopromocion")
public class UsuarioPromocionController {
    @Autowired
    UsuarioPromocionServicio usuarioPromocionServicio;

    @GetMapping
    public Optional<UsuarioPromocion> obtenerPromocionPorUsuarioYPromocion(@RequestBody UsuarioPromocionDTO usuarioPromocionDTO){
        return this.usuarioPromocionServicio.obtenerPorUsuarioYPromocion(usuarioPromocionDTO);

    }

    @PostMapping
    public UsuarioPromocion guardarUsuarioPromocion(@RequestBody UsuarioPromocionDTO usuarioPromocion){
        return this.usuarioPromocionServicio.guardarUsuarioPromocion(usuarioPromocion);
    }

}
