package com.ceiba.configuracion;

import com.ceiba.finca.puerto.repositorio.RepositorioFinca;
import com.ceiba.finca.servicio.ServicioCrearFinca;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.finca.servicio.ServicioActualizarFinca;
import com.ceiba.finca.servicio.ServicioEliminarFinca;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearFinca servicioCrearFinca(RepositorioFinca repositorioFinca) {
        return new ServicioCrearFinca(repositorioFinca);
    }

    @Bean
    public ServicioEliminarFinca servicioEliminarFinca(RepositorioFinca repositorioFinca) {
        return new ServicioEliminarFinca(repositorioFinca);
    }

    @Bean
    public ServicioActualizarFinca servicioActualizarFinca(RepositorioFinca repositorioFinca) {
        return new ServicioActualizarFinca(repositorioFinca);
    }


}
