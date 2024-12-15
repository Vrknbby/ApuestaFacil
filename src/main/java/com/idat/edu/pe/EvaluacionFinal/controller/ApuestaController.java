package com.idat.edu.pe.EvaluacionFinal.controller;

import com.idat.edu.pe.EvaluacionFinal.DTO.ApuestaDTO;
import com.idat.edu.pe.EvaluacionFinal.model.Apuesta;
import com.idat.edu.pe.EvaluacionFinal.model.Partido;
import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.service.ApuestaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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


    @DeleteMapping(path = "/{id}/{idUsuario}")
    public ResponseEntity<String> eliminarApuestaPorId(@PathVariable("id")Long idApuesta, @PathVariable("idUsuario") Long idusuario){
        boolean ok = this.apuestaServicio.eliminarApuesta(idApuesta,idusuario);
        if (ok) return ResponseEntity.ok("La apuesta se elimino correctamente");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo eliminar la apuesta correctamente.");

    }

    @GetMapping("/queryPartido")
    public ArrayList<Apuesta> obtenerApuestaPorPartido(@RequestParam("partidoId") Partido id){
        return this.apuestaServicio.obtenerApuestaPorPartido(id);
    }

    @GetMapping("/queryUsuario")
    public ArrayList<Apuesta> obtenerApuestaPorUsuario(@RequestParam("usuarioId") Usuario id){
        return this.apuestaServicio.obtenerApuestaPorUsuario(id);
    }

    @PutMapping("/edit/{id}")
    public Apuesta actualizarApuesta(@PathVariable("id") Long idApuesta,@RequestBody ApuestaDTO apuestaDTO){
        return this.apuestaServicio.actualizarApuesta(idApuesta,apuestaDTO);
    }


}
