package com.ceiba.reserva.comando.fabrica;

import com.ceiba.reserva.modelo.entidad.Reserva;
import org.springframework.stereotype.Component;

import com.ceiba.reserva.comando.ComandoReserva;

import java.time.LocalTime;

@Component
public class FabricaReserva {

    public Reserva crear(ComandoReserva comandoReserva) {
        return new Reserva(
                comandoReserva.getId(),
                comandoReserva.getIdUsuario(),
                comandoReserva.getIdFinca(),
                comandoReserva.getFechaInicioReserva().atStartOfDay(),
                comandoReserva.getFechaFinReserva().atTime(23,59,0)
        );
    }

}
