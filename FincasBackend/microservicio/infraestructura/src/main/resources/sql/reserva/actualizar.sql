update reserva
set id_usuario = :idUsuario,
	id_finca = :idFinca,
	fecha_inicio_reserva = :fechaInicioReserva,
	fecha_fin_reserva = :fechaFinReserva,
	valor_total_reserva = :valorTotalReserva
where id = :id