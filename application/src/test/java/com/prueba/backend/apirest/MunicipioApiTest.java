package com.prueba.backend.apirest;

import com.prueba.backend.dto.MunicipioDto;
import com.prueba.backend.service.MunicipioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MunicipioApi.class)
class MunicipioApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MunicipioService municipioService;

    @Test
    void obtenerMunicipiosDevuelveListaEnJson() throws Exception {
        MunicipioDto municipio = new MunicipioDto();
        municipio.setCodigo("03014");
        municipio.setNombre("Alicante");

        when(municipioService.obtenerMunicipios())
                .thenReturn(List.of(municipio));

        mockMvc.perform(get("/api/municipio/obtenerMunicipios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].codigo").value("03014"))
                .andExpect(jsonPath("$[0].nombre").value("Alicante"));
    }
}