package com.prueba.backend.serviceimpl;

import com.prueba.backend.dto.MunicipioDto;
import com.prueba.backend.serviceimpl.util.AemetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class MunicipioServiceImplTest {

    private static final String URL_DATOS =
            "https://datos.test/municipios";

    private MunicipioServiceImpl service;
    private MockRestServiceServer servidor;
    private AemetService aemetService;

    @BeforeEach
    void setUp() {
        aemetService = mock(AemetService.class);

        RestClient.Builder builder = RestClient.builder();
        servidor = MockRestServiceServer.bindTo(builder).build();

        service = new MunicipioServiceImpl(
                builder,
                new ObjectMapper(),
                aemetService
        );

        when(aemetService.obtenerUrlDatosAemet(
                "https://opendata.aemet.es/opendata/api/maestro/municipios"
        )).thenReturn(URL_DATOS);
    }

    @Test
    void eliminaPrefijoIdDeLosCodigosDeMunicipio() {
        servidor.expect(requestTo(URL_DATOS))
                .andRespond(withSuccess("""
                        [
                          {"id":"id03014","nombre":"Alicante"},
                          {"id":"id28079","nombre":"Madrid"}
                        ]
                        """, MediaType.APPLICATION_JSON));

        List<MunicipioDto> municipios = service.obtenerMunicipios();

        assertEquals(2, municipios.size());
        assertEquals("03014", municipios.get(0).getCodigo());
        assertEquals("28079", municipios.get(1).getCodigo());

        servidor.verify();
    }
}