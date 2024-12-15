package com.idat.edu.pe.EvaluacionFinal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.idat.edu.pe.EvaluacionFinal.model.Equipo;
import com.idat.edu.pe.EvaluacionFinal.repository.EquipoRepository;
import com.idat.edu.pe.EvaluacionFinal.service.EquipoServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class EquipoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EquipoServicio equipoServicio;

    @Autowired
    private EquipoRepository equipoRepository;

    @Test
    public void testObtenerEquipo() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/equipo")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testObtenerEquipoPorID() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/equipo/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testActualizarEquipo() throws Exception {

        Long idEquipoExistente = 1L;
        Equipo equipoActualizado = new Equipo();
        equipoActualizado.setNombre("Nuevo Nombre");
        equipoActualizado.setPais("Nuevo País");
        equipoActualizado.setFechaFundacion("2000-01-01");
        equipoActualizado.setPresidente("Nuevo Presidente");

        String equipoJson = asJsonString(equipoActualizado);  // Método que convierte a JSON

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/equipo/edit/{id}", idEquipoExistente)
                        .content(equipoJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testEliminarUsuarioPorId() throws Exception {
        Long idEquipoAEliminar = 406L; //Equipo no debe estar asociado a un Partido

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/equipo/{id}", idEquipoAEliminar)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());  // Verifica que la respuesta sea 200 OK

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/equipo")  // Usa la ruta correcta
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
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
    @Test
    void testGuardarEquipo() throws Exception {
        Equipo nuevoEquipo = new Equipo("Real Madrid", "España", "06-03-1902", "Florentino Pérez");

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/equipo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(nuevoEquipo)) // Convertir objeto a JSON
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Real Madrid"))
                .andExpect(jsonPath("$.pais").value("España"));
    }
}
