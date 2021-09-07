package com.ceiba.finca.controlador;

import com.ceiba.finca.consulta.ManejadorListarFincas;
import com.ceiba.finca.modelo.dto.DtoFinca;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fincas")
@Api(tags = {"Controlador consulta fincas"})
public class ConsultaControladorFinca {

    private ManejadorListarFincas manejadorListarFincas;

    public ConsultaControladorFinca(ManejadorListarFincas manejadorListarFincas) {
        this.manejadorListarFincas = manejadorListarFincas;
    }

    @GetMapping
    @ApiOperation("Listar Fincas")
    public List<DtoFinca> listar() {
        return this.manejadorListarFincas.ejecutar();
    }

    @GetMapping("/{fechaInicio}/{fechaFin}")
    @ApiOperation("Listar fincas con disponibilidad")
    public List<DtoFinca> listarConDisponibilidad(@PathVariable String fechaInicio, @PathVariable String fechaFin) {
        return this.manejadorListarFincas.ejecutar(fechaInicio, fechaFin);
    }
}
