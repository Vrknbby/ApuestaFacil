package com.idat.edu.pe.EvaluacionFinal.controller;

import com.idat.edu.pe.EvaluacionFinal.model.Equipo;
import com.idat.edu.pe.EvaluacionFinal.model.Promocion;
import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.model.UsuarioPromocion;
import com.idat.edu.pe.EvaluacionFinal.service.PromocionServicio;
import com.idat.edu.pe.EvaluacionFinal.service.UsuarioPromocionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/promocion")
public class PromocionController {

    @Autowired
    PromocionServicio promocionServicio;

    @GetMapping()
    public List<Promocion> obtenerPromociones(){
        return promocionServicio.obtenerPromociones();
    }

    @GetMapping(path = "/{codigo}")
    public Promocion obtenerPromocionPorCodigo(@PathVariable("codigo") String codigo){
        return this.promocionServicio.buscarPorCodigo(codigo);
    }

    @PostMapping()
    public Promocion guardarPromocion(@RequestBody Promocion promocion){
        return this.promocionServicio.guardarPromocion(promocion);
    }

    @DeleteMapping(path = "/{id}")
    public String obtenerPromocionPorCodigo(@PathVariable("id") Long id){
        boolean ok = this.promocionServicio.eliminarPromocion(id);
        if (ok) return "La promocion se elimino correctamente";
        else return "No se pudo eliminar la promocion";
    }

    @PutMapping(path = "/edit/{id}")
    public ResponseEntity<Promocion> actualizarPromocion (@PathVariable Long id, @RequestBody Promocion promocionActualizado){
        Promocion promocion = this.promocionServicio.actualizarPromocion(promocionActualizado, id);
        if (promocion != null){
            return ResponseEntity.ok(promocion);
        }else{
            return ResponseEntity.notFound().build();
        }
    }




}
