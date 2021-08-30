package com.ceiba.reserva.puerto.repositorio;

import com.ceiba.finca.modelo.dto.DtoFinca;
import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDateTime;
import java.util.List;

public interface RepositorioReserva {

    Long crear(Reserva reserva);

    boolean existe(Long idFinca, LocalDateTime fechaInicioReserva, LocalDateTime fechaFinReserva);

    List<DtoFinca> cargarFincaPorId(Long id);

}
