package com.idat.edu.pe.EvaluacionFinal.controller;

import com.idat.edu.pe.EvaluacionFinal.DTO.MovimientoDTO;
import com.idat.edu.pe.EvaluacionFinal.model.Movimiento;
import com.idat.edu.pe.EvaluacionFinal.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/movimiento")
public class MovimientoController {

    @Autowired
    MovimientoService movimientoService;

    @GetMapping
    public ArrayList<Movimiento> obtenerMovimiento(){
        return this.movimientoService.obtenerMovimiento();
    }

    @GetMapping("/{id}")
    public Movimiento obtenerMovimientoPorId(@PathVariable("id") Long id){
        return this.movimientoService.obtenerMovimientoPorId(id);
    }

    @PostMapping
    public Movimiento guardarMovimiento(@RequestBody MovimientoDTO movimientoDTO){
        return this.movimientoService.guardarMovimiento(movimientoDTO);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Movimiento> actualizarMovimiento(@PathVariable("id") Long id, @RequestBody MovimientoDTO movimientoDTO){
        Movimiento movimiento = this.movimientoService.actualizarMovimiento(id, movimientoDTO);
        if (movimiento != null) return ResponseEntity.ok(movimiento);
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public String eliminarMovimientoPorId(@PathVariable("id") Long id){
        boolean ok = this.movimientoService.eliminarMovimiento(id);
        if (ok) return "El movimiento se elimino correctamente";
        else return "No se pudo eliminar el movimiento";
    }


}
