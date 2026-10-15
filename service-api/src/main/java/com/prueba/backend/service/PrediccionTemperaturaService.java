package com.prueba.backend.service;

import com.prueba.backend.dto.PrediccionTemperaturaDto;

/**
 * The interface Prediccion temperatura service.
 */
public interface PrediccionTemperaturaService {

    /**
     * Obtener prediccion temperatura prediccion temperatura dto.
     *
     * @param codigoMunicipio   the codigo municipio
     * @param unidadTemperatura the unidad temperatura
     * @return the prediccion temperatura dto
     */
    PrediccionTemperaturaDto obtenerPrediccionTemperatura(
            String codigoMunicipio,
            String unidadTemperatura);
}
