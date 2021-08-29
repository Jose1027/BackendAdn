package com.ceiba.finca.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoFinca {

    private Long id;
    private String nombre;
    private String direccion;
    private LocalDateTime fechaCreacion;
    private Double precioPorDia;
    private int cantidadHabitaciones;

}
