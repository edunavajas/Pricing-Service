# Usar una imagen base con Java 17
FROM openjdk:17-jdk-slim

# Argumento para el JAR de la aplicación
ARG JAR_FILE=target/*.jar

# Copiar el JAR del proyecto al contenedor
COPY ${JAR_FILE} app.jar

# Exponer el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]
