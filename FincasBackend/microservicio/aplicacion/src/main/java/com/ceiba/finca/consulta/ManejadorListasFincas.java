package com.ceiba.finca.consulta;

import com.ceiba.finca.modelo.dto.DtoFinca;
import com.ceiba.finca.puerto.dao.DaoFinca;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListasFincas {

    private final DaoFinca daoFinca;

    public ManejadorListasFincas(DaoFinca daoFinca) {
        this.daoFinca = daoFinca;
    }

    public List<DtoFinca> ejecutar() {
        return this.daoFinca.listar();
    }
}
