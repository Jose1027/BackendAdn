package com.ceiba.reserva.puerto.repositorio;

import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDateTime;

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
     * @param idFinca
     * @return si existe o no
     */
    boolean existe(Long idFinca, LocalDateTime fechaInicioReserva, LocalDateTime fechaFinReserva);

}
