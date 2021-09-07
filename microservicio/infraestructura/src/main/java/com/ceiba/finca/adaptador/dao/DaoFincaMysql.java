package com.ceiba.finca.adaptador.dao;

import com.ceiba.finca.modelo.dto.DtoFinca;
import com.ceiba.finca.puerto.dao.DaoFinca;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoFincaMysql implements DaoFinca {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "finca", value = "listar")
    private static String sqlListar;

    @SqlStatement(namespace = "finca", value = "listarConDisponibilidad")
    private static String sqlListarConDisponibilidad;

    public DaoFincaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoFinca> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoFinca());
    }

    @Override
    public List<DtoFinca> listarConDisponibilidad(String fechaInicio, String fechaFin) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("fechaInicio", fechaInicio);
        paramSource.addValue("fechaFin", fechaFin);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarConDisponibilidad, paramSource, new MapeoFinca());
    }
}
