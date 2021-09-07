package com.ceiba.reserva.modelo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DtoReserva {
    private Long id;
    private String idUsuario;
    private Long idFinca;
    private LocalDate fechaInicioReserva;
    private LocalDate fechaFinReserva;
    private Double valorTotalReserva;

}
