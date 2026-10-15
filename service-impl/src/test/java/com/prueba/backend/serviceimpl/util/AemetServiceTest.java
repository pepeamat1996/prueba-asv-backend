package com.prueba.backend.serviceimpl.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class AemetServiceTest {

    private static final String URL_ENDPOINT =
            "https://aemet.test/api/municipios";
    private static final String API_KEY = "clave-de-prueba";

    private AemetService service;
    private MockRestServiceServer servidor;

    @BeforeEach
    void setUp() {
        RestClient.Builder builder = RestClient.builder();
        servidor = MockRestServiceServer.bindTo(builder).build();

        service = new AemetService(builder, API_KEY);
    }

    @Test
    void devuelveLaUrlDeLosDatosYEnviaLaApiKey() {
        servidor.expect(requestTo(URL_ENDPOINT))
                .andExpect(header("api_key", API_KEY))
                .andRespond(withSuccess("""
                        {"datos":"https://aemet.test/datos/123"}
                        """, MediaType.APPLICATION_JSON));

        String resultado = service.obtenerUrlDatosAemet(URL_ENDPOINT);

        assertEquals("https://aemet.test/datos/123", resultado);
        servidor.verify();
    }

    @Test
    void lanzaExcepcionCuandoLaUrlDeDatosEstaVacia() {
        servidor.expect(requestTo(URL_ENDPOINT))
                .andExpect(header("api_key", API_KEY))
                .andRespond(withSuccess("""
                        {"datos":""}
                        """, MediaType.APPLICATION_JSON));

        assertThrows(
                IllegalStateException.class,
                () -> service.obtenerUrlDatosAemet(URL_ENDPOINT)
        );

        servidor.verify();
    }
}