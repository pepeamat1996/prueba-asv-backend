
package com.prueba.backend.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The type Municipio dto.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MunicipioDto {

    /**
     * The Codigo.
     */
    @JsonAlias("id")
    private String codigo;
    /**
     * The Nombre.
     */
    private String nombre;

    /**
     * Instantiates a new Municipio dto.
     */
    public MunicipioDto() {
    }

    /**
     * Gets codigo.
     *
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sets codigo.
     *
     * @param codigo the codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Gets nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets nombre.
     *
     * @param nombre the nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
