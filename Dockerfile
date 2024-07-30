FROM amazoncorretto:17-alpine-jdk

COPY target/com.registro.usuarios-1.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]