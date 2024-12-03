package com.idat.edu.pe.EvaluacionFinal.controller;

import com.idat.edu.pe.EvaluacionFinal.model.Descuento;
import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioServicio usuarioServicio;

    @GetMapping()
    public ArrayList<Usuario> obtenerUsuarios(){
        return usuarioServicio.obtenerUsuarios();
    }

    @PostMapping()
    public Usuario guardarUsuario(@RequestBody Usuario usuario){
        return this.usuarioServicio.guardarUsuario(usuario);
    }

    @GetMapping(path = "/{id}")
    public Optional<Usuario> obtenerUsuarioPorId(@PathVariable("id") Long id){
        return this.usuarioServicio.obtenerPorId(id);
    }

    @GetMapping("/queryEmail")
    public Optional<Usuario> obtenerUsuarioPorEmail(@RequestParam("email") String email){
        return this.usuarioServicio.obtenerPorEmail(email);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarUsuarioPorId(@PathVariable("id")Long id){
        boolean ok = this.usuarioServicio.eliminarUsuario(id);
        if (ok) return "El usuario se elimino correctamente";
        else return "No se pudo eliminar el Usuario";
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado){
        Usuario usuario = this.usuarioServicio.actualizarUsuario(id, usuarioActualizado);
        if (usuario != null){
            return ResponseEntity.ok(usuario);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/addfondos/{id}")
    public ResponseEntity<Usuario> ingresarFondos(@PathVariable Long id, @RequestBody Usuario usuarioNewFondos){
        Usuario usuario = this.usuarioServicio.ingresarFondos(id, usuarioNewFondos);
        if(usuario != null){
            return ResponseEntity.ok(usuario);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/codigo/{id}")
    public boolean ingresarCodigoDescuento(@PathVariable Long id, @RequestBody Descuento descuentoCod){
        return usuarioServicio.agregarDescuento(id, descuentoCod);
    }

}
