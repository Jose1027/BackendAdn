package com.ceiba.finca.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DtoFinca {
    private Long id;
    private String nombre;
    private String direccion;
    private LocalDate fechaCreacion;
    private Double precioPorDia;
    private int cantidadHabitaciones;

}
