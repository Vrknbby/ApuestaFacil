package com.idat.edu.pe.EvaluacionFinal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.idat.edu.pe.EvaluacionFinal.DTO.MovimientoDTO;
import com.idat.edu.pe.EvaluacionFinal.model.Movimiento;
import com.idat.edu.pe.EvaluacionFinal.model.Usuario;
import com.idat.edu.pe.EvaluacionFinal.repository.MovimientoRepository;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MovimientoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    void obtenerMovimiento() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/movimiento")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void obtenerMovimientoPorId() throws Exception {
        Long idMovimiento = 4L; // Ajusta según un ID válido en tu base de datos
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/movimiento/{id}", idMovimiento)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void guardarMovimiento() throws Exception {
        Usuario usuario = usuarioServicio.obtenerPorId(6L).orElseThrow(); // Asegúrate de tener un usuario válido en tu base de datos

        MovimientoDTO nuevoMovimiento = new MovimientoDTO();
        nuevoMovimiento.setTipo("Ingreso");
        nuevoMovimiento.setFecha("2024-12-15");
        nuevoMovimiento.setMonto(new BigDecimal("100.50"));
        nuevoMovimiento.setIdUsuario(usuario.getId());

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/movimiento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(nuevoMovimiento))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipo").value("Ingreso"))
                .andExpect(jsonPath("$.monto").value(100.50));
    }

    @Test
    void actualizarMovimiento() throws Exception {
        Long idMovimiento = 4L; // Ajusta según un ID válido en tu base de datos
        MovimientoDTO movimientoActualizado = new MovimientoDTO();
        movimientoActualizado.setTipo("Egreso");
        movimientoActualizado.setMonto(new BigDecimal("200.00"));

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/movimiento/edit/{id}", idMovimiento)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(movimientoActualizado))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipo").value("Egreso"))
                .andExpect(jsonPath("$.monto").value(200.00));
    }

    @Test
    void eliminarMovimientoPorId() throws Exception {
        Long idMovimiento = 4L; // Ajusta según un ID válido en tu base de datos

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/movimiento/{id}", idMovimiento)
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
