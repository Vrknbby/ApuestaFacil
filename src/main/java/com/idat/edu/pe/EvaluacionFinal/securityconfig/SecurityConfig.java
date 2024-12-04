package com.idat.edu.pe.EvaluacionFinal.securityconfig;

import com.idat.edu.pe.EvaluacionFinal.Components.JwtAuthenticationEntryPoint;
import com.idat.edu.pe.EvaluacionFinal.Components.JwtAuthenticationFilter;
import com.idat.edu.pe.EvaluacionFinal.Components.JwtUtil;
import com.idat.edu.pe.EvaluacionFinal.service.CustomUsuarioDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String[] PUBLIC_URLS = {
            "/api/autenticacion/login",
    };
    @Autowired
    CustomUsuarioDetailsService usuarioDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CustomUsuarioDetailsService customUsuarioDetailsService;

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactiva CSRF para pruebas
                .authorizeHttpRequests(requests -> requests
                        //.requestMatchers(PUBLIC_URLS).permitAll()
                        //.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        //.anyRequest().authenticated()
                        .anyRequest().permitAll()
                )
                .exceptionHandling(handling -> {
                    handling.authenticationEntryPoint(jwtAuthenticationEntryPoint);
                })
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Configura sesión sin estado
                );
        http.addFilterBefore(jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Autowired
    public void configure (AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(usuarioDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("*")// o poner el local host
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(false); // allowCredentials debe ser false si allowedOrigins es "*"
            }
        };
    }

}
