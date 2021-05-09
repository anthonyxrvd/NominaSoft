insert into empleados(id,direccion,dni,estado_civil,fecha_nacimiento,nombre,telefono,grado_academico)values(1,'villa del mar',72314566,'soltero','2000-07-07','Ralff',980989181,'Universitario');

insert into afp(id, descuento, nombre, tipo_afp) VALUES (2,20,'Profuturo',1);

insert into otros_conceptos(id, monto_adelantos, monto_horas_ausente, monto_horas_extra, monto_otros_descuentos, monto_otros_ingresos, monto_reintegro)VALUES(1,120,10,30,200,300,0);

insert into contratos(id, activo, asignacion_familiar, cargo, fecha_fin, fecha_inicio, horas_contratadas, valor_hora, afp_id, otrosconceptos_id, empleado_id)VALUES(1,1,1,'tester','2021-12-12','2021-01-01',320,20,2,1,1);

insert into periodo_de_pago(id, estado, fecha_fin, fecha_inicio) VALUES (1,1,'2022-02-02','2021-01-01');

insert into pagos(id, fecha_actual, contrato_id, periododepago_id) VALUES (1,'2021-04-04',1,1)