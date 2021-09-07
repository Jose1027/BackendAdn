package com.ceiba.finca;

import com.ceiba.finca.modelo.entidad.Finca;
import com.ceiba.finca.puerto.repositorio.RepositorioFinca;
import com.ceiba.finca.servicio.ServicioCrearFinca;
import com.ceiba.finca.testdatabuilder.FincaTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;


import static org.junit.Assert.assertEquals;

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

    @Test
    public void crarFincaTest() {
        Finca finca = new FincaTestDataBuilder().build();
        RepositorioFinca repositorioFinca = Mockito.mock(RepositorioFinca.class);
        ServicioCrearFinca servicioCrearFinca = new ServicioCrearFinca(repositorioFinca);
        Mockito.when(repositorioFinca.existe(Mockito.anyString())).thenReturn(false);

        Long idResultado = servicioCrearFinca.ejecutar(finca);
        assertEquals(0L,idResultado,0);
    }
}
