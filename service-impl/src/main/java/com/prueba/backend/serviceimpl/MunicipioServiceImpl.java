
package com.prueba.backend.serviceimpl;

import com.prueba.backend.dto.MunicipioDto;
import com.prueba.backend.service.MunicipioService;
import com.prueba.backend.serviceimpl.util.AemetService;
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
     * Instantiates a new Municipio service.
     *
     * @param restClientBuilder the rest client builder
     * @param objectMapper      the object mapper
     * @param aemetService      the aemet service
     */
    public MunicipioServiceImpl(
            RestClient.Builder restClientBuilder,
            ObjectMapper objectMapper,
            AemetService aemetService) {
        this.restClient = restClientBuilder.build();
        this.objectMapper = objectMapper;
        this.aemetService = aemetService;
    }

    /**
     * Obtener municipios list.
     *
     * @return the list
     */
    @Override
    public List<MunicipioDto> obtenerMunicipios() {
        String urlDatos = aemetService.obtenerUrlDatosAemet(
                "https://opendata.aemet.es/opendata/api/maestro/municipios"
        );

        String json = restClient.get()
                .uri(urlDatos)
                .retrieve()
                .body(String.class);

        List<MunicipioDto> municipios = objectMapper.readValue(
                json,
                new TypeReference<>() {
                }
        );

        //Es necesario mapear cada municipio para quitar el id que pone AEMET (id44001 --> 44001)
        municipios.forEach(municipio ->
                municipio.setCodigo(municipio.getCodigo().replaceFirst("^id", ""))
        );

        return municipios;
    }
}