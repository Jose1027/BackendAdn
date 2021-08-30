package com.ceiba.reserva.modelo.entidad;


import lombok.Getter;

import java.time.LocalDateTime;
import lombok.Setter;


@Getter
@Setter
public class Reserva {

    private Long id;
    private String idUsuario;
    private Long idFinca;
    private LocalDateTime fechaInicioReserva;
    private LocalDateTime fechaFinReserva;
    private Double valorTotalReserva;

    public Reserva(Long id, String idUsuario, Long idFinca, LocalDateTime fechaInicioReserva, LocalDateTime fechaFinReserva) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idFinca = idFinca;
        this.fechaInicioReserva = fechaInicioReserva;
        this.fechaFinReserva = fechaFinReserva;
    }
}
