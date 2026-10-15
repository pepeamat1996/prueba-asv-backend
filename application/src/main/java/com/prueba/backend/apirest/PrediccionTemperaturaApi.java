package com.prueba.backend.apirest;

import com.prueba.backend.dto.PrediccionTemperaturaDto;
import com.prueba.backend.service.PrediccionTemperaturaService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Prediccion temperatura api.
 */
@RestController
@RequestMapping("/api/prediccion")
public class PrediccionTemperaturaApi {

    /**
     * The Prediccion temp service.
     */
    private final PrediccionTemperaturaService prediccionTempService;

    /**
     * Instantiates a new Prediccion temperatura api.
     *
     * @param prediccionTempService the prediccion temp service
     */
    public PrediccionTemperaturaApi(
            PrediccionTemperaturaService prediccionTempService) {
        this.prediccionTempService = prediccionTempService;
    }

    /**
     * Obtener prediccion temperatura prediccion temperatura dto.
     *
     * @param codigoMunicipio   the codigo municipio
     * @param unidadTemperatura the unidad temperatura
     * @return the prediccion temperatura dto
     */
    @GetMapping(value = "/obtenerPrediccionTemperatura", produces = MediaType.APPLICATION_JSON_VALUE)
    public PrediccionTemperaturaDto obtenerPrediccionTemperatura(
            @RequestParam(name="codigoMunicipio") String codigoMunicipio,
            @RequestParam(name="unidadTemperatura") String unidadTemperatura) {

        return prediccionTempService.obtenerPrediccionTemperatura(
                codigoMunicipio,
                unidadTemperatura);
    }
}