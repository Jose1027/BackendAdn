package com.ceiba.reserva.modelo.entidad;


import lombok.Getter;

import java.time.LocalDate;
import lombok.Setter;


@Getter
@Setter
public class Reserva {

    private Long id;
    private String idUsuario;
    private Long idFinca;
    private LocalDate fechaInicioReserva;
    private LocalDate fechaFinReserva;
    private Double valorTotalReserva;

    public Reserva(Long id, String idUsuario, Long idFinca, LocalDate fechaInicioReserva, LocalDate fechaFinReserva) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idFinca = idFinca;
        this.fechaInicioReserva = fechaInicioReserva;
        this.fechaFinReserva = fechaFinReserva;
    }
}
