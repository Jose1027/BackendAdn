package com.ceiba.finca.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.finca.comando.ComandoFinca;
import com.ceiba.finca.servicio.testdatabuilder.ComandoFincaTestDataBuilder;
import com.ceiba.reserva.controlador.ComandoControladorReserva;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ComandoControladorReserva.class)
public class ComandoControladorFincaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void crear() throws Exception {
        // arrange
        ComandoFinca finca = new ComandoFincaTestDataBuilder().build();

        // act - assert
        mocMvc.perform(post("/fincas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(finca)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 2}"));
    }
}
