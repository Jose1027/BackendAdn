package com.ceiba.finca.modelo.entidad;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Finca {

    private Long id;
    private String nombre;
    private String direccion;
    private LocalDateTime fechaCreacion;
    private Double precio;
    private int cantidadHabitaciones;

    public Finca(Long id, String nombre, String direccion, LocalDateTime fechaCreacion, Double precio, int cantidadHabitaciones) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaCreacion = fechaCreacion;
        this.precio = precio;
        this.cantidadHabitaciones = cantidadHabitaciones;
    }
}
