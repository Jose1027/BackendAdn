package com.ceiba.reserva.puerto.repositorio;

import com.ceiba.finca.modelo.dto.DtoFinca;
import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDate;
import java.util.List;

public interface RepositorioReserva {

    Long crear(Reserva reserva);

    boolean existe(Long idFinca, LocalDate fechaInicioReserva, LocalDate fechaFinReserva);

    List<DtoFinca> cargarFincaPorId(Long id);

}
