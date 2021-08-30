package com.ceiba.reserva.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class RepositorioReservaMysql implements RepositorioReserva {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="reserva", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace="reserva", value="eliminar")
    private static String sqlEliminar;

    @SqlStatement(namespace="reserva", value="existe")
    private static String sqlExiste;

    @SqlStatement(namespace="reserva", value="existeExcluyendoId")
    private static String sqlExisteExcluyendoId;

    public RepositorioReservaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Reserva reserva) {
        return this.customNamedParameterJdbcTemplate.crear(reserva, sqlCrear);
    }

    @Override
    public boolean existe(Long idFinca, LocalDateTime fechaInicioReserva, LocalDateTime fechaFinReserva) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idFinca", idFinca);
        paramSource.addValue("fechaInicioReserva", fechaInicioReserva);
        paramSource.addValue("fechaFinReserva", fechaFinReserva);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,paramSource, Boolean.class);
    }

}
