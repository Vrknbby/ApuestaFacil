package com.idat.edu.pe.EvaluacionFinal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.idat.edu.pe.EvaluacionFinal.model.Promocion;
import com.idat.edu.pe.EvaluacionFinal.repository.PromocionRepository;
import com.idat.edu.pe.EvaluacionFinal.service.PromocionServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PromocionControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private PromocionServicio promocionServicio;

    @Autowired
    private PromocionRepository promocionRepository;

    @Test
    void testObtenerPromociones() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/promocion")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testOtenerPromocionPorCodigo() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/promocion/HALOWEEN")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGuardarPromocion() throws Exception{
        Promocion nuevaPromocion = new Promocion("RecargaAdicional", 100.0, "NAVIDAD", "NAVIDAD" ,"ACTIVO");

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/promocion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(nuevaPromocion))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipoPromocion").value("RecargaAdicional"))
                .andExpect(jsonPath("$.montoPromocion").value(100.0))
                .andExpect(jsonPath("$.nombrePromocion").value("NAVIDAD"))
                .andExpect(jsonPath("$.codigoPromocion").value("NAVIDAD"))
                .andExpect(jsonPath("$.estadoPromocion").value("ACTIVO"));
    }

    @Test
    void testEliminarPromocionPorCodigo() throws Exception{
        Long idPromocionAEliminar = 2L;

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/promocion/{id}", idPromocionAEliminar)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());  // Verifica que la respuesta sea 200 OK

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/promocion")  // Usa la ruta correcta
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testActualizarPromocion() throws Exception{
        Long idPromocionExistente = 4L;
        Promocion promocionActualizada = new Promocion();
        promocionActualizada.setEstadoPromocion("ACTIVO");
        promocionActualizada.setNombrePromocion("AñoNuevo");
        promocionActualizada.setTipoPromocion("ApuestaGratis");
        promocionActualizada.setMontoPromocion(100.0);
        promocionActualizada.setCodigoPromocion("NUEVO2025");

        String promocionJson = asJsonString(promocionActualizada);

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/promocion/edit/{id}", idPromocionExistente)
                        .content(promocionJson)
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