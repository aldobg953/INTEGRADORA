# INTEGRADORA
Nuestro proyecto cuenta con bastantes endpoints, todos estan almacenados en main/java/com/registro/usuarios/controlador
Para poner algunos ejemplos:
Contamos con el endpoint /universidades que se encuentra en la carpeta controlador en el archivo UniversidadControlador.java
el cual devuelve el html universidades.html y ademas manda una lista del objeto universidad, con los datos de todas las universidades
que manejamos
Lo mismo con el endpoint /carreras/areas la cual se encuentra en carrera controlador y que envia una lista de objetos de 
areas, las cuales son las areas de estudio en las que se dividen nuestras carreras, ademas de mandar el html de areas.html

Para la conexion a la base de datos:
-colocamos la informacion de la conexion en application.properties, como el enlace, el usuario y contraseña.
y utilizamos los repositorios, que se encuentran en la carpeta de repositorio, las cuales levan la anotacion @Repository de 
spring boot para identificar que sera nuestras clases que se conectaran a la base de datos. extienden de JpaRepository
indicando cual es el tipo de dato que devolveran y el tipo de dato que es la llave primaria.
contamos con varios repositorios, como por ejemplo: Universidad, carrera, especialidad, las cuales se utilizan para crear,
actualizar, eliminar y consultar.