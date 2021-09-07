package com.ceiba.reserva;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.finca.modelo.dto.DtoFinca;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.ServicioCrearReserva;
import com.ceiba.reserva.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ServicioCrearReservaTest {
    @Test
    public void validarReservaExistenciaPreviaTest() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioReserva.existe(reserva.getIdFinca(), reserva.getFechaInicioReserva(), reserva.getFechaFinReserva())).thenReturn(true);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ExcepcionDuplicidad.class, "La finca ya se encuentra reservada para las fechas seleccionadas");
    }

    @Test
    public void calcularDiasEntreFechasTest() {
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        int diasEntreFechas = servicioCrearReserva.calcularDiasEntreFechas(reserva.getFechaInicioReserva(), reserva.getFechaFinReserva());
        assertEquals(3, diasEntreFechas);
    }

    @Test
    public void calcularDiasEntreFechasMismoDiaTest() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder();
        reservaTestDataBuilder.conFechaFin(LocalDate.of(2021,9,1));
        Reserva reserva = reservaTestDataBuilder.build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        int diasEntreFechas = servicioCrearReserva.calcularDiasEntreFechas(reserva.getFechaInicioReserva(), reserva.getFechaFinReserva());
        assertEquals(1, diasEntreFechas);
    }

    @Test
    public void validarFincaNoExiste() {
        Reserva reserva = new ReservaTestDataBuilder().buildIdFincaNoExiste();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        BasePrueba.assertThrows(() -> servicioCrearReserva.cargarFinca(reserva.getIdFinca()), ExcepcionSinDatos.class, "La finca no existe en el sistema");
    }

    @Test
    public void calcularPrecioReservaPorDiasTest() {
        Reserva reserva = new ReservaTestDataBuilder().build();
        DtoFinca dtoFinca = new ReservaTestDataBuilder().buildIDtoFinca();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        int tresDias = 3;
        double precioCalculado = servicioCrearReserva.calcularPrecioReservaPorDias(reserva.getFechaInicioReserva(), tresDias, dtoFinca.getPrecioPorDia());
        assertEquals(1080000, precioCalculado, 0);
    }

    @Test
    public void calcularPrecioReservaTotalEnSemanaTest() {
        Reserva reserva = new ReservaTestDataBuilder().build();
        DtoFinca dtoFinca = new ReservaTestDataBuilder().buildIDtoFinca();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        double precioCalculado = servicioCrearReserva.calcularPrecioReservaTotal(reserva.getFechaInicioReserva(), reserva.getFechaFinReserva(), dtoFinca.getPrecioPorDia());
        assertEquals(1080000, precioCalculado, 0);
    }

    @Test
    public void calcularPrecioReservaFinDeSemana() {
        Reserva reserva = new ReservaTestDataBuilder().buildFinDeSemana();
        DtoFinca dtoFinca = new ReservaTestDataBuilder().buildIDtoFinca();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        double precioCalculado = servicioCrearReserva.calcularPrecioReservaTotal(reserva.getFechaInicioReserva(), reserva.getFechaFinReserva(), dtoFinca.getPrecioPorDia());
        assertEquals(800000, precioCalculado, 0);
    }

    @Test
    public void calcularPrecioReservaTresDiasEntreSemanaYFinDeSemana() {
        Reserva reserva = new ReservaTestDataBuilder().buildSemanaYFinDeSemana();
        DtoFinca dtoFinca = new ReservaTestDataBuilder().buildIDtoFinca();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        double precioCalculado = servicioCrearReserva.calcularPrecioReservaTotal(reserva.getFechaInicioReserva(), reserva.getFechaFinReserva(), dtoFinca.getPrecioPorDia());
        assertEquals(1160000, precioCalculado, 0);
    }

    @Test
    public void calcularPrecioReservaTodaLaSemanaYFinDeSemana() {
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder();
        reservaTestDataBuilder.conFechaInicio(LocalDate.of(2021,8,30));
        reservaTestDataBuilder.conFechaFin(LocalDate.of(2021,9,5));
        Reserva reserva = reservaTestDataBuilder.build();
        DtoFinca dtoFinca = new ReservaTestDataBuilder().buildIDtoFinca();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        double precioCalculado = servicioCrearReserva.calcularPrecioReservaTotal(reserva.getFechaInicioReserva(), reserva.getFechaFinReserva(), dtoFinca.getPrecioPorDia());
        assertEquals(2210000, precioCalculado, 0);
    }


    @Test
    public void validarReservaSinExistenciaPreviaTest() {
        Reserva reserva = new ReservaTestDataBuilder().build();
        DtoFinca dtoFinca = new ReservaTestDataBuilder().buildIDtoFinca();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        List<DtoFinca> fincas = new ArrayList<>();
        fincas.add(dtoFinca);
        Mockito.when(repositorioReserva.cargarFincaPorId(Mockito.anyLong())).thenReturn(fincas);

        Long idResultado = servicioCrearReserva.ejecutar(reserva);
        assertEquals(0L, idResultado, 0);
    }

    @Test
    public void validarFechaInicioMenorQueFechaFin() {
        Reserva reserva = new ReservaTestDataBuilder().buildFechaInicioDespuesDeFechaFin();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        BasePrueba.assertThrows(() -> servicioCrearReserva.validarFechaFinalDespuesDeFechaInicial(reserva.getFechaInicioReserva(), reserva.getFechaFinReserva()), ExcepcionValorInvalido.class, "La fecha de inicio de la reserva no puede ser mayor que la fecha final");
    }

    @Test
    public void validarQueDiasDeReservaSuperaLoEstablecido() {
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        int quinceDias = 15;
        BasePrueba.assertThrows(() -> servicioCrearReserva.validarMaximoDiasReserva(quinceDias), ExcepcionValorInvalido.class, "Solo se puede reservar maximo 7 dias");
    }
}
