
# Pricing service API

Una aplicación Spring Boot para administrar y recuperar los precios de los productos según la fecha y el ID del producto.

## Prerrequisitos

- Java 17
- Maven
- Docker (Opcional)
- IDE (como IntelliJ IDEA, para ejecución desde el IDE)

## Ejecución en local

1. **Compilación de la aplicación**:

   Ejecuta el siguiente comando para compilar la aplicación:
   ```
   mvn clean compile
   ```

2. **Ejecución desde un IDE**:

   Importa el proyecto en un IDE como IntelliJ IDEA y ejecuta la clase principal de tu aplicación Spring Boot.

## Ejecución en Docker

1. **Construir la aplicación**:

   Antes de construir la imagen Docker, asegúrate de empaquetar tu aplicación con Maven:
   ```
   mvn clean package
   ```
   Esto generará el archivo JAR de tu aplicación en el directorio `target`.

2. **Construir la imagen Docker**:

   Desde el directorio raíz de tu proyecto (donde se encuentra el Dockerfile), ejecuta el siguiente comando:
   ```
   docker build -t pricingservice:0.0.1-SNAPSHOT .
   ```

3. **Ejecutar el contenedor**:

   Una vez construida la imagen, puedes ejecutarla con:
   ```
   docker run -p 8080:8080 pricingservice:0.0.1-SNAPSHOT
   ```

## Ejecución de tests

Para ejecutar los tests, utiliza el siguiente comando:
```
mvn test
```

## Creación de la Base de Datos

La creación de la base de datos e inserción de datos mockup se manejan automáticamente mediante Liquibase en una base de datos H2.

## Documentación de la API

La documentación de la API se encuentra en el archivo `resources/openapi/swagger.yml`. Es recomendable importar este archivo en Postman para facilitar las pruebas de los endpoints.

## Usuarios por defecto

Se han creado dos usuarios por defecto:
- Usuario: `user`
  Contraseña: `user`

- Usuario: `admin`
  Contraseña: `admin`

## Arquitectura de la aplicación

Para realizar la construccion de esta aplicación he optado por Arquitectura Hexagonal.

## Autor

Este proyecto ha sido creado y desarrollado por Eduardo Navajas Cortés.
