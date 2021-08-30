package com.ceiba.reserva.servicio;

import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;


public class ServicioCrearReserva {

    private static final String LA_FINCA_YA_EXISTE_EN_EL_SISTEMA = "La finca ya se encuentra reservada para las fechas seleccionadas";

    private final RepositorioReserva repositorioReserva;

    public ServicioCrearReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    public Long ejecutar(Reserva reserva) {
        validarExistenciaPrevia(reserva);
        return this.repositorioReserva.crear(reserva);
    }

    private void validarExistenciaPrevia(Reserva reserva) {
        boolean existe = this.repositorioReserva.existe(reserva.getIdFinca(), reserva.getFechaInicioReserva(), reserva.getFechaFinReserva());
        if (existe) {
            throw new ExcepcionDuplicidad(LA_FINCA_YA_EXISTE_EN_EL_SISTEMA);
        }
    }

    public int calcularDiasEntreFechas(LocalDateTime fechaInicial, LocalDateTime fechaFinal){
        return (int) DAYS.between(fechaFinal,fechaInicial);
    }
}
