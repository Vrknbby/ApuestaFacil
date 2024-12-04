package com.idat.edu.pe.EvaluacionFinal.controller;

import com.idat.edu.pe.EvaluacionFinal.model.Equipo;
import com.idat.edu.pe.EvaluacionFinal.model.Promocion;
import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.model.UsuarioPromocion;
import com.idat.edu.pe.EvaluacionFinal.service.PromocionServicio;
import com.idat.edu.pe.EvaluacionFinal.service.UsuarioPromocionServicio;
import org.springframework.beans.factory.annotation.Autowired;
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




}
