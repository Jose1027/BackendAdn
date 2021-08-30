update finca
set nombre = :nombre,
	direccion = :direccion,
	fecha_creacion = :fechaCreacion,
	precio = :precio,
	cantidad_habitaciones = :cantidadHabitaciones
where id = :id