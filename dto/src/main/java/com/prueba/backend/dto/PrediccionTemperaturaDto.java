package com.prueba.backend.dto;

import java.util.List;

/**
 * The type Prediccion temperatura dto.
 */
public class PrediccionTemperaturaDto {

    /**
     * The Media temperatura.
     */
    Double mediaTemperatura;

    /**
     * The Unidad temperatura.
     */
    String unidadTemperatura;

    /**
     * The Prob precipitacion.
     */
    List<ProbabilidadPrecipitacionDto> probPrecipitacion;

    /**
     * Instantiates a new Prediccion temperatura dto.
     */
    public PrediccionTemperaturaDto() {
    }

    /**
     * Gets media temperatura.
     *
     * @return the media temperatura
     */
    public Double getMediaTemperatura() {
        return mediaTemperatura;
    }

    /**
     * Sets media temperatura.
     *
     * @param mediaTemperatura the media temperatura
     */
    public void setMediaTemperatura(Double mediaTemperatura) {
        this.mediaTemperatura = mediaTemperatura;
    }

    /**
     * Gets unidad temperatura.
     *
     * @return the unidad temperatura
     */
    public String getUnidadTemperatura() {
        return unidadTemperatura;
    }

    /**
     * Sets unidad temperatura.
     *
     * @param unidadTemperatura the unidad temperatura
     */
    public void setUnidadTemperatura(String unidadTemperatura) {
        this.unidadTemperatura = unidadTemperatura;
    }

    /**
     * Gets prob precipitacion.
     *
     * @return the prob precipitacion
     */
    public List<ProbabilidadPrecipitacionDto> getProbPrecipitacion() {
        return probPrecipitacion;
    }

    /**
     * Sets prob precipitacion.
     *
     * @param probPrecipitacion the prob precipitacion
     */
    public void setProbPrecipitacion(List<ProbabilidadPrecipitacionDto> probPrecipitacion) {
        this.probPrecipitacion = probPrecipitacion;
    }
}

