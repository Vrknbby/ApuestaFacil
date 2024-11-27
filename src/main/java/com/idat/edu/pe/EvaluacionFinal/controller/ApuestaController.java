package com.idat.edu.pe.EvaluacionFinal.controller;

import com.idat.edu.pe.EvaluacionFinal.DTO.ApuestaDTO;
import com.idat.edu.pe.EvaluacionFinal.model.Apuesta;
import com.idat.edu.pe.EvaluacionFinal.model.Equipo;
import com.idat.edu.pe.EvaluacionFinal.model.Partido;
import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.service.ApuestaServicio;
import com.idat.edu.pe.EvaluacionFinal.service.EquipoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/apuesta")
public class ApuestaController {

    @Autowired
    ApuestaServicio apuestaServicio;

    @GetMapping()
    public ArrayList<Apuesta> obtenerApuesta(){
        return apuestaServicio.obtenerApuesta();
    }

    @PostMapping()
    public Apuesta guardarApuesta(@RequestBody ApuestaDTO apuestaDTO){
        return this.apuestaServicio.guardarApuesta(apuestaDTO);
    }

    @GetMapping(path = "/{id}")
    public Optional<Apuesta> obtenerApuestaPorId(@PathVariable("id") Long id){
        return this.apuestaServicio.obtenerApuestaPorId(id);
    }


    @DeleteMapping(path = "/{id}")
    public String eliminarApuestaPorId(@PathVariable("id")Long id){
        boolean ok = this.apuestaServicio.eliminarApuesta(id);
        if (ok) return "La apuesta se elimino correctamente";
        else return "No se pudo eliminar la apuesta";
    }

    @GetMapping("/queryPartido")
    public ArrayList<Apuesta> obtenerApuestaPorPartido(@RequestParam("partidoId") Partido id){
        return this.apuestaServicio.obtenerApuestaPorPartido(id);
    }

    @GetMapping("/queryUsuario")
    public ArrayList<Apuesta> obtenerApuestaPorUsuario(@RequestParam("usuarioId") Usuario id){
        return this.apuestaServicio.obtenerApuestaPorUsuario(id);
    }


}
