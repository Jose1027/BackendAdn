package com.ceiba.finca.comando.fabrica;

import com.ceiba.finca.comando.ComandoFinca;
import com.ceiba.finca.modelo.entidad.Finca;
import org.springframework.stereotype.Component;

@Component
public class FabricaFinca {

    public Finca crear(ComandoFinca comandoFinca) {
        return new Finca(
                comandoFinca.getId(),
                comandoFinca.getNombre(),
                comandoFinca.getDireccion(),
                comandoFinca.getFechaCreacion(),
                comandoFinca.getPrecioPorDia(),
                comandoFinca.getCantidadHabitaciones()
        );
    }
}
