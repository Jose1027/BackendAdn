package com.ceiba.finca.testdatabuilder;

import com.ceiba.finca.modelo.entidad.Finca;


import java.time.LocalDateTime;

public class FincaTestDataBuilder {

    private Long id;
    private String nombre;
    private String direccion;
    private LocalDateTime fechaCreacion;
    private Double precioPorDia;
    private int cantidadHabitaciones;

    public FincaTestDataBuilder() {
        nombre = "el paraiso";
        direccion = "combia";
        fechaCreacion = LocalDateTime.now();
        precioPorDia = 400000.0;
        cantidadHabitaciones = 5;
    }

    public FincaTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public FincaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public Finca build() {
        return new Finca(id, nombre, direccion, fechaCreacion, precioPorDia, cantidadHabitaciones);
    }
}
