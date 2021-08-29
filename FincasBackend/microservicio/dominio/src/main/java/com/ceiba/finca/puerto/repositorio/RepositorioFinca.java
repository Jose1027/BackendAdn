package com.ceiba.finca.puerto.repositorio;


import com.ceiba.finca.modelo.entidad.Finca;

public interface RepositorioFinca {
    /**
     * Permite crear un usuario
     *
     * @param finca
     * @return el id generado
     */
    Long crear(Finca finca);

    /**
     * Permite actualizar una finca
     *
     * @param finca
     */
    void actualizar(Finca finca);

    /**
     * Permite eliminar una finca
     *
     * @param id
     */
    void eliminar(Long id);

    /**
     * Permite validar si existe una finca con un nombre
     *
     * @param nombre
     * @return si existe o no
     */
    boolean existe(String nombre);

    /**
     * Permite validar si existe una finca con un nombre excluyendo un id
     *
     * @param nombre
     * @return si existe o no
     */
    boolean existeExcluyendoId(Long id, String nombre);

}
