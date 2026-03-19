# Logistics Management System

Sistema de gestión logística terrestre y marítima desarrollado con Spring Boot y Angular.

## Tecnologías

- **Backend:** Java 21, Spring Boot 3.5, Spring Security, JWT, JPA, Liquibase
- **Frontend:** Angular 18, TypeScript, SCSS
- **Base de datos:** PostgreSQL 16
- **Infraestructura:** Docker, Docker Compose, Nginx

## Requisitos

- Docker Desktop instalado y corriendo
- Git

## Instalación y ejecución

### Con Docker (recomendado)
```bash
git clone https://github.com/DaniluxGz/logistics-management-system.git
cd logistics-management-system
docker compose up --build
```

- Frontend: http://localhost:4200
- Backend API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html

Para acceder, regístrese desde la pantalla de inicio de la aplicación.

### Sin Docker (desarrollo local)

**Backend:**
```bash
cd backend
mvn spring-boot:run
```

**Frontend:**
```bash
cd frontend/logistics-frontend
npm install
ng serve
```

## Entregables

- `docs/er-diagram/er-diagram.png` — Diagrama Entidad-Relación
- `docs/er-diagram/database-script.sql` — Script SQL de la base de datos
- Repositorio Git — este repositorio
- Artefactos de despliegue — `docker-compose.yml` + `Dockerfile` en backend y frontend

## Funcionalidades

- Autenticación con JWT (registro y login)
- Gestión de clientes
- Envíos terrestres con descuento del 5% para cantidad mayor a 10 unidades
- Envíos marítimos con descuento del 3% para cantidad mayor a 10 unidades
- Gestión de bodegas y puertos
- Documentación automática con Swagger UI
- Migraciones de base de datos con Liquibase

## Reglas de negocio

- Descuento automático del 5% en envíos terrestres con cantidad mayor a 10 unidades
- Descuento automático del 3% en envíos marítimos con cantidad mayor a 10 unidades
- Número de guía único por envío (terrestre y marítimo)
- Precio original y precio final almacenados independientemente
- Formato de placa validado: ABC123 o ABC-123

## Arquitectura

El backend sigue **Arquitectura Hexagonal (Ports & Adapters)** con separación estricta entre:

- `domain` — entidades, repositorios y servicios de dominio
- `application` — casos de uso y lógica de negocio
- `infrastructure` — controladores REST, seguridad JWT y configuración

## Patrones y buenas prácticas

- **Arquitectura Hexagonal** — el dominio no depende de frameworks ni de la base de datos
- **Git Flow** — ramas `main`, `develop` y `feature/*` con commits semánticos (Conventional Commits)
- **DTOs y Mappers** — las entidades JPA no se exponen directamente en la API
- **Variables de entorno para secretos** — credenciales y claves JWT nunca hardcodeadas
- **Tests unitarios** — cobertura de lógica de negocio crítica con JUnit 5 y Mockito
- **Validaciones** — `@Valid`, `@Pattern`, `@Email` en backend y Reactive Forms en Angular
- **Principio de responsabilidad única (SRP)** — cada clase tiene una única razón para cambiar

## Justificación tecnológica

**Java 21 LTS + Spring Boot 3.5** — versión LTS con soporte extendido. Spring Boot reduce configuración boilerplate y acelera el desarrollo de APIs REST productivas.

**Spring Security + JWT** — autenticación stateless mediante Bearer tokens, ideal para APIs consumidas por SPAs. Sin estado en servidor, escalable horizontalmente.

**PostgreSQL 16 + Liquibase** — base de datos relacional robusta y de código abierto. Liquibase gestiona las migraciones de esquema de forma versionada y reproducible.

**Angular 18** — framework empresarial con tipado fuerte (TypeScript), lazy loading por módulos, reactive forms con validaciones y arquitectura escalable.

**Docker + Docker Compose** — garantiza reproducibilidad del entorno. Un solo comando levanta toda la infraestructura sin dependencias locales adicionales.
