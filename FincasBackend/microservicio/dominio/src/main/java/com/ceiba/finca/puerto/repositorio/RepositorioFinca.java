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
     * Permite validar si existe una finca con un nombre
     *
     * @param nombre
     * @return si existe o no
     */
    boolean existe(String nombre);


}
