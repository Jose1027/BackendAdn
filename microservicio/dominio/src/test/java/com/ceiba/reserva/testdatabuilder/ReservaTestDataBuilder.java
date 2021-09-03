package com.ceiba.reserva.testdatabuilder;


import com.ceiba.finca.modelo.dto.DtoFinca;
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
        fechaInicioReserva = LocalDateTime.of(2021,9,1,0,0);
        fechaFinReserva = LocalDateTime.of(2021,9,3,0,0);
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

    public Reserva build() {
        return new Reserva(id, idUsuario, idFinca, fechaInicioReserva, fechaFinReserva);
    }

    public Reserva buildIdFincaNoExiste() {
        return new Reserva(id, idUsuario, 45L, fechaInicioReserva, fechaFinReserva);
    }

    public DtoFinca buildIDtoFinca() {
        DtoFinca dtoFinca = new DtoFinca(1L, "la hermosa", "vereda la hermosa", LocalDateTime.now().minusYears(2), 400000.0, 6);
        return dtoFinca;
    }

    public Reserva buildFinDeSemana() {
        return new Reserva(id, idUsuario, idFinca,LocalDateTime.of(2021,9,4,0,0), LocalDateTime.of(2021,9,5,0,0));
    }

    public Reserva buildSemanaYFinDeSemana() {
        return new Reserva(id, idUsuario, idFinca,LocalDateTime.of(2021,9,3,0,0), LocalDateTime.of(2021,9,5,0,0));
    }

    public ReservaTestDataBuilder conFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicioReserva = fechaInicio;
        return this;
    }

    public ReservaTestDataBuilder conFechaFin(LocalDateTime fechaFin) {
        this.fechaFinReserva = fechaFin;
        return this;
    }

    public Reserva buildFechaInicioDespuesDeFechaFin() {
        return new Reserva(id, idUsuario, idFinca, LocalDateTime.now().plusDays(10), fechaFinReserva);
    }

    public Reserva buildFechaFinSuperaLaSemana() {
        return new Reserva(id, idUsuario, idFinca, fechaInicioReserva, LocalDateTime.now().plusDays(15));
    }

}
