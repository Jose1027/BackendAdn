package com.ceiba.reserva.puerto.dao;

import com.ceiba.reserva.modelo.dto.DtoReserva;

import java.util.List;

public interface DaoReserva {

    /**
     * Permite listar reservas
     *
     * @return los reservas
     */
    List<DtoReserva> listar();
}
