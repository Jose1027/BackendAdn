package com.ceiba.reserva.testdatabuilder;


import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDateTime;

public class ReservaTestDataBuilder {

    private Long id;
    private String idUsuario;
    private Long idFinca;
    private LocalDateTime fechaInicioReserva;
    private LocalDateTime fechaFinReserva;
    private Double valorTotalReserva;

    public ReservaTestDataBuilder() {

        idUsuario = "1088302416";
        idFinca = 1L;
        fechaInicioReserva = LocalDateTime.now();
        fechaFinReserva = LocalDateTime.now();
        valorTotalReserva = 800000.0;
    }

    public ReservaTestDataBuilder conIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

    public ReservaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public Reserva build(){
        return new Reserva(id,idUsuario,idFinca,fechaFinReserva,fechaInicioReserva,valorTotalReserva);
    }
}
