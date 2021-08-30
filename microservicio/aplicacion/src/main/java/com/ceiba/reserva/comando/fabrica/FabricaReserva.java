package com.ceiba.reserva.comando.fabrica;

import com.ceiba.reserva.modelo.entidad.Reserva;
import org.springframework.stereotype.Component;

import com.ceiba.reserva.comando.ComandoReserva;

@Component
public class FabricaReserva {

    public Reserva crear(ComandoReserva comandoReserva) {
        return new Reserva(
                comandoReserva.getId(),
                comandoReserva.getIdUsuario(),
                comandoReserva.getIdFinca(),
                comandoReserva.getFechaInicioReserva(),
                comandoReserva.getFechaFinReserva()
        );
    }

}
