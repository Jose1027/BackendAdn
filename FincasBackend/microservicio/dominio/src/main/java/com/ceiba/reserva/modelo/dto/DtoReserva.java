package com.ceiba.reserva.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class DtoReserva {
    private Long id;
    private String idUsuario;
    private Long idFinca;
    private Date fechaFinReserva;
    private Double valorTotalReserva;

}
