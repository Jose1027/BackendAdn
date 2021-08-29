package com.ceiba.reserva.puerto.repositorio;

import com.ceiba.reserva.modelo.entidad.Reserva;

public interface RepositorioReserva {
    /**
     * Permite crear una reserva
     *
     * @param reserva
     * @return el id generado
     */
    Long crear(Reserva reserva);

    /**
     * Permite eliminar una reserva
     *
     * @param id
     */
    void eliminar(Long id);

    /**
     * Permite validar si existe una reserva con un nombre
     *
     * @param nombre
     * @return si existe o no
     */
    boolean existe(String nombre);

    /**
     * Permite validar si existe una reserva con un nombre excluyendo un id
     *
     * @param nombre
     * @return si existe o no
     */
    boolean existeExcluyendoId(Long id, String nombre);

}
