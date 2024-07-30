create database integradora;

-- Insertar roles de usuarios
insert into roles (id_rol,nombre) 
values (1,'ROLE_USER'),
(2,'ROLE_ADMIN'),
(3,'ROLE_SUPER');

INSERT INTO horarios (id_horario, nombre_horario, nombre_horario_en, nombre_horario_fr) VALUES
(1, 'Matutino', 'morning', 'matin'),
(2, 'Vespertino', 'evening', 'soirée'),
(3, 'Nocturno', 'night', 'nuit'),
(4, 'Híbrido', 'hybrid', 'hybride'),
(5, 'Mañana y Tarde', 'morning and evening', 'matin et soir');

INSERT INTO modalidades (id_modalidad, nombre_modalidad, nombre_modalidad_en, nombre_modalidad_fr) VALUES
(1, 'Presencial', 'In-person', 'En personne'),
(2, 'Virtual', 'Virtual', 'Virtuel'),
(3, 'Híbrido', 'Hybrid', 'Hybride'),
(4, 'Virtual y Presencial', 'Virtual and in-person', 'Virtuel et en personne');

INSERT INTO periodo_escolar (id_periodo_escolar, nombre_periodo, nombre_periodo_en, nombre_periodo_fr) VALUES
(1, 'Cuatrimestre', 'four-month period', 'période de quatre mois'),
(2, 'Semestre', 'semester', 'semestre'),
(3, 'Trimestre', 'trimester', 'quart'),
(4, 'Bimestre', 'bimester', 'bimestre'),
(5, 'Anual', 'yearly', 'annuel');

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