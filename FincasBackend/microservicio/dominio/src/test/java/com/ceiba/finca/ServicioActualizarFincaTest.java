package com.ceiba.finca;

import com.ceiba.finca.modelo.entidad.Finca;
import com.ceiba.finca.puerto.repositorio.RepositorioFinca;
import com.ceiba.finca.servicio.ServicioActualizarFinca;
import com.ceiba.finca.testdatabuilder.FincaTestDataBuilder;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;

public class ServicioActualizarFincaTest {

    @Test
    public void validarFincaExistenciaPreviaTest() {
        // arrange
        Finca finca = new FincaTestDataBuilder().conId(1L).build();
        RepositorioFinca repositorioFinca = Mockito.mock(RepositorioFinca.class);
        Mockito.when(repositorioFinca.existeExcluyendoId(Mockito.anyLong(), Mockito.anyString())).thenReturn(true);
        com.ceiba.finca.servicio.ServicioActualizarFinca servicioActualizarFinca = new com.ceiba.finca.servicio.ServicioActualizarFinca(repositorioFinca);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarFinca.ejecutar(finca), ExcepcionDuplicidad.class, "La finca ya existe en el sistema");
    }
}
