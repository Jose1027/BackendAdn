package com.ceiba.finca;

import com.ceiba.finca.modelo.entidad.Finca;
import com.ceiba.finca.puerto.repositorio.RepositorioFinca;
import com.ceiba.finca.servicio.ServicioCrearFinca;
import com.ceiba.finca.testdatabuilder.FincaTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;

public class ServicioCreaFincaTest {


    @Test
    public void validarFincaExistenciaPreviaTest() {
        // arrange
        Finca finca = new FincaTestDataBuilder().build();
        RepositorioFinca repositorioFinca = Mockito.mock(RepositorioFinca.class);
        Mockito.when(repositorioFinca.existe(Mockito.anyString())).thenReturn(true);
        ServicioCrearFinca servicioCrearFinca = new ServicioCrearFinca(repositorioFinca);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearFinca.ejecutar(finca), ExcepcionDuplicidad.class, "La finca ya existe en el sistema");
    }
}
