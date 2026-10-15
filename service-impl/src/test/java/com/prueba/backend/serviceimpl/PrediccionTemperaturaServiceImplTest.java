package com.prueba.backend.serviceimpl;

import com.prueba.backend.dto.PrediccionTemperaturaDto;
import com.prueba.backend.serviceimpl.util.AemetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class PrediccionTemperaturaServiceImplTest {

    private static final String URL_DATOS =
            "https://datos.test/prediccion";

    private AemetService aemetService;
    private PrediccionTemperaturaServiceImpl service;
    private MockRestServiceServer servidor;

    @BeforeEach
    void setUp() {
        aemetService = mock(AemetService.class);

        RestClient.Builder builder = RestClient.builder();
        servidor = MockRestServiceServer.bindTo(builder).build();

        service = new PrediccionTemperaturaServiceImpl(
                builder,
                new ObjectMapper(),
                aemetService
        );

        when(aemetService.obtenerUrlDatosAemet(
                "https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/diaria/03014"
        )).thenReturn(URL_DATOS);
    }

    @Test
    void calculaTemperaturaMediaEnCelsius() {
        prepararRespuesta(LocalDate.now().plusDays(1).toString());

        PrediccionTemperaturaDto resultado =
                service.obtenerPrediccionTemperatura("03014", "G_CEL");

        assertEquals(20.0, resultado.getMediaTemperatura());
        assertEquals("G_CEL", resultado.getUnidadTemperatura());

        servidor.verify();
    }

    @Test
    void convierteTemperaturaAFahrenheit() {
        prepararRespuesta(LocalDate.now().plusDays(1).toString());

        PrediccionTemperaturaDto resultado =
                service.obtenerPrediccionTemperatura("03014", "G_FAH");

        assertEquals(68.0, resultado.getMediaTemperatura());
        assertEquals("G_FAH", resultado.getUnidadTemperatura());

        servidor.verify();
    }

    @Test
    void devuelveSoloLosCuatroPeriodosDePrecipitacion() {
        prepararRespuesta(LocalDate.now().plusDays(1).toString());

        PrediccionTemperaturaDto resultado =
                service.obtenerPrediccionTemperatura("03014", "G_CEL");

        assertEquals(4, resultado.getProbPrecipitacion().size());

        assertEquals("00-06",
                resultado.getProbPrecipitacion().get(0).getPeriodo());
        assertEquals("06-12",
                resultado.getProbPrecipitacion().get(1).getPeriodo());
        assertEquals("12-18",
                resultado.getProbPrecipitacion().get(2).getPeriodo());
        assertEquals("18-24",
                resultado.getProbPrecipitacion().get(3).getPeriodo());

        servidor.verify();
    }

    @Test
    void lanzaExcepcionSiNoHayPrediccionParaManana() {
        prepararRespuesta(LocalDate.now().toString());

        assertThrows(
                IllegalStateException.class,
                () -> service.obtenerPrediccionTemperatura(
                        "03014", "G_CEL")
        );

        servidor.verify();
    }

    private void prepararRespuesta(String fecha) {
        String json = """
                [
                  {
                    "prediccion": {
                      "dia": [
                        {
                          "fecha": "%sT00:00:00",
                          "temperatura": {
                            "maxima": 30,
                            "minima": 10
                          },
                          "probPrecipitacion": [
                            {"periodo": "00-06", "value": 0},
                            {"periodo": "06-12", "value": 10},
                            {"periodo": "12-18", "value": 20},
                            {"periodo": "18-24", "value": 30},
                            {"periodo": "00-24", "value": 15}
                          ]
                        }
                      ]
                    }
                  }
                ]
                """.formatted(fecha);

        servidor.expect(requestTo(URL_DATOS))
                .andRespond(withSuccess(
                        json,
                        MediaType.APPLICATION_JSON
                ));
    }
}