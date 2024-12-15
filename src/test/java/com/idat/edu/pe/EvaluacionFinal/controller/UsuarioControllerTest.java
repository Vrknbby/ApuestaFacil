package com.idat.edu.pe.EvaluacionFinal.controller;

import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.repository.UsuarioRepository;
import com.idat.edu.pe.EvaluacionFinal.service.UsuarioServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UsuarioControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    void testObtenerUsuarios() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/usuario")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGuardarUsuario() throws Exception {
        Usuario nuevoUsuario = new Usuario("Nuevo Usuario", "nuevo@usuario.com", "pass123", LocalDate.parse("1995-05-15"), new BigDecimal("3000.00") ,"75368889");

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(nuevoUsuario))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Nuevo Usuario"))
                .andExpect(jsonPath("$.email").value("nuevo@usuario.com"));
    }

    @Test
    void testEliminarUsuarioPorId() throws Exception {
        Long idUsuarioAEliminar = 6L;

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/usuario/{id}", idUsuarioAEliminar)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());  // Verifica que la respuesta sea 200 OK

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/usuario")  // Usa la ruta correcta
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testObtenerUsuarioPorID() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/usuario/6")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testActualizarUsuario() throws Exception {

        Long idUsuarioExistente = 6L;
        Usuario usuarioActualizado = new Usuario();
        usuarioActualizado.setNombre("Nuevo Nombre");
        usuarioActualizado.setEmail("nuevo.email@example.com");
        usuarioActualizado.setPassword("nuevoPassword123");
        usuarioActualizado.setFechaNacimiento(LocalDate.parse("1990-01-01"));
        usuarioActualizado.setFondos(new BigDecimal("1500.00"));
        usuarioActualizado.setDni("12345678");

        String usuarioJson = asJsonString(usuarioActualizado);

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/usuario/edit/{id}", idUsuarioExistente)
                        .content(usuarioJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    private static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
