package com.ceiba.finca.adaptador.repositorio;

import com.ceiba.finca.modelo.entidad.Finca;
import com.ceiba.finca.puerto.repositorio.RepositorioFinca;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioFincaMysql implements RepositorioFinca {
    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="finca", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace="finca", value="eliminar")
    private static String sqlEliminar;

    @SqlStatement(namespace="finca", value="existe")
    private static String sqlExiste;

    @SqlStatement(namespace="finca", value="existeExcluyendoId")
    private static String sqlExisteExcluyendoId;

    public RepositorioFincaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Finca finca) {
        return this.customNamedParameterJdbcTemplate.crear(finca, sqlCrear);
    }

    @Override
    public boolean existe(String nombre) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("nombre", nombre);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,paramSource, Boolean.class);
    }
}
