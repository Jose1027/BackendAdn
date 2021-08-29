package com.ceiba.reserva.consulta;

import java.util.List;

import com.ceiba.reserva.puerto.dao.DaoReserva;
import org.springframework.stereotype.Component;

import com.ceiba.reserva.modelo.dto.DtoReserva;

@Component
public class ManejadorListarReservas {

    private final DaoReserva daoReserva;

    public ManejadorListarReservas(DaoReserva daoReserva) {
        this.daoReserva = daoReserva;
    }

    public List<DtoReserva> ejecutar() {
        return this.daoReserva.listar();
    }
}
