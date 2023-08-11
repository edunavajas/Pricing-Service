# ---- Fase de construcción ----
# Usa una imagen base de Maven para compilar la aplicación
FROM maven:3.8.1-jdk-11-slim AS build

# Copia los archivos `pom.xml` y `src` al contenedor
COPY pom.xml /build/
COPY src /build/src/

# Cambia el directorio de trabajo
WORKDIR /build/

# Compila la aplicación
RUN mvn clean package

# ---- Fase de ejecución ----
# Usa una imagen base de Java para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Copia el JAR generado en la fase de construcción al nuevo contenedor
COPY --from=build /build/target/*.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
