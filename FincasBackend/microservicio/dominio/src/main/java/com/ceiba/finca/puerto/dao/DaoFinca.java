package com.ceiba.finca.puerto.dao;

import com.ceiba.finca.modelo.dto.DtoFinca;

import java.util.List;

public interface DaoFinca {

    /**
     * Permite listar fincas
     *
     * @return las fincas
     */
    List<DtoFinca> listar();
}
