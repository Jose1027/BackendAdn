select f.id, f.nombre, f.direccion, f.fecha_creacion, f.precio, f.cantidad_habitaciones
from finca f
where not exists (select 1 from reserva r where f.id = r.id_finca
and (r.fecha_inicio_reserva between :fechaInicio and :fechaFin)
and (r.fecha_fin_reserva between :fechaInicio and :fechaFin))