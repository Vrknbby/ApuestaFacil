package com.idat.edu.pe.EvaluacionFinal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.idat.edu.pe.EvaluacionFinal.DTO.PartidoDTO;
import com.idat.edu.pe.EvaluacionFinal.model.Apuesta;
import com.idat.edu.pe.EvaluacionFinal.model.Equipo;
import com.idat.edu.pe.EvaluacionFinal.model.Partido;
import com.idat.edu.pe.EvaluacionFinal.repository.PartidoRepository;
import com.idat.edu.pe.EvaluacionFinal.service.PartidoServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PartidoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private PartidoServicio partidoServicio;

    @Test
    void testObtenerPartidos() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/partido")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGuardarPartido() throws Exception{

        PartidoDTO nuevoPartido = new PartidoDTO();

        nuevoPartido.setIdEquipoLocal(1L);
        nuevoPartido.setIdEquipoVisitante(2L);
        nuevoPartido.setGolesVisitante(0);
        nuevoPartido.setGolesLocal(0);
        nuevoPartido.setEstado("Próximo");
        nuevoPartido.setFechahora(new Date());

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/partido")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(nuevoPartido))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void testObtenerPartidoPorId() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/partido/11")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testEliminarPartidoPorId() throws Exception{
        Long idPartidoAEliminar = 20L;

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/partido/{id}", idPartidoAEliminar)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());  // Verifica que la respuesta sea 200 OK

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/partido")  // Usa la ruta correcta
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testActualizarPartido() throws Exception{

        Long idPartidoExistente = 11L;
        PartidoDTO partidoDTOActualizado = new PartidoDTO();

        partidoDTOActualizado.setIdEquipoLocal(3L);
        partidoDTOActualizado.setIdEquipoVisitante(4L);
        partidoDTOActualizado.setGolesVisitante(1);
        partidoDTOActualizado.setGolesLocal(2);
        partidoDTOActualizado.setEstado("En Vivo");
        partidoDTOActualizado.setFechahora(new Date());

        String usuarioJson = asJsonString(partidoDTOActualizado);

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/partido/edit/{id}", idPartidoExistente)
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