package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.finca.modelo.dto.DtoFinca;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;


public class ServicioCrearReserva {

    private static final String LA_FINCA_YA_ESTA_RESERVADA_EN_EL_SISTEMA = "La finca ya se encuentra reservada para las fechas seleccionadas";
    private static final String LA_FINCA_NO_EXISTE_EN_EL_SISTEMA = "La finca no existe en el sistema";
    private static final String FECHA_INICIO_MAYOR_QUE_FECHA_FIN = "La fecha de inicio de la reserva no puede ser mayor que la fecha final";
    private static final String MENSAJE_MAXIMO_DIAS = "Solo se puede reservar maximo 7 dias";
    private static final double PROCENTAJE_DE_DESCUENTO_DIA_DE_SEMANA = 0.1;
    private static final double PROCENTAJE_DE_DESCUENTO_MAS_DE_TRES_DIAS = 0.15;
    private static final int CANTIDAD_DIAS_PARA_APLICAR_DESCUENTO = 3;
    private static final int CANTIDAD_DIAS_MAXIMO_RESERVAR = 7;
    private static final int DIAS_PARA_FECHAS_IGUALES = 1;

    private final RepositorioReserva repositorioReserva;

    public ServicioCrearReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    public Long ejecutar(Reserva reserva) {
        DtoFinca dtoFinca;
        double precioReservaTotal;
        validarExistenciaPrevia(reserva);
        dtoFinca = cargarFinca(reserva.getIdFinca());
        validarFechaFinalDespuesDeFechaInicial(reserva.getFechaInicioReserva(), reserva.getFechaFinReserva());
        precioReservaTotal = calcularPrecioReservaTotal(reserva.getFechaInicioReserva(), reserva.getFechaFinReserva(), dtoFinca.getPrecioPorDia());
        reserva.setValorTotalReserva(precioReservaTotal);
        return this.repositorioReserva.crear(reserva);
    }

    private void validarExistenciaPrevia(Reserva reserva) {
        boolean existe = this.repositorioReserva.existe(reserva.getIdFinca(), reserva.getFechaInicioReserva(), reserva.getFechaFinReserva());
        if (existe) {
            throw new ExcepcionDuplicidad(LA_FINCA_YA_ESTA_RESERVADA_EN_EL_SISTEMA);
        }
    }

    public int calcularDiasEntreFechas(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        if (fechaInicial.toLocalDate().equals(fechaFinal.toLocalDate())) {
            return DIAS_PARA_FECHAS_IGUALES;
        }
        return (int) DAYS.between(fechaInicial, fechaFinal.plusDays(1));
    }

    public DtoFinca cargarFinca(Long idFinca) {
        DtoFinca dtoFinca = this.repositorioReserva.cargarFincaPorId(idFinca).isEmpty() ? null : this.repositorioReserva.cargarFincaPorId(idFinca).get(0);
        if (dtoFinca == null) {
            throw new ExcepcionSinDatos(LA_FINCA_NO_EXISTE_EN_EL_SISTEMA);
        } else {
            return dtoFinca;
        }
    }

    public double calcularPrecioReservaTotal(LocalDateTime fechaInicial, LocalDateTime fechaFinal, double precioPorDia) {
        double precioTotal;
        int diasReserva = calcularDiasEntreFechas(fechaInicial, fechaFinal);
        validarMaximoDiasReserva(diasReserva);
        precioTotal = calcularPrecioReservaPorDias(fechaInicial, diasReserva, precioPorDia);
        if (diasReserva > CANTIDAD_DIAS_PARA_APLICAR_DESCUENTO) {
            precioTotal = precioTotal - (precioTotal * PROCENTAJE_DE_DESCUENTO_MAS_DE_TRES_DIAS);
        }
        return precioTotal;
    }

    public double calcularPrecioReservaPorDias(LocalDateTime fechaInicial, int dias, double precioPorDia) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date dateFechaInicial = Date.from(fechaInicial.atZone(defaultZoneId).toInstant());
        Calendar fechaInicioCalendar = Calendar.getInstance();
        fechaInicioCalendar.setTime(dateFechaInicial);
        int contadorDias = 0;
        double precioTotal = 0.0;
        while (contadorDias < dias) {
            if (fechaInicioCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && fechaInicioCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                precioTotal = precioTotal + (precioPorDia - (precioPorDia * PROCENTAJE_DE_DESCUENTO_DIA_DE_SEMANA));
            } else {
                precioTotal = precioTotal + precioPorDia;
            }
            contadorDias++;
            fechaInicioCalendar.add(Calendar.DATE, 1);
        }
        return precioTotal;
    }

    public void validarFechaFinalDespuesDeFechaInicial(LocalDateTime fecheInicio, LocalDateTime fechaFin) {
        if (fecheInicio.isAfter(fechaFin)) {
            throw new ExcepcionValorInvalido(FECHA_INICIO_MAYOR_QUE_FECHA_FIN);
        }
    }

    public void validarMaximoDiasReserva(int dias) {
        if (dias > CANTIDAD_DIAS_MAXIMO_RESERVAR) {
            throw new ExcepcionValorInvalido(MENSAJE_MAXIMO_DIAS);
        }
    }
}
