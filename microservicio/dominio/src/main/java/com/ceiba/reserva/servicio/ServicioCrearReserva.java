package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.finca.modelo.dto.DtoFinca;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

import java.time.LocalDateTime;
import java.util.Calendar;

import static java.time.temporal.ChronoUnit.DAYS;


public class ServicioCrearReserva {

    private static final String LA_FINCA_YA_ESTA_RESERVADA_EN_EL_SISTEMA = "La finca ya se encuentra reservada para las fechas seleccionadas";
    private static final String LA_FINCA_NO_EXISTE_EN_EL_SISTEMA = "La finca no existe en el sistema";
    private static final double PROCENTAJE_DE_DESCUENTO_DIA_DE_SEMANA = 0.1;
    private static final double PROCENTAJE_DE_DESCUENTO_MAS_DE_TRES_DIAS = 0.15;
    private static final int CANTIDAD_DIAS_PARA_APLICAR_DESCUENTO = 3;

    private final RepositorioReserva repositorioReserva;

    public ServicioCrearReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    public Long ejecutar(Reserva reserva) {
        DtoFinca dtoFinca;
        double precioReservaTotal = 0.0;
        validarExistenciaPrevia(reserva);
        dtoFinca = cargarFinca(reserva.getIdFinca());
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
        return (int) DAYS.between(fechaInicial,fechaFinal);
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
        double precioTotal = 0.0;
        int diasReserva = 0;
        diasReserva = calcularDiasEntreFechas(fechaInicial, fechaFinal);
        precioTotal = calcularPrecioReservaPorDias(fechaInicial, diasReserva, precioPorDia);
        if (diasReserva >= CANTIDAD_DIAS_PARA_APLICAR_DESCUENTO) {
            precioTotal = precioTotal - (precioTotal * PROCENTAJE_DE_DESCUENTO_MAS_DE_TRES_DIAS);
        }
        return precioTotal;
    }

    public double calcularPrecioReservaPorDias(LocalDateTime fechaInicial, int dias, double precioPorDia) {
        Calendar fechaInicioCalendar = Calendar.getInstance();
        fechaInicioCalendar.clear();
        fechaInicioCalendar.set(fechaInicial.getYear(), fechaInicial.getMonthValue(), fechaInicial.getDayOfMonth(), 0, 0, 0);
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
}
