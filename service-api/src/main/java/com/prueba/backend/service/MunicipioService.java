
package com.prueba.backend.service;

import com.prueba.backend.dto.MunicipioDto;

import java.util.List;

/**
 * The interface Municipio service.
 */
public interface MunicipioService {

    /**
     * Obtener municipios list.
     *
     * @return the list
     */
    List<MunicipioDto> obtenerMunicipios();
}
