package com.idat.edu.pe.EvaluacionFinal.controller;

import com.idat.edu.pe.EvaluacionFinal.model.Equipo;
import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.service.EquipoServicio;
import com.idat.edu.pe.EvaluacionFinal.service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipo")
public class EquipoController {

    @Autowired
    EquipoServicio equipoServicio;

    @GetMapping()
    public ArrayList<Equipo> obtenerEquipo(){
        return equipoServicio.obtenerEquipo();
    }

    @PostMapping()
    public Equipo guardarEquipo(@RequestBody Equipo equipo){
        return this.equipoServicio.guardarEquipo(equipo);
    }

    @GetMapping(path = "/{id}")
    public Optional<Equipo> obtenerEquipoPorId(@PathVariable("id") Long id){
        return this.equipoServicio.obtenerEquipoPorId(id);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarEquipoPorId(@PathVariable("id")Long id){
        boolean ok = this.equipoServicio.eliminarEquipo(id);
        if (ok) return "El equipo se elimino correctamente";
        else return "No se pudo eliminar el Equipo";
    }

    @PutMapping(path = "/edit/{id}")
    public ResponseEntity<Equipo> actualizarEquipo (@PathVariable Long id,@RequestBody Equipo equipoActualizado){
        Equipo equipo = this.equipoServicio.actualizarEquipo(id, equipoActualizado);
        if (equipo != null){
            return ResponseEntity.ok(equipo);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
