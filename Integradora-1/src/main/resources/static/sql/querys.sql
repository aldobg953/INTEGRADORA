create database integradora;

-- edicion de tabla y agregar una universidad (ya no son necesarios)
alter table universidades modify column direccion_google varchar(600); -- para aumentar la cantidad de caracteres
alter table universidades modify column informacion varchar(1000); -- para aumentar la cantidad de informacion

-- Insertar roles de usuarios
insert into roles (id_rol,nombre) 
values (1,'ROLE_USER'),
(2,'ROLE_ADMIN'),
(3,'ROLE_SUPER');

-- Insertar modalidades
insert into modalidades (nombre_modalidad)
values 
('Presencial'),
('Virtual'),
('Hibrido'),
('Virtual o Presencial');

-- Insertar los periodos que existen

insert into periodo_escolar (nombre_periodo)
values
('Cuatrimestre'),
('Semestre'),
('Trimestre'),
('Bimestre'),
('Anual'),
('Quimestre'),
('Modulo');


-- insertar los horarios de las carreras
--   matutino, vespertino, nocturno o hibrido.
insert into horarios (nombre_horario)
values
('Matutino'),
('Vespertino'),
('Nocturno'),
('Hibrido');

-- inserta universidades
insert into universidades (correo, direccion, direccion_google, informacion, nombre_abreviado, nombre_completo, pagina_web, telefono, folder,caracteristicas,tipo_institucion) 
values
('contacto@utch.edu.mx','Av. Montes Americanos #9501, Col. Sector 35, C.P. 31216, Chihuahua, Chih., México.', 
'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d7003.141572755976!2d-106.15690605963428!3d28.642623378977508!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x86ea42c17167dc41%3A0x51865060df9440fb!2sUTCH!5e0!3m2!1ses-419!2smx!4v1717543396334!5m2!1ses-419!2smx',
'<ul><li>Educación de calidad</li><li>investigación innovadora</li><li>comunidad diversa y oportunidades de crecimiento personal y profesional.</li></ul>',
'UTCH','Universidad Tecnologica de Chihuahua','https://www.utch.edu.mx/','(614) 4 32 20 00', 'utch','caracteristicas en base de datos','publica'),
('contacto@utch.edu.mx','Km 3.5 Carretera Chihuahua a Aldama Colinas de León, 31313 Chihuahua, Chih.',
'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3500.9353098317106!2d-106.04276022449811!3d28.661655575648812!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x86ea45257990aadb%3A0x8dc405326b2aa7e!2sUTCH%20BIS!5e0!3m2!1ses-419!2smx!4v1717612864534!5m2!1ses-419!2smx',
'informacion de utch bis en base de datos', 'UTCH BIS', 'Universidad Tecnologica de Chihuahua BIS','https://bis.utch.edu.mx/','(614) 4 32 20 00','utchbis','caracteristicas en base de datos','publica'),
(' informes@utchsur.edu.mx','Km. 3 Carretera Chihuahua a Aldama S/N',
'https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d14003.792430824195!2d-106.0357084!3d28.6612724!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x86ea44ea3eb085a3%3A0xc6c94b37ec294343!2sUniversidad%20Tecnol%C3%B3gica%20de%20Chihuahua%20Sur!5e0!3m2!1ses-419!2smx!4v1717613362126!5m2!1ses-419!2smx',
'informacion de utch sur en base de datos', 'UTCH SUR', 'Universidad Tecnologica de Chihuahua Sur','https://utchsur.edu.mx/','614-4-20-34-10','utchsur','caracteristicas en base de datos','publica')
;