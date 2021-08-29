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
     * Permite validar si existe una reserva con un nombre
     *
     * @param nombre
     * @return si existe o no
     */
    boolean existe(String nombre);

}
