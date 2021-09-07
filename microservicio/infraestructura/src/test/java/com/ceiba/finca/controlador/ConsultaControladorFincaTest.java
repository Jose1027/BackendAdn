package com.ceiba.finca.controlador;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ceiba.ApplicationMock;
import com.ceiba.reserva.controlador.ConsultaControladorReserva;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ConsultaControladorReserva.class)
public class ConsultaControladorFincaTest {


    @Autowired
    private MockMvc mocMvc;

    @Test
    public void listar() throws Exception {

        // arrange

        // act - assert


        mocMvc.perform(get("/fincas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre", is("el refugio")));
    }

    @Test
    public void listarConDisponibilidad() throws Exception {
        // arrange
        String fechaInicio = "2021-09-11";
        String fechaFin = "2020-09-13";
        // act - assert
        mocMvc.perform(get("/fincas/{fechaInicio}/{fechaFin}/", fechaInicio, fechaFin)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is("el refugio")));
    }
}
