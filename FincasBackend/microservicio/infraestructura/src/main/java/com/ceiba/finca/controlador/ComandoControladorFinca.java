package com.ceiba.finca.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.finca.comando.ComandoFinca;
import com.ceiba.finca.comando.manejador.ManejadorCrearFinca;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fincas")
@Api(tags = {"Controlador comando finca"})
public class ComandoControladorFinca {

    private final ManejadorCrearFinca manejadorCrearFinca;

    @Autowired
    public ComandoControladorFinca(ManejadorCrearFinca manejadorCrearFinca) {
        this.manejadorCrearFinca = manejadorCrearFinca;
    }

    @PostMapping
    @ApiOperation("Crear Finca")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoFinca comandoFinca) {
        return manejadorCrearFinca.ejecutar(comandoFinca);
    }
}
