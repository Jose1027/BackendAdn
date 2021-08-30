package com.ceiba.finca.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.finca.comando.ComandoFinca;
import com.ceiba.finca.comando.fabrica.FabricaFinca;
import com.ceiba.finca.modelo.entidad.Finca;
import com.ceiba.finca.servicio.ServicioCrearFinca;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearFinca  implements ManejadorComandoRespuesta<ComandoFinca, ComandoRespuesta<Long>> {

    private final FabricaFinca fabricaFinca;
    private final ServicioCrearFinca servicioCrearFinca;

    public ManejadorCrearFinca(FabricaFinca fabricaFinca, ServicioCrearFinca servicioCrearFinca) {
        this.fabricaFinca = fabricaFinca;
        this.servicioCrearFinca = servicioCrearFinca;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoFinca comandoFinca){
        Finca finca = this.fabricaFinca.crear(comandoFinca);
        return new ComandoRespuesta<>(this.servicioCrearFinca.ejecutar(finca));
    }
}
