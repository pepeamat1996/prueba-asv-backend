package com.prueba.backend.dto;

/**
 * The type Probabilidad precipitacion dto.
 */
public class ProbabilidadPrecipitacionDto {

    /**
     * The Probabilidad.
     */
    Integer probabilidad;

    /**
     * The Periodo.
     */
    String periodo;

    /**
     * Instantiates a new Probabilidad precipitacion dto.
     */
    public ProbabilidadPrecipitacionDto() {
    }

    /**
     * Gets probabilidad.
     *
     * @return the probabilidad
     */
    public Integer getProbabilidad() {
        return probabilidad;
    }

    /**
     * Sets probabilidad.
     *
     * @param probabilidad the probabilidad
     */
    public void setProbabilidad(Integer probabilidad) {
        this.probabilidad = probabilidad;
    }

    /**
     * Gets periodo.
     *
     * @return the periodo
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * Sets periodo.
     *
     * @param periodo the periodo
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
