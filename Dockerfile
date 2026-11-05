# Use an official Maven image as a parent image
FROM maven:3.9-eclipse-temurin-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file
COPY Integradora-1/pom.xml .

# Copy the project source
COPY Integradora-1/src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use OpenJDK for the runtime
FROM eclipse-temurin:17-jre-jammy

# Set the working directory in the container
WORKDIR /app

# Copy the built artifact from the build stage
COPY --from=build /app/target/com.registro.usuarios-1.0.jar ./app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "app.jar"]