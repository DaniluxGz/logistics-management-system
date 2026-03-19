# logistics-management-system
Logistics Management System (Spring Boot + Angular) with REST APIs, validation, security, and business rules for land and maritime shipping.


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
git clone https://github.com/TU_USUARIO/logistics-management-system.git
cd logistics-management-system
docker compose up --build
```

- Frontend: http://localhost:4200
- Backend API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html

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

## Funcionalidades

- Autenticación con JWT (registro y login)
- Gestión de clientes
- Envíos terrestres con descuento del 5% para cantidad > 10
- Envíos marítimos con descuento del 3% para cantidad > 10
- Gestión de bodegas y puertos
- Documentación automática con Swagger
- Migraciones de base de datos con Liquibase

## Arquitectura

El backend sigue arquitectura hexagonal (Ports & Adapters) con las siguientes capas:

- `domain` — entidades y repositorios
- `application` — casos de uso y servicios
- `infrastructure` — controladores REST, seguridad y configuración

## Credenciales de prueba

Regístrate en `/auth/registro` con cualquier email y contraseña de mínimo 6 caracteres.