# Usa una imagen base de Java
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo pom.xml al contenedor (esto puede ser opcional si solo necesitas el JAR)
COPY integradora-1/pom.xml /app/pom.xml

# Copia el directorio target al contenedor
COPY integradora-1/target /app/target

# Expone el puerto en el que la aplicación se ejecuta
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "target/com.registro.usuarios-1.0.jar"]