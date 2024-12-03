package com.idat.edu.pe.EvaluacionFinal.controller;
import com.idat.edu.pe.EvaluacionFinal.model.Descuento;
import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.service.DescuentoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/descuento")
public class DescuentoController {
    @Autowired
    DescuentoServicio descuentoServicio;

    @GetMapping()
    public ArrayList<Descuento> obtenerDescuentos(){
        return descuentoServicio.obtenerDescuentos();
    }

    @PostMapping()
    public Descuento guardarDescuento(@RequestBody Descuento descuento){
        return this.descuentoServicio.guardarDescuento(descuento);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarDescuento(@PathVariable("id")Long id){
        boolean ok = this.descuentoServicio.eliminarDescuento(id);
        if (ok) return "El Descuento se elimino correctamente";
        else return "No se pudo eliminar el Descuento";
    }

}
