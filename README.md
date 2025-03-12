# API de Gestión de Cursos

Esta es una API RESTful que permite gestionar cursos y artículos de aprendizaje. La API está construida utilizando **Spring Boot** y **Java 8**, con **MySQL** como base de datos para almacenar la información.

## Descripción

La API permite realizar las siguientes operaciones:
- Crear, obtener, actualizar y eliminar cursos.
- Gestionar artículos relacionados con los cursos.

## Endpoints

### 1. Crear un Curso

- **Método HTTP**: `POST`
- **Endpoint**: `/courses`
- **Descripción**: Crea un nuevo curso con un título y una descripción.
- **Autenticación**: Autenticarse con el token generado
- **Campos requeridos**:
  - `title`: El título del curso (mínimo 1 carácter, máximo 255 caracteres).
  - `description`: La descripción del curso (mínimo 1 carácter, máximo 2048 caracteres).

#### Ejemplo de solicitud:

**URL**: 
```
POST http://localhost:8080/courses
```

**Body** (JSON):
```json
{
  "title": "Curso de Programación Java",
  "description": "Este curso cubre conceptos básicos y avanzados de Java."
}
```

#### Respuesta Exitosa (201 Created):

```json
{
  "id": "UUID_GENERADO",
  "title": "Curso de Programación Java",
  "description": "Este curso cubre conceptos básicos y avanzados de Java.",
  "createdAt": "2025-03-12T02:56:31",
  "updatedAt": "2025-03-12T02:56:31",
  "articles": []
}
```

#### Respuesta de Error (400 Bad Request):

```json
{
  "timestamp": "2025-03-12T02:56:31.199+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "El título no debe estar vacío",
  "path": "/courses"
}
```

### 2. Obtener Todos los Cursos

- **Método HTTP**: `GET`
- **Endpoint**: `/courses`
- **Descripción**: Obtiene la lista de todos los cursos disponibles.
- **Autenticación**: No es necesario

#### Ejemplo de solicitud:

**URL**:
```
GET http://localhost:8080/courses
```

#### Respuesta Exitosa (200 OK):

```json
[
  {
    "id": "UUID_GENERADO",
    "title": "Curso de Programación Java",
    "description": "Este curso cubre conceptos básicos y avanzados de Java.",
    "createdAt": "2025-03-12T02:56:31",
    "updatedAt": "2025-03-12T02:56:31",
    "articles": []
  },
  {
    "id": "UUID_GENERADO_2",
    "title": "Curso de Python",
    "description": "Este curso enseña los fundamentos de Python.",
    "createdAt": "2025-03-11T12:34:56",
    "updatedAt": "2025-03-11T12:34:56",
    "articles": []
  }
]
```

### 3. Obtener un Curso por ID

- **Método HTTP**: `GET`
- **Endpoint**: `/courses/{id}`
- **Descripción**: Obtiene un curso específico utilizando su ID.
- **Autenticación**: No es necesario

#### Ejemplo de solicitud:

**URL**:
```
GET http://localhost:8080/courses/{id}
```

#### Respuesta Exitosa (200 OK):

```json
{
  "id": "UUID_GENERADO",
  "title": "Curso de Programación Java",
  "description": "Este curso cubre conceptos básicos y avanzados de Java.",
  "createdAt": "2025-03-12T02:56:31",
  "updatedAt": "2025-03-12T02:56:31",
  "articles": []
}
```

### 4. Actualizar un Curso

- **Método HTTP**: `PUT`
- **Endpoint**: `/courses/{id}`
- **Descripción**: Actualiza un curso existente con nuevos valores de título y descripción.
- **Autenticación**: No es necesario
- **Campos requeridos**:
  - `title`: El título del curso (mínimo 1 carácter, máximo 255 caracteres).
  - `description`: La descripción del curso (mínimo 1 carácter, máximo 2048 caracteres).

#### Ejemplo de solicitud:

**URL**:
```
PUT http://localhost:8080/courses/{id}
```

**Body** (JSON):
```json
{
  "title": "Curso de Programación Avanzada en Java",
  "description": "Este curso cubre técnicas avanzadas de programación en Java."
}
```

#### Respuesta Exitosa (200 OK):

```json
{
  "id": "UUID_GENERADO",
  "title": "Curso de Programación Avanzada en Java",
  "description": "Este curso cubre técnicas avanzadas de programación en Java.",
  "createdAt": "2025-03-12T02:56:31",
  "updatedAt": "2025-03-12T02:56:31",
  "articles": []
}
```

### 5. Eliminar un Curso

- **Método HTTP**: `DELETE`
- **Endpoint**: `/courses/{id}`
- **Descripción**: Elimina un curso utilizando su ID.
- **Autenticación**: No es necesario

#### Ejemplo de solicitud:

**URL**:
```
DELETE http://localhost:8080/courses/{id}
```

#### Respuesta Exitosa (204 No Content):
```
(Respuesta vacía, sin contenido)
```

## Seguridad

Esta API no requiere autenticación para acceder a sus endpoints. Sin embargo, en un entorno de producción, se recomienda implementar autenticación y autorización utilizando JWT (JSON Web Tokens) o alguna otra estrategia de seguridad.

## Dependencias

- **Spring Boot 2.x**
- **Java 8**
- **MySQL**
- **Lombok** (para simplificar el código)
- **Spring Data JPA** (para el manejo de la base de datos)
- **Spring Validation** (para la validación de los datos de entrada)
  
## Configuración

La configuración de la base de datos y otras configuraciones específicas de la aplicación se manejan mediante archivos `application.yml` o `application.properties`.

### Ejemplo de `application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mi_base_de_datos
    username: root
    password: mi_contraseña
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
  server:
    port: 8080
```

## Notas Adicionales

- Asegúrate de tener **MySQL** corriendo en tu entorno local.
- Los valores de las propiedades como `username`, `password`, y `url` de la base de datos deben ser configurados correctamente.
  
---
