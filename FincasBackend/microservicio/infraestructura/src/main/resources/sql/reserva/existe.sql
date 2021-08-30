select count(1)
from reserva
where id_finca = :idFinca and ((:fechaInicioReserva between fecha_inicio_reserva and fecha_fin_reserva) or
(:fechaFinReserva between fecha_inicio_reserva and fecha_fin_reserva));