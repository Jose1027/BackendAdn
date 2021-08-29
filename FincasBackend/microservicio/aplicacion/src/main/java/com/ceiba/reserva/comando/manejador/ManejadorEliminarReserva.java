package com.ceiba.reserva.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.finca.servicio.ServicioEliminarFinca;
import org.springframework.stereotype.Component;


@Component
public class ManejadorEliminarReserva implements ManejadorComando<Long> {

    private final ManejadorEliminarReserva manejadorEliminarReserva;

    public ManejadorEliminarReserva(ManejadorEliminarReserva manejadorEliminarReserva) {
        this.manejadorEliminarReserva = manejadorEliminarReserva;
    }

    public void ejecutar(Long idUsuario) {
        this.manejadorEliminarReserva.ejecutar(idUsuario);
    }
}
