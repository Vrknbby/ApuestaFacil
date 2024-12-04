package com.idat.edu.pe.EvaluacionFinal.controller;

import com.idat.edu.pe.EvaluacionFinal.Components.JwtUtil;
import com.idat.edu.pe.EvaluacionFinal.DTO.LoginRequestDTO;
import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.service.AutenticacionServicio;
import com.idat.edu.pe.EvaluacionFinal.service.CustomUsuarioDetailsService;
import com.idat.edu.pe.EvaluacionFinal.service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/autenticacion")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUsuarioDetailsService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AutenticacionServicio autenticacionServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;


    @PostMapping("/login2")
    public ResponseEntity<String> autenticacionUsuario(@RequestBody LoginRequestDTO loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = userService.loadUserByUsername(loginRequest.getEmail());
            String token = jwtUtil.generateToken(userDetails.getUsername());

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<Usuario>> login(@RequestParam String email, @RequestParam String password) {
        boolean isAuthenticated = autenticacionServicio.authenticate(email, password);
        if (isAuthenticated) {
            Optional<Usuario> usuario = usuarioServicio.obtenerPorEmail(email);
            return ResponseEntity.ok(usuario);
        } else {
            return null;
        }
    }
}


