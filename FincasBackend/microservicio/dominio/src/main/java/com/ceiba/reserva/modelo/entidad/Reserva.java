package com.ceiba.reserva.modelo.entidad;


import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;


@Getter
public class Reserva {

    private static final String SE_DEBE_INGRESAR_LA_FECHA_CREACION = "Se debe ingresar la fecha de creación";
    private static final String LA_CLAVE_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A = "La clave debe tener una longitud mayor o igual a %s";
    private static final String SE_DEBE_INGRESAR_LA_CLAVE = "Se debe ingresar la clave";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DE_USUARIO = "Se debe ingresar el nombre de usuario";

    private static final int LONGITUD_MINIMA_CLAVE = 4;

    private Long id;
    private String idUsuario;
    private Long idFinca;
    private LocalDateTime fechaInicioReserva;
    private LocalDateTime fechaFinReserva;
    private Double valorTotalReserva;

    public Reserva(Long id, String idUsuario, Long idFinca, LocalDateTime fechaInicioReserva, LocalDateTime fechaFinReserva, Double valorTotalReserva) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idFinca = idFinca;
        this.fechaInicioReserva = fechaInicioReserva;
        this.fechaFinReserva = fechaFinReserva;
        this.valorTotalReserva = valorTotalReserva;
    }
}
