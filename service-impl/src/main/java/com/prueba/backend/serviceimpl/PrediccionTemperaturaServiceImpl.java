package com.prueba.backend.serviceimpl;

import com.prueba.backend.dto.PrediccionTemperaturaDto;
import com.prueba.backend.dto.ProbabilidadPrecipitacionDto;
import com.prueba.backend.service.PrediccionTemperaturaService;
import com.prueba.backend.serviceimpl.util.AemetService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Prediccion temperatura service.
 */
@Service
public class PrediccionTemperaturaServiceImpl implements PrediccionTemperaturaService {

    /**
     * The Rest client.
     */
    private final RestClient restClient;
    /**
     * The Object mapper.
     */
    private final ObjectMapper objectMapper;
    /**
     * The Aemet service.
     */
    private final AemetService aemetService;

    /**
     * Instantiates a new Prediccion temperatura service.
     *
     * @param restClientBuilder the rest client builder
     * @param objectMapper      the object mapper
     * @param aemetService      the aemet service
     */
    public PrediccionTemperaturaServiceImpl(
            RestClient.Builder restClientBuilder,
            ObjectMapper objectMapper,
            AemetService aemetService) {
        this.restClient = restClientBuilder.build();
        this.objectMapper = objectMapper;
        this.aemetService = aemetService;
    }

    /**
     * Obtener prediccion temperatura prediccion temperatura dto.
     *
     * @param codigoMunicipio   the codigo municipio
     * @param unidadTemperatura the unidad temperatura
     * @return the prediccion temperatura dto
     */
    @Override
    public PrediccionTemperaturaDto obtenerPrediccionTemperatura(String codigoMunicipio, String unidadTemperatura) {
        String urlDatos = aemetService.obtenerUrlDatosAemet(
                "https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/diaria/"
                        + codigoMunicipio);

        // Descargar la respuesta JSON
        String json = restClient.get()
                .uri(urlDatos)
                .retrieve()
                .body(String.class);

        JsonNode respuesta = objectMapper.readTree(json);
        LocalDate manana = LocalDate.now().plusDays(1);
        JsonNode prediccionManana = null;

        for (JsonNode dia : respuesta.path(0).path("prediccion").path("dia")) {
            if (dia.path("fecha").asString().startsWith(manana.toString())) {
                prediccionManana = dia;
                break;
            }
        }

        if (prediccionManana == null) {
            throw new IllegalStateException(
                    "No hay datos de previsión para mañana en AEMET");
        }

        return crearPrediccionTemperaturaDto(prediccionManana, unidadTemperatura);
    }

    /**
     * Crear prediccion temperatura dto prediccion temperatura dto.
     *
     * @param prediccionManana  the prediccion manana
     * @param unidadTemperatura the unidad temperatura
     * @return the prediccion temperatura dto
     */
    private PrediccionTemperaturaDto crearPrediccionTemperaturaDto(
            JsonNode prediccionManana, String unidadTemperatura) {

        //A partir de la predicción del día de mañana, calculamos la temperatura media en su unidad correspondiente (ºC o ºF)
        JsonNode temperatura = prediccionManana.path("temperatura");

        double media = Math.round(
                ((temperatura.path("maxima").asDouble()
                        + temperatura.path("minima").asDouble()) / 2.0) * 10.0) / 10.0;

        if ("G_FAH".equals(unidadTemperatura)) {
            media = media * 9.0 / 5.0 + 32.0;
        }

        PrediccionTemperaturaDto dto = new PrediccionTemperaturaDto();
        dto.setMediaTemperatura(media);
        dto.setUnidadTemperatura(unidadTemperatura);

        List<ProbabilidadPrecipitacionDto> probabilidades = new ArrayList<>();
        //Se muestran los periodos de cada 6 horas, ya que aemet muestra también periodos completos (00-24) que no aportan la información que queremos
        List<String> periodosValidos = List.of("00-06", "06-12", "12-18", "18-24");

        for (JsonNode precipitacion : prediccionManana.path("probPrecipitacion")) {
            String periodo = precipitacion.path("periodo").asString();

            if (periodosValidos.contains(periodo)) {
                ProbabilidadPrecipitacionDto prob = new ProbabilidadPrecipitacionDto();
                prob.setPeriodo(precipitacion.path("periodo").asString());
                prob.setProbabilidad(precipitacion.path("value").asInt());
                probabilidades.add(prob);
            }
        }

        dto.setProbPrecipitacion(probabilidades);

        return dto;
    }

}
