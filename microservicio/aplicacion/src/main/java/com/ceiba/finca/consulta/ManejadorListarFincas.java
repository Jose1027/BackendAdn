package com.ceiba.finca.consulta;

import com.ceiba.finca.modelo.dto.DtoFinca;
import com.ceiba.finca.puerto.dao.DaoFinca;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarFincas {

    private final DaoFinca daoFinca;

    public ManejadorListarFincas(DaoFinca daoFinca) {
        this.daoFinca = daoFinca;
    }

    public List<DtoFinca> ejecutar() {
        return this.daoFinca.listar();
    }

    public List<DtoFinca> ejecutar(String fechaInicio, String fechaFin) {
        return this.daoFinca.listarConDisponibilidad(fechaInicio, fechaFin);
    }
}
