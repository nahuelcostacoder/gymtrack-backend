# GymTrack Backend

Backend de GymTrack, una aplicación orientada al seguimiento de entrenamientos y la interacción social entre usuarios.

Desarrollado con Spring Boot, Java y MySQL.

## Funcionalidades principales

- Registro e inicio de sesión con JWT
- Gestión de usuarios, perfiles, roles y permisos
- Creación y gestión de rutinas
- Registro de entrenamientos
- Historial de ejercicios
- Sistema de amistades
- Publicaciones asociadas a entrenamientos
- Me gusta y comentarios
- Feed paginado
- Autorización por ownership
- Gestión administrativa mediante permisos
- Almacenamiento externo de imágenes

## Tecnologías

- Java 21
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- MySQL
- Docker
- MapStruct
- Lombok
- Maven

## Arquitectura

El proyecto sigue una arquitectura por capas:

Controller
↓
Service
↓
Repository
↓
Database

También utiliza DTOs y mappers para evitar exponer directamente las entidades.

## Seguridad

La autenticación se realiza mediante JWT.

El sistema utiliza roles y permisos:

- USUARIO
- ADMIN

Los permisos administrativos se validan mediante `@PreAuthorize`.

Para recursos propios, como rutinas, publicaciones, comentarios y entrenamientos, se valida además que el usuario autenticado sea propietario del recurso.

## Paginación

Los endpoints que pueden devolver grandes cantidades de información utilizan paginación con Spring Data `Pageable`.

Ejemplo:

GET /api/publicaciones?page=0&size=10

## Optimización del feed

Para evitar realizar consultas adicionales por cada publicación, los conteos de likes y comentarios se obtienen mediante consultas agrupadas.

En lugar de consultar individualmente cada publicación:

1 query de publicaciones
+ N queries de likes
+ N queries de comentarios
+ N queries para comprobar el like del usuario

se realizan consultas en lote mediante `IN` y `GROUP BY`.

Esto reduce considerablemente la cantidad de consultas realizadas a la base de datos.

## Base de datos

MySQL se ejecuta mediante Docker.

Levantar los servicios:

docker compose up -d

Servicios:

- MySQL: puerto 3306
- phpMyAdmin: puerto 8081

## Variables de entorno

La aplicación requiere las siguientes variables:

BD_URL
BD_USER
BD_PASSWORD
JWT_PRIVATE_KEY
JWT_ISSUER

Las credenciales y claves privadas no se almacenan en el repositorio.

## Ejecutar el proyecto

Requisitos:

- Java 21
- Docker
- Maven

1. Clonar el repositorio
2. Configurar las variables de entorno
3. Levantar MySQL

docker compose up -d

4. Ejecutar:

./mvnw spring-boot:run

La API estará disponible en:

http://localhost:8080

## API

Algunos endpoints principales:

POST /api/auth/registro
POST /api/auth/login

GET /api/rutinas/mias
GET /api/entrenamientos
GET /api/publicaciones
POST /api/publicaciones
POST /api/amistades/solicitudes

La mayoría de los endpoints requieren autenticación mediante Bearer Token.

## Estado del proyecto

Backend funcional de GymTrack.

Próximas mejoras:

- Tests unitarios y de integración
- CI/CD
- Migraciones con Flyway
- Deploy
