package com.ceiba.finca.servicio.testdatabuilder;

import com.ceiba.finca.comando.ComandoFinca;
import com.ceiba.finca.modelo.entidad.Finca;

import java.time.LocalDateTime;

public class ComandoFincaTestDataBuilder {
    private Long id;
    private String nombre;
    private String direccion;
    private LocalDateTime fechaCreacion;
    private Double precioPorDia;
    private int cantidadHabitaciones;

    public ComandoFincaTestDataBuilder() {
        nombre = "el paraiso";
        direccion = "combia";
        fechaCreacion = LocalDateTime.now();
        precioPorDia = 400000.0;
        cantidadHabitaciones = 5;
    }

    public ComandoFinca build() {
        return new ComandoFinca(id, nombre, direccion, fechaCreacion, precioPorDia, cantidadHabitaciones);
    }
}

