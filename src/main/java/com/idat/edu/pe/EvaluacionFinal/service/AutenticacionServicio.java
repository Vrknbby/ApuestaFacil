package com.idat.edu.pe.EvaluacionFinal.service;

import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacionServicio {

    @Autowired
    private UsuarioServicio usuarioServicio;

    public boolean authenticate(String email, String password) {
        Optional<Usuario> usuarioOptional = usuarioServicio.obtenerPorEmail(email);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            // Comparar la contraseña directamente sin encriptación
            return password.equals(usuario.getPassword());
        }
        return false;
    }

    public boolean authenticateDNI(String dni){
        Optional<Usuario> usuarioOptional = usuarioServicio.optenerPorDNI(dni);

        if (usuarioOptional.isPresent()){return true;}
        else return false;
    }
}
