package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.comando.ComandoReserva;

import java.time.LocalDate;


public class ComandoReservaTestDataBuilder {

    private Long id;
    private String idUsuario;
    private Long idFinca;
    private LocalDate fechaInicioReserva;
    private LocalDate fechaFinReserva;
    private Double valorTotalReserva;

    public ComandoReservaTestDataBuilder() {
        idUsuario = "1234";
        idFinca = 1L;
        fechaInicioReserva = LocalDate.now();
        fechaFinReserva = LocalDate.now().plusDays(3);
        valorTotalReserva = 400000.0;
    }


    public ComandoReserva build() {
        return new ComandoReserva(id, idUsuario, idFinca, fechaInicioReserva, fechaFinReserva);
    }
}
