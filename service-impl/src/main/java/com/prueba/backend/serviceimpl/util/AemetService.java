package com.prueba.backend.serviceimpl.util;

import com.prueba.backend.dto.RespuestaAemetDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 * The type Aemet service.
 */
@Service
public class AemetService {

    /**
     * The Api key.
     */
    private final String apiKey;
    /**
     * The Rest client.
     */
    private final RestClient restClient;

    /**
     * Instantiates a new Aemet service.
     *
     * @param restClientBuilder the rest client builder
     * @param apiKey            the api key
     */
    public AemetService(
            RestClient.Builder restClientBuilder,
            @Value("${aemet.api.key}") String apiKey) {
        this.restClient = restClientBuilder.build();
        this.apiKey = apiKey;
    }

    /**
     * Obtener url datos aemet string.
     *
     * @param urlEndpoint the url endpoint
     * @return the string
     */
    public String obtenerUrlDatosAemet(String urlEndpoint) {
        RespuestaAemetDto respuesta = restClient.get()
                .uri(urlEndpoint)
                .header("api_key", apiKey)
                .retrieve()
                .body(RespuestaAemetDto.class);

        if (respuesta == null || respuesta.getDatos() == null
                || respuesta.getDatos().isBlank()) {
            throw new IllegalStateException(
                    "AEMET no ha devuelto la URL de los datos");
        }

        return respuesta.getDatos();
    }
}