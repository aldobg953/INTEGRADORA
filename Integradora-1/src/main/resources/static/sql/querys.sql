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

INSERT INTO areas (descripcion, descripcion_frances, descripcion_ingles, nombre_area, nombre_area_frances, nombre_area_ingles)
VALUES
("Educación, Artes y Humanidades: Disciplina enfocada en el desarrollo cultural, histórico, artístico y pedagógico, así como en la formación integral de individuos a través de la educación y las artes.",
 "Éducation, Arts et Humanités : Discipline axée sur le développement culturel, historique, artistique et pédagogique, ainsi que sur la formation globale des individus à travers l'éducation et les arts.",
 "Education, Arts and Humanities: Discipline focused on cultural, historical, artistic, and pedagogical development, as well as the comprehensive development of individuals through education and the arts.",
 "Educación, Artes y Humanidades", "Éducation, Arts et Humanités", "Education, Arts and Humanities"),

("Ciencias Sociales, Administración y Derecho: Estudio de la organización social, la gestión de empresas y la aplicación de leyes para regular las relaciones humanas en la sociedad.",
 "Sciences Sociales, Administration et Droit : Étude de l'organisation sociale, de la gestion des entreprises et de l'application des lois pour réguler les relations humaines dans la société.",
 "Social Sciences, Administration and Law: Study of social organization, business management, and the application of laws to regulate human relationships in society.",
 "Ciencias Sociales, Administración y Derecho", "Sciences Sociales, Administration et Droit", "Social Sciences, Administration and Law"),

("Ciencias Naturales y Ciencias Exactas: Estudio de fenómenos naturales y principios matemáticos que describen el mundo físico, abarcando disciplinas como biología, química, física y matemáticas.",
 "Sciences Naturelles et Sciences Exactes : Étude des phénomènes naturels et des principes mathématiques décrivant le monde physique, incluant la biologie, la chimie, la physique et les mathématiques.",
 "Natural Sciences and Exact Sciences: Study of natural phenomena and mathematical principles describing the physical world, including biology, chemistry, physics, and mathematics.",
 "Ciencias Naturales y Ciencias Exactas", "Sciences Naturelles et Sciences Exactes", "Natural Sciences and Exact Sciences"),

("Tecnologías de la Información y de la Comunicación: Uso y desarrollo de tecnologías relacionadas con la información y la comunicación, incluyendo informática, redes, software y telecomunicaciones.",
 "Technologies de l'Information et de la Communication : Utilisation et développement de technologies liées à l'information et à la communication, incluant l'informatique, les réseaux, les logiciels et les télécommunications.",
 "Information and Communication Technologies: Use and development of technologies related to information and communication, including computing, networks, software, and telecommunications.",
 "Tecnologías de la Información y de la Comunicación", "Technologies de l'Information et de la Communication", "Information and Communication Technologies"),

("Ingeniería, Manufactura y Construcción: Aplicación de principios científicos y matemáticos para diseñar y mejorar estructuras, sistemas y procesos, incluyendo ingeniería, manufactura y construcción.",
 "Ingénierie, Fabrication et Construction : Application de principes scientifiques et mathématiques pour concevoir et améliorer des structures, systèmes et processus, incluant ingénierie, fabrication et construction.",
 "Engineering, Manufacturing and Construction: Application of scientific and mathematical principles to design and improve structures, systems, and processes, including engineering, manufacturing, and construction.",
 "Ingeniería, Manufactura y Construcción", "Ingénierie, Fabrication et Construction", "Engineering, Manufacturing and Construction"),

("Agronomía y Veterinaria: Estudio de la agricultura y la medicina veterinaria, abarcando la gestión de cultivos, salud animal y técnicas de producción y cuidado.",
 "Agronomie et Médecine Vétérinaire : Étude de l'agriculture et de la médecine vétérinaire, incluant la gestion des cultures, la santé animale et les techniques de production et de soins.",
 "Agronomy and Veterinary Medicine: Study of agriculture and veterinary medicine, including crop management, animal health, and production and care techniques.",
 "Agronomía y Veterinaria", "Agronomie et Médecine Vétérinaire", "Agronomy and Veterinary Medicine"),

("Ciencias de la Salud: Estudio de disciplinas relacionadas con la salud y el bienestar, como medicina, enfermería y farmacología, enfocándose en el cuidado de la salud.",
 "Sciences de la Santé : Étude des disciplines liées à la santé et au bien-être, telles que la médecine, les soins infirmiers et la pharmacologie, avec un focus sur les soins de santé.",
 "Health Sciences: Study of disciplines related to health and well-being, such as medicine, nursing, and pharmacology, focusing on health care.",
 "Ciencias de la Salud", "Sciences de la Santé", "Health Sciences"),

("Servicios: Prestación de servicios a individuos y organizaciones, abarcando sectores como turismo, hospitalidad, servicios financieros y profesionales.",
 "Services : Fourniture de services aux individus et aux organisations, incluant des secteurs tels que le tourisme, l'hôtellerie, les services financiers et professionnels.",
 "Services: Provision of services to individuals and organizations, including sectors such as tourism, hospitality, financial services, and professional services.",
 "Servicios", "Services", "Services");







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


-- TEST VOCACIONAL
-- Crear las preguntas iniciales
INSERT INTO pregunta (text, text_ingles, text_frances) VALUES
('Pregunta 1 en español', 'Question 1 in English', 'Question 1 in French'),
('Pregunta 2 en español', 'Question 2 in English', 'Question 2 in French'),
('Pregunta 3 en español', 'Question 3 in English', 'Question 3 in French'),
('Pregunta 4 en español', 'Question 4 in English', 'Question 4 in French'),
('Pregunta 5 en español', 'Question 5 in English', 'Question 5 in French'),
('Pregunta 6 en español', 'Question 6 in English', 'Question 6 in French'),
('Pregunta 7 en español', 'Question 7 in English', 'Question 7 in French'),
('Pregunta 8 en español', 'Question 8 in English', 'Question 8 in French');


-- Insertar respuestas para cada pregunta
INSERT INTO respuesta (text, text_ingles, text_frances, id_pregunta, id_area) VALUES
-- Pregunta 1
('Respuesta 1 para Pregunta 1 en español', 'Answer 1 for Question 1 in English', 'Answer 1 for Question 1 in French', 1, 1),
('Respuesta 2 para Pregunta 1 en español', 'Answer 2 for Question 1 in English', 'Answer 2 for Question 1 in French', 1, 2),
('Respuesta 3 para Pregunta 1 en español', 'Answer 3 for Question 1 in English', 'Answer 3 for Question 1 in French', 1, 3),
('Respuesta 4 para Pregunta 1 en español', 'Answer 4 for Question 1 in English', 'Answer 4 for Question 1 in French', 1, 4),

-- Pregunta 2
('Respuesta 1 para Pregunta 2 en español', 'Answer 1 for Question 2 in English', 'Answer 1 for Question 2 in French', 2, 1),
('Respuesta 2 para Pregunta 2 en español', 'Answer 2 for Question 2 in English', 'Answer 2 for Question 2 in French', 2, 2),
('Respuesta 3 para Pregunta 2 en español', 'Answer 3 for Question 2 in English', 'Answer 3 for Question 2 in French', 2, 3),
('Respuesta 4 para Pregunta 2 en español', 'Answer 4 for Question 2 in English', 'Answer 4 for Question 2 in French', 2, 4),

-- Pregunta 3
('Respuesta 1 para Pregunta 3 en español', 'Answer 1 for Question 3 in English', 'Answer 1 for Question 3 in French', 3, 1),
('Respuesta 2 para Pregunta 3 en español', 'Answer 2 for Question 3 in English', 'Answer 2 for Question 3 in French', 3, 2),
('Respuesta 3 para Pregunta 3 en español', 'Answer 3 for Question 3 in English', 'Answer 3 for Question 3 in French', 3, 3),
('Respuesta 4 para Pregunta 3 en español', 'Answer 4 for Question 3 in English', 'Answer 4 for Question 3 in French', 3, 4),

-- Pregunta 4
('Respuesta 1 para Pregunta 4 en español', 'Answer 1 for Question 4 in English', 'Answer 1 for Question 4 in French', 4, 1),
('Respuesta 2 para Pregunta 4 en español', 'Answer 2 for Question 4 in English', 'Answer 2 for Question 4 in French', 4, 2),
('Respuesta 3 para Pregunta 4 en español', 'Answer 3 for Question 4 in English', 'Answer 3 for Question 4 in French', 4, 3),
('Respuesta 4 para Pregunta 4 en español', 'Answer 4 for Question 4 in English', 'Answer 4 for Question 4 in French', 4, 4),

-- Pregunta 5
('Respuesta 1 para Pregunta 5 en español', 'Answer 1 for Question 5 in English', 'Answer 1 for Question 5 in French', 5, 1),
('Respuesta 2 para Pregunta 5 en español', 'Answer 2 for Question 5 in English', 'Answer 2 for Question 5 in French', 5, 2),
('Respuesta 3 para Pregunta 5 en español', 'Answer 3 for Question 5 in English', 'Answer 3 for Question 5 in French', 5, 3),
('Respuesta 4 para Pregunta 5 en español', 'Answer 4 for Question 5 in English', 'Answer 4 for Question 5 in French', 5, 4),

-- Pregunta 6
('Respuesta 1 para Pregunta 6 en español', 'Answer 1 for Question 6 in English', 'Answer 1 for Question 6 in French', 6, 1),
('Respuesta 2 para Pregunta 6 en español', 'Answer 2 for Question 6 in English', 'Answer 2 for Question 6 in French', 6, 2),
('Respuesta 3 para Pregunta 6 en español', 'Answer 3 for Question 6 in English', 'Answer 3 for Question 6 in French', 6, 3),
('Respuesta 4 para Pregunta 6 en español', 'Answer 4 for Question 6 in English', 'Answer 4 for Question 6 en French', 6, 4),

-- Pregunta 7
('Respuesta 1 para Pregunta 7 en español', 'Answer 1 for Question 7 in English', 'Answer 1 for Question 7 in French', 7, 1),
('Respuesta 2 para Pregunta 7 en español', 'Answer 2 for Question 7 in English', 'Answer 2 for Question 7 en French', 7, 2),
('Respuesta 3 para Pregunta 7 en español', 'Answer 3 for Question 7 in English', 'Answer 3 for Question 7 en French', 7, 3),
('Respuesta 4 para Pregunta 7 en español', 'Answer 4 for Question 7 in English', 'Answer 4 for Question 7 en French', 7, 4),

-- Pregunta 8
('Respuesta 1 para Pregunta 8 en español', 'Answer 1 for Question 8 in English', 'Answer 1 for Question 8 en French', 8, 1),
('Respuesta 2 para Pregunta 8 en español', 'Answer 2 for Question 8 in English', 'Answer 2 for Question 8 en French', 8, 2),
('Respuesta 3 para Pregunta 8 en español', 'Answer 3 for Question 8 in English', 'Answer 3 for Question 8 en French', 8, 3),
('Respuesta 4 para Pregunta 8 en español', 'Answer 4 for Question 8 en English', 'Answer 4 for Question 8 en French', 8, 4);