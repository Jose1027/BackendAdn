CREATE TABLE reserva (
  id int(11) not null auto_increment,
  id_usuario varchar(45) not null,
  id_finca int(11) not null,
  fecha_inicio_reserva datetime null,
  fecha_fin_reserva datetime null,
  valor_total_reserva double null,
  PRIMARY KEY (id)
);

CREATE TABLE finca (
  id int(11) not null auto_increment,
  nombre varchar(45) not null,
  direccion varchar(45) not null,
  fecha_creacion datetime null,
  precio double null,
  cantidad_habitaciones int not null,
  PRIMARY KEY (id)
);