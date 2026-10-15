
package com.prueba.backend.serviceimpl;

import com.prueba.backend.dto.MunicipioDto;
import com.prueba.backend.dto.RespuestaAemetDto;
import com.prueba.backend.service.MunicipioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * The type Municipio service.
 */
@Service
public class MunicipioServiceImpl implements MunicipioService {

    /**
     * The Api key.
     */
    @Value("${aemet.api.key}")
    private String apiKey;

    /**
     * The Rest client.
     */
    private final RestClient restClient = RestClient.create();
    /**
     * The Object mapper.
     */
    private final ObjectMapper objectMapper = new ObjectMapper();


    /**
     * Obtener municipios list.
     *
     * @return the list
     */
    @Override
    public List<MunicipioDto> obtenerMunicipios() {
        String urlDatos = obtenerUrlDatosAemet();

        String json = restClient.get()
                .uri(urlDatos)
                .retrieve()
                .body(String.class);

        return objectMapper.readValue(
                json,
                new TypeReference<>() {
                }
        );
    }


    /**
     * Obtener url datos aemet string.
     *
     * @return the string
     */
    private String obtenerUrlDatosAemet() {
        RespuestaAemetDto respuesta = restClient.get()
                .uri("https://opendata.aemet.es/opendata/api/maestro/municipios")
                .header("api_key", apiKey)
                .retrieve()
                .body(RespuestaAemetDto.class);

        if (respuesta == null || respuesta.getDatos() == null
                || respuesta.getDatos().isBlank()) {
            throw new IllegalStateException(
                    "AEMET no ha devuelto la URL de los datos"
            );
        }

        return respuesta.getDatos();
    }


}
