package com.prueba.backend.apirest;

import com.prueba.backend.dto.PrediccionTemperaturaDto;
import com.prueba.backend.service.PrediccionTemperaturaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PrediccionTemperaturaApi.class)
class PrediccionTemperaturaApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PrediccionTemperaturaService prediccionTemperaturaService;

    @Test
    void obtenerPrediccionDevuelveDatosEnJson() throws Exception {
        PrediccionTemperaturaDto prediccion =
                new PrediccionTemperaturaDto();
        prediccion.setMediaTemperatura(20.5);
        prediccion.setUnidadTemperatura("G_CEL");

        when(prediccionTemperaturaService.obtenerPrediccionTemperatura(
                "03014", "G_CEL"))
                .thenReturn(prediccion);

        mockMvc.perform(get(
                        "/api/prediccion/obtenerPrediccionTemperatura")
                        .param("codigoMunicipio", "03014")
                        .param("unidadTemperatura", "G_CEL"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mediaTemperatura").value(20.5))
                .andExpect(jsonPath("$.unidadTemperatura").value("G_CEL"));

        verify(prediccionTemperaturaService)
                .obtenerPrediccionTemperatura("03014", "G_CEL");
    }
}
