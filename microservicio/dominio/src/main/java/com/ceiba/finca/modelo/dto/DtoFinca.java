package com.ceiba.finca.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoFinca {
    private Long id;
    private String nombre;
    private String direccion;
    private LocalDateTime fechaCreacion;
    private Double precioPorDia;
    private int cantidadHabitaciones;

}
