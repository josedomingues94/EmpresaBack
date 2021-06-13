

INSERT INTO departamentos (id, nombre) VALUES (1, 'Compras');
INSERT INTO departamentos (id, nombre) VALUES (2, 'Ventas');
INSERT INTO departamentos (id, nombre) VALUES (3, 'Administracion');
INSERT INTO departamentos (id, nombre) VALUES (4, 'Impuestos');
INSERT INTO departamentos (id, nombre) VALUES (5, 'Recursos humanos');
INSERT INTO departamentos (id, nombre) VALUES (6, 'Marketing');
INSERT INTO departamentos (id, nombre) VALUES (7, 'Publicidad y RRPP');
INSERT INTO departamentos (id, nombre) VALUES (8, 'Direccion');

INSERT INTO empleados (dni, nombre, apellido1, apellido2, create_at, email, departamento) VALUES('51133615S', 'Jose', 'Domingues', 'Gordillo', '1994-05-28', 'jose@gmail.com',6);
INSERT INTO empleados (dni, nombre, apellido1, apellido2, create_at, email, departamento) VALUES('41594759H', 'Elia', 'Dotor', 'Puente', '1991-03-21', 'elia@gmail.com',2);
INSERT INTO empleados (dni, nombre, apellido1, apellido2, create_at, email, departamento) VALUES('86140537V', 'Fatima', 'Gordillo', 'Dominguez', '1995-03-27', 'fatima@gmail.com',6);
INSERT INTO empleados (dni, nombre, apellido1, apellido2, create_at, email, departamento) VALUES('53652910M', 'Javier', 'Serrato', 'Jimenez', '1991-08-14', 'javier@gmail.com',2);
INSERT INTO empleados (dni, nombre, apellido1, apellido2, create_at, email, departamento) VALUES('57971451C', 'Giulia', 'Garcia', 'Gil', '1990-04-02', 'giulia@gmail.com',3);
INSERT INTO empleados (dni, nombre, apellido1, apellido2, create_at, email, departamento) VALUES('59146104V', 'Sara', 'Sanchez', 'Gonzalez', '1996-01-30', 'sara@gmail.com',6);
INSERT INTO empleados (dni, nombre, apellido1, apellido2, create_at, email, departamento) VALUES('52579634S', 'Paula', 'Montero', 'Sanchez', '1992-12-07', 'paula@gmail.com',4);
INSERT INTO empleados (dni, nombre, apellido1, apellido2, create_at, email, departamento) VALUES('50457492Y', 'Francisco', 'Roales', 'Fernandez', '1990-10-09', 'francisco@gmail.com',4);
INSERT INTO empleados (dni, nombre, apellido1, apellido2, create_at, email, departamento) VALUES('58218387M', 'Luis', 'Izquierdo', 'Lopez', '1974-02-20', 'luis@gmail.com',5);
INSERT INTO empleados (dni, nombre, apellido1, apellido2, create_at, email, departamento) VALUES('56961108K', 'Hector', 'Rodriguez', 'Ramos', '1994-09-20', 'hector@gmail.com',6);
INSERT INTO empleados (dni, nombre, apellido1, apellido2, create_at, email, departamento) VALUES('55633232G', 'Sergio', 'Torres', 'Martinez', '1995-11-19', 'sergio@gmail.com',5);
INSERT INTO empleados (dni, nombre, apellido1, apellido2, create_at, email, departamento) VALUES('55809842C', 'Oscar', 'Alonso', 'Monje', '1993-02-04', 'oscar@gmail.com',5);

/* Creamos algunos usuarios con sus roles */
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('javier','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq',1, 'Javier', 'Serrato','javier@gmail.com');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('admin','$2a$10$RmdEsvEfhI7Rcm9f/uZXPebZVCcPC7ZXZwV51efAvMAp1rIaRAfPK',1, 'Jose', 'Domingues','jose@admin.com');

INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 1);

