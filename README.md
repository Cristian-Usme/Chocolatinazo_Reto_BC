# El Chocolatinazo - Backend (Clean Architecture)

Backend del reto **El Chocolatinazo** construido con **Spring Boot**, **Clean Architecture**, **JWT** y **RBAC**.

## Stack

- Java 25
- Spring Boot 4.0.3
- Spring Security
- Spring Data JPA
- H2 en memoria
- JWT (JJWT)
- Springdoc OpenAPI (Swagger UI)

## Arquitectura

El proyecto está organizado por capas:

- `domain/model`: entidades y puertos del dominio (sin framework).
- `domain/usecase`: lógica de negocio.
- `infrastructure/driven-adapters`: persistencia JPA.
- `infrastructure/entry-points/api-rest`: controladores REST, seguridad, DTOs, Swagger.
- `applications/app-service`: ensamblaje de dependencias e inicio de la app.

## Requisitos

- Java 25
- Gradle Wrapper (incluido)

## Cómo ejecutar

### 1) Build

```bash
./gradlew clean build
```

### 2) Run

```bash
./gradlew clean bootRun
```

La API levanta por defecto en:

- `http://localhost:8080`

## Swagger / OpenAPI

Con la app arriba, abre:

- `http://localhost:8080/swagger-ui/index.html`
- `http://localhost:8080/v3/api-docs`

### Cómo autenticar en Swagger

1. Ejecuta `POST /auth/login` o `POST /auth/register`.
2. Copia el `token` del response.
3. En Swagger, clic en **Authorize**.
4. Pega: `Bearer <tu_token>`
5. Prueba endpoints protegidos por rol.

## Endpoints principales

### Auth

- `POST /auth/register` (público)
- `POST /auth/login` (público)

### Game

- `POST /game/pick` (PLAYER)
- `PUT /game/price` (ADMIN)
- `POST /game/calculate-loser` (ADMIN)

### Audit

- `GET /audit/current-game` (AUDITOR)
- `GET /audit/finished-games` (AUDITOR)

## Reglas de negocio clave

- Un jugador no puede elegir dos veces en la misma ronda.
- `calculate-loser` soporta reglas `HIGHEST` y `LOWEST`.
- Al cerrar una ronda:
    - se persiste en `finished_games`,
    - se limpia `current_game_records`.

## Seguridad

- Autenticación con JWT.
- Autorización por rol usando `@PreAuthorize`.
- Respuestas esperadas:
    - `401` si no hay token o es inválido.
    - `403` si el rol no tiene permiso.

## Postman

Se incluyen artefactos listos para importar:

- Colección: `docs/postman/chocolatinazo.postman_collection.json`
- Environment: `docs/postman/chocolatinazo-local.postman_environment.json`

Flujo recomendado en Postman:

1. Ejecutar carpeta `1. Auth` para generar tokens.
2. Ejecutar carpeta `2. Game And Audit Endpoints`.
3. Validar carpeta `3. Security Checks` (`401/403`).

## Uso de IA
1. Asistencia con documentación e investigación.
2. Asistencia con errores de código.
3. Creación del README.md
4. Creación de los paquetes de postman.
