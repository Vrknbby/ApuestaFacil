package com.idat.edu.pe.EvaluacionFinal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idat.edu.pe.EvaluacionFinal.model.Rol;
import com.idat.edu.pe.EvaluacionFinal.service.RolServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RolControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RolServicio rolServicio;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testObtenerRoles() throws Exception {
        ArrayList<Rol> rolesMock = new ArrayList<>();
        rolesMock.add(new Rol("Administrador"));
        rolesMock.add(new Rol("Usuario"));
        when(rolServicio.obtenerRol()).thenReturn(rolesMock);
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/rol")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombreRol", is("Administrador")))
                .andExpect(jsonPath("$[1].nombreRol", is("Usuario")));
    }

    // Test para guardar un rol
    @Test
    void guardarRol() throws Exception {
        Rol rolMock = new Rol("Admin");
        when(rolServicio.guardarRol(rolMock)).thenReturn(rolMock);
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/rol")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(rolMock))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void obtenerRolPorId() throws Exception {

        Rol rolMock = new Rol("Moderador");
        rolMock.setId(1L); // Asignar un ID al rol
        when(rolServicio.obtenerRolPorId(1L)).thenReturn(Optional.of(rolMock));
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/rol/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreRol", is("Moderador")));
    }

    // Test para eliminar un rol por ID
    @Test
    void eliminarRolPorId() throws Exception {
        when(rolServicio.eliminarRol(1L)).thenReturn(true);
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/rol/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("El rol se elimino correctamente")));
    }
}