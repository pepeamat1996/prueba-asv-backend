
package com.prueba.backend.apirest;

import com.prueba.backend.dto.MunicipioDto;
import com.prueba.backend.service.MunicipioService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Municipio api.
 */
@RestController
@RequestMapping("/api/municipio")
public class MunicipioApi {

    /**
     * The Municipio service.
     */
    private final MunicipioService municipioService;

    /**
     * Instantiates a new Municipio api.
     *
     * @param municipioService the municipio service
     */
    public MunicipioApi(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    /**
     * Obtener municipios list.
     *
     * @return the list
     */
    @GetMapping(value = "/obtenerMunicipios", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MunicipioDto> obtenerMunicipios() {
        return municipioService.obtenerMunicipios();
    }
}
