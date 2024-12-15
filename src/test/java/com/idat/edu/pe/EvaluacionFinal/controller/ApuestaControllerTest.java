package com.idat.edu.pe.EvaluacionFinal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.idat.edu.pe.EvaluacionFinal.DTO.ApuestaDTO;
import com.idat.edu.pe.EvaluacionFinal.model.Apuesta;
import com.idat.edu.pe.EvaluacionFinal.model.Partido;
import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.service.ApuestaServicio;
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
class ApuestaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ApuestaServicio apuestaServicio;

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
    void obtenerApuesta() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/apuesta")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void guardarApuesta() throws Exception {

        ApuestaDTO apuestaDTO = new ApuestaDTO(11L, 3L, new BigDecimal("150.00"), LocalDate.of(1999, 6, 6), true, 2L);


        mvc.perform(MockMvcRequestBuilders
                        .post("/api/apuesta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(apuestaDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.montoApuesta").value(150.0))
                .andExpect(jsonPath("$.resultado").value(true));
    }


    @Test
    void obtenerApuestaPorId() throws Exception {
        Long idApuesta = 13L;

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/apuesta/{id}", idApuesta)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void eliminarApuestaPorId() throws Exception {
        Long idApuesta = 13L;
        Long idUsuario = 2L;

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/apuesta/{id}/{idUsuario}", idApuesta, idUsuario)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void obtenerApuestaPorUsuario() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/apuesta/queryUsuario")
                        .param("usuarioId", "2")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void obtenerApuestaPorPartido() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/apuesta/queryPartido")
                        .param("partidoId", "11") // Agregar el parámetro partidoId a la URL
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()); // Verificar que el estado de la respuesta sea 200 OK
    }

    @Test
    void actualizarApuesta() throws Exception {

        ApuestaDTO apuestaDTOactualizada = new ApuestaDTO(11L, 3L, new BigDecimal("150.00"), LocalDate.of(1999, 6, 6), true, 2L);

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/apuesta/edit/{id}", 13L)
                        .content(asJsonString(apuestaDTOactualizada))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}