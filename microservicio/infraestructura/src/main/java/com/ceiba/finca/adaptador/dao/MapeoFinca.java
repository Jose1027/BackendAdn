package com.ceiba.finca.adaptador.dao;

import com.ceiba.finca.modelo.dto.DtoFinca;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoFinca implements RowMapper<DtoFinca>, MapperResult {

    @Override
    public DtoFinca mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        String direccion = resultSet.getString("direccion");
        LocalDateTime fechaCreacion = extraerLocalDateTime(resultSet, "fecha_creacion");
        Double precio = resultSet.getDouble("precio");
        int cantidadHabitaciones = resultSet.getInt("cantidad_habitaciones");

        return new DtoFinca(id, nombre, direccion, fechaCreacion, precio, cantidadHabitaciones);
    }


}
