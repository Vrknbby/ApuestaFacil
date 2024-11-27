package com.idat.edu.pe.EvaluacionFinal.controller;

import com.idat.edu.pe.EvaluacionFinal.model.Rol;

import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.service.RolServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping("/api/rol")
public class RolController {

    @Autowired
    RolServicio rolServicio;

    @GetMapping()
    public ArrayList<Rol> obtenerRol(){
        return rolServicio.obtenerRol();
    }

    @PostMapping()
    public Rol guardarRol(@RequestBody Rol rol){
        return this.rolServicio.guardarRol(rol);
    }

    @GetMapping(path = "/{id}")
    public Optional<Rol> obtenerRolPorId(@PathVariable("id") Long id){
        return this.rolServicio.obtenerRolPorId(id);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarRolPorId(@PathVariable("id")Long id){
        boolean ok = this.rolServicio.eliminarRol(id);
        if (ok) return "El rol se elimino correctamente";
        else return "No se pudo eliminar el Rol";
    }
}
