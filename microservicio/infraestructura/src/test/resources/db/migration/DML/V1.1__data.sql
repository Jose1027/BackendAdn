insert into finca (nombre, direccion, fecha_creacion, precio, cantidad_habitaciones)
values ('el refugio','morelia','2020-01-02',400000.0,5);
insert into reserva (id_usuario,id_finca,fecha_inicio_reserva,fecha_fin_reserva,valor_total_reserva)
values ('12345',1L,now(),now(),800000.0);
