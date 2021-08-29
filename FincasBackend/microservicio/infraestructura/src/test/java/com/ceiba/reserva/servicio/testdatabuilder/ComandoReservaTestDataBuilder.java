package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.comando.ComandoReserva;

import java.util.Date;


public class ComandoReservaTestDataBuilder {

    private Long id;
    private String idUsuario;
    private Long idFinca;
    private Date fechaFinReserva;
    private Double valorTotalReserva;

    public ComandoReservaTestDataBuilder() {
        idUsuario = "1234";
        idFinca = 1L;
        fechaFinReserva = new Date();
        valorTotalReserva = 400000.0;
    }

    public ComandoReservaTestDataBuilder conIdUsuario(String IdUsuario) {
        this.idUsuario = IdUsuario;
        return this;
    }

    public ComandoReserva build() {
        return new ComandoReserva(id, idUsuario, idFinca, fechaFinReserva, valorTotalReserva);
    }
}
