package com.ceiba.reserva.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import org.springframework.jdbc.core.RowMapper;

public class MapeoReserva implements RowMapper<DtoReserva>, MapperResult {

    @Override
    public DtoReserva mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String idUsuario = resultSet.getString("id_usuario");
        Long idFinca = resultSet.getLong("id_finca");
        LocalDateTime fechaInicioReserva = extraerLocalDateTime(resultSet, "fecha_inicio_reserva");
        LocalDateTime fechaFinReserva = extraerLocalDateTime(resultSet, "fecha_fin_reserva");
        Double valorTotalReserva = resultSet.getDouble("valor_total_reserva");

        return new DtoReserva(id, idUsuario, idFinca, fechaInicioReserva, fechaFinReserva, valorTotalReserva);
    }

}
