package com.ceiba.reserva;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.finca.modelo.dto.DtoFinca;
import com.ceiba.finca.puerto.repositorio.RepositorioFinca;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.ServicioCrearReserva;
import com.ceiba.reserva.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
        int diasEntreFechas = 0;
        diasEntreFechas = servicioCrearReserva.calcularDiasEntreFechas(reserva.getFechaInicioReserva(), reserva.getFechaFinReserva());
        assertEquals(3, diasEntreFechas);
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
        int diasEntreFechas = servicioCrearReserva.calcularDiasEntreFechas(reserva.getFechaInicioReserva(), reserva.getFechaFinReserva());
        double precioCalculado = servicioCrearReserva.calcularPrecioReservaPorDias(reserva.getFechaInicioReserva(), diasEntreFechas, dtoFinca.getPrecioPorDia());
        assertEquals(1080000.0, precioCalculado,0);
    }

    @Test
    public void calcularPrecioReservaTotalTest() {
        Reserva reserva = new ReservaTestDataBuilder().build();
        DtoFinca dtoFinca = new ReservaTestDataBuilder().buildIDtoFinca();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        double precioCalculado = servicioCrearReserva.calcularPrecioReservaTotal(reserva.getFechaInicioReserva(), reserva.getFechaFinReserva(), dtoFinca.getPrecioPorDia());
        assertEquals(918000.0, precioCalculado,0);
    }


    @Test
    public void validarReservaSinExistenciaPreviaTest() {
        Reserva reserva = new ReservaTestDataBuilder().build();
        DtoFinca dtoFinca = new ReservaTestDataBuilder().buildIDtoFinca();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        List<DtoFinca> fincas= new ArrayList<>();
        fincas.add(dtoFinca);
        Mockito.when(repositorioReserva.cargarFincaPorId(Mockito.anyLong())).thenReturn(fincas);

        Long idResultado = servicioCrearReserva.ejecutar(reserva);
        assertEquals(0L,idResultado,0);
    }

}
