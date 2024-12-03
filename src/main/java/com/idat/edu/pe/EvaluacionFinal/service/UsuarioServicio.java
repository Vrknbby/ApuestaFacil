package com.idat.edu.pe.EvaluacionFinal.service;

import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioServicio{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ArrayList<Usuario> obtenerUsuarios(){
        return (ArrayList<Usuario>) usuarioRepository.findAll();

    }


    public Usuario guardarUsuario(Usuario usuario){
        Usuario user = new Usuario(
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getFechaNacimiento(),
                usuario.getFondos(),
                usuario.getDni()
        );
        return usuarioRepository.save(user);
    }

    public Optional<Usuario> obtenerPorId(Long id){
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> obtenerPorEmail(String email){return usuarioRepository.findByEmail(email);}

    public Optional<Usuario> optenerPorDNI(String dni) {return usuarioRepository.findByDni(dni);}

    public boolean eliminarUsuario(Long id){
        try{
            usuarioRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false;
        }
    }

    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Optional<Usuario> usuarioExistenteOptional = usuarioRepository.findById(id);
        if (usuarioExistenteOptional.isPresent()) {
            Usuario usuarioExistente = usuarioExistenteOptional.get();

            if (usuarioActualizado.getNombre() != null) {
                usuarioExistente.setNombre(usuarioActualizado.getNombre());
            }
            if (usuarioActualizado.getEmail() != null) {
                usuarioExistente.setEmail(usuarioActualizado.getEmail());
            }
            if (usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isEmpty()) {
                usuarioExistente.setPassword(usuarioActualizado.getPassword());
            }
            if (usuarioActualizado.getFondos() != null) {
                usuarioExistente.setFondos(usuarioActualizado.getFondos());
            }
            if (usuarioActualizado.getFechaNacimiento() != null) {
                usuarioExistente.setFechaNacimiento(usuarioActualizado.getFechaNacimiento());
            }

            return usuarioRepository.save(usuarioExistente);
        } else {

            return null;
        }
    }

    public Usuario ingresarFondos(Long id, Usuario usuario){
        Usuario nuevoaux = new Usuario();
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            nuevoaux = usuarioOptional.get();
        }

        BigDecimal nuevosFondos = nuevoaux.getFondos().add(usuario.getFondos());
        nuevoaux.setFondos(nuevosFondos);

        return this.actualizarUsuario(id, nuevoaux);
    }

    public Usuario retirarFondos(Long id, Usuario usuario){
        return usuario;
    }

}




