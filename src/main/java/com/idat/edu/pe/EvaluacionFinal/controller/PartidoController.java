package com.idat.edu.pe.EvaluacionFinal.controller;

import com.idat.edu.pe.EvaluacionFinal.DTO.PartidoDTO;
import com.idat.edu.pe.EvaluacionFinal.model.Partido;
import com.idat.edu.pe.EvaluacionFinal.service.PartidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/partido")
public class PartidoController {

    @Autowired
    PartidoServicio partidoServicio;

    @GetMapping
    public ArrayList<Partido> obtenerPartidos(){
        return this.partidoServicio.obtenerPartidos();
    }

    //Importante poner el RequestBody para que tome correctamente el Post del Json
    @PostMapping
    public Partido guardarPartido(@RequestBody PartidoDTO partidoDTO){
        return this.partidoServicio.guardarPartido(partidoDTO);
    }

    @GetMapping("/{id}")
    public Optional<Partido> obtenerPartidoPorId(@PathVariable("id") Long id){
        return this.partidoServicio.obtenerPartidoPorId(id);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPartidoPorId(@PathVariable("id")Long id){
        boolean ok = this.partidoServicio.eliminarPartido(id);
        if (ok) return "El partido se elimino correctamente";
        else return "No se pudo eliminar el partido";
    }

    //@GetMapping("/query")
    //public ArrayList<Partido> obtenerPartidosPorFecha(@RequestParam("fecha_hora") Date fecha_hora){
        //return this.partidoServicio.obtenerPartidosPorFecha(fecha_hora);
    //}

    @GetMapping("/queryEstado")
    public ArrayList<Partido> obtenerPartidosPorEstado(@RequestParam("estado") String estado){
        return this.partidoServicio.obtenerPartidosPorEstado(estado);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Partido> actualizarPartido(@PathVariable Long id, @RequestBody PartidoDTO partidoDTOactualizado){
        Partido partido = partidoServicio.actualizarPartido(id, partidoDTOactualizado);
        if(partido != null){
            return ResponseEntity.ok(partido);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/results/{id}")
    public ResponseEntity<Partido> partido (@PathVariable Long id, @RequestBody PartidoDTO partidoDTO){
        Partido partido = partidoServicio.definirGanadorPartido(id,partidoDTO);
        return ResponseEntity.ok(partido);
    }

    @PutMapping("/changeState")
    public ResponseEntity<Partido> cambiarEstado(@RequestBody PartidoDTO partidoDTO){
        Partido partido = partidoServicio.cambiarAEnVivo(partidoDTO);
        return ResponseEntity.ok(partido);
    }

}
