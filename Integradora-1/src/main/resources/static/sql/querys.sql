create database integradora;

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
('Hibrido'),
('Matutino y Vespertino');

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

insert into areas(nombre_area, descripcion)
value
('Administracion', 'Las carreras del área de administración se enfocan en la gestión y organización de recursos humanos, financieros y materiales dentro de una empresa u organización. '),
('Ingenieria', 'Ingeniería se centra en la aplicación de principios científicos y matemáticos para diseñar, desarrollar y mejorar estructuras, sistemas y procesos.');

insert into carreras (bilingue, costo, horario_especifico, informacion, nombre, roadmap, fk_area, fk_horario, fk_modalidad, fk_periodo_escolar, fk_universidad,
 como_desemp, donde_trabajar, porque_estudiar)
values
(false, 2667, '<p>Lunes a viernes de 7 A.M. a *2 P.M.</br> Lunes a viernes de 5 P.M. a 10 P.M.</p>', 'Desarrolladas a partir de la informática, microelectrónica y telecomunicaciones, las tecnologías 
de la información y comunicación tienen una intensa presencia en las sociedades desarrolladas, a nivel empresarial e individual a través 
de aplicaciones basadas en internet, comercio electrónico, dispositivos móviles, entre otras plataformas.',
'TECNOLOGÍAS DE LA INFORMACIÓN E INNOVACIÓN DIGITAL', 'https://roadmap.sh/r/embed?id=66625d3ce724e39e4df0bcc4',2,5,1,1,1,'<li>Gerente de sistemas y 
de telecomunicaciones.</li><li>Administrador de redes y sistemas.</li><li>Coordinador de comercio electrónico.</li><li>Coordinador de plataformas de educación 
a distancia.</li><li>Gerente del Departamento de Sistemas.</li><li>Gerente de seguridad en cómputo.</li><li>Jefe de proyectos de Comercio electrónico y Multimedia.
</li><li>Diseñador, programador y administrador de sitios web y de comercio electrónico.</li><li>Diseñador y desarrollador de proyectos multimedia.</li>','El 
Ingeniero en Tecnologías de la Información e Innovación Digital podrá desenvolverse en los sectores privado, público y social, en las diferentes ramas productivas 
que demanden servicios de Tecnologías de la Información como sitios Web de e-comerce, generación de elementos multimedia, implementación de estrategias de 
marketing a través de la Web, la integración de empresas orientadas al desarrollo, soporte y comercialización de bienes y servicios para la Web, así como empresas
 orientadas a las telecomunicaciones y soluciones en materia de conectividad y redes informáticas.', 'Una carrera en T.I. te permite diseñar, crear, innovar, 
 hacer un cambio y trabajar prácticamente cualquier área de interés, garantizando una creciente y amplia demanda laboral.'),
 
(false, 2667, '<p>Lunes a viernes de 7 A.M. a *2 P.M.</br> Lunes a viernes de 5 P.M. a 10 P.M.</p>', 'Las empresas y organizaciones son el motor de crecimiento y desarrollo 
de cualquier país. Hoy en día el entorno empresarial es competitivo, exigente y se encuentra en constante cambio por tanto resulta imprescindible contar 
con profesionales capaces de tomar decisiones que generen valor social y económico en una empresa u organización, a través del estudio de la gestión eficiente 
de los recursos humanos, financieros y materiales de éstas, para alcanzar la sostenibilidad y el cumplimiento de objetivos.',
'NEGOCIOS Y MERCADOTECNIA', 'https://roadmap.sh/r/embed?id=6663849ee724e39e4d12ce1d',1,5,1,1,1,null,null,null);
