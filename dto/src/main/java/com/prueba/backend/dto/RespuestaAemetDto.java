package com.prueba.backend.dto;

/**
 * The type Respuesta aemet dto.
 */
public class RespuestaAemetDto {
    /**
     * The Descripcion.
     */
    private String descripcion;
    /**
     * The Estado.
     */
    private int estado;
    /**
     * The Datos.
     */
    private String datos;
    /**
     * The Metadatos.
     */
    private String metadatos;

    /**
     * Instantiates a new Respuesta aemet dto.
     */
    public RespuestaAemetDto() {
    }

    /**
     * Gets descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets descripcion.
     *
     * @param descripcion the descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets estado.
     *
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Sets estado.
     *
     * @param estado the estado
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * Gets datos.
     *
     * @return the datos
     */
    public String getDatos() {
        return datos;
    }

    /**
     * Sets datos.
     *
     * @param datos the datos
     */
    public void setDatos(String datos) {
        this.datos = datos;
    }

    /**
     * Gets metadatos.
     *
     * @return the metadatos
     */
    public String getMetadatos() {
        return metadatos;
    }

    /**
     * Sets metadatos.
     *
     * @param metadatos the metadatos
     */
    public void setMetadatos(String metadatos) {
        this.metadatos = metadatos;
    }
}