package com.ceiba.configuracion;

import com.ceiba.finca.puerto.repositorio.RepositorioFinca;
import com.ceiba.finca.servicio.ServicioCrearFinca;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.ServicioCrearReserva;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearFinca servicioCrearFinca(RepositorioFinca repositorioFinca) {
        return new ServicioCrearFinca(repositorioFinca);
    }

    @Bean
    public ServicioCrearReserva servicioCrearReserva(RepositorioReserva repositorioReserva) {
        return new ServicioCrearReserva(repositorioReserva);
    }

}
