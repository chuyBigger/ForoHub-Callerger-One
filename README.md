# Proyecto API Foro - Plataforma de Discusión en Java Spring Boot

¡Bienvenido a **API Foro**, un sistema backend desarrollado con **Java y Spring Boot** que gestiona foros de discusión en línea!  
Esta API permite la creación, visualización, actualización y eliminación de tópicos, así como la gestión de usuarios y autenticación mediante JWT. Está diseñada siguiendo buenas prácticas de desarrollo y arquitectura RESTful.

---

## 📝 Descripción

Este proyecto implementa un backend para un foro con las siguientes características:

- API RESTful para la gestión de tópicos, cursos y usuarios.
- Seguridad y autenticación con **JWT**.
- Validaciones de datos usando **Jakarta Validation**.
- Persistencia de datos con **Spring Data JPA** y **MySQL**.
- Manejo centralizado de excepciones y mensajes de error claros.
- Estructura modular para facilitar mantenimiento y escalabilidad.

---

## 📌 Funcionalidades principales

### ✅ Gestión de Tópicos
- Crear un nuevo tópico asociado a un curso y usuario.
- Listar tópicos activos con paginación.
- Actualizar título y mensaje de un tópico.
- Eliminar tópicos (borrado lógico).

### ✅ Gestión de Cursos
- Registrar nuevos cursos.
- Listar cursos existentes.
- Relacionar cursos con tópicos.

### ✅ Gestión de Usuarios
- Registro de nuevos usuarios.
- Asignación de perfiles y roles.
- Autenticación segura con JWT.

### ✅ Seguridad
- Encriptación de contraseñas con **BCrypt**.
- Control de acceso por roles.
- Protección de endpoints con **Spring Security**.

### Otras características:
- Documentación de API con **Swagger UI**.
- Control de errores y respuestas HTTP adecuadas.
- Arquitectura limpia con separación en capas (`domain`, `infra`, `controller`).

---

## 🛠️ Tecnologías utilizadas

- **Java 17+**
- **Spring Boot 3+**
- **Spring Security + JWT**
- **Spring Data JPA**
- **MySQL**
- **Hibernate**
- **Jakarta Validation**
- **Swagger/OpenAPI**
- **Maven**

---

## 🚀 Cómo ejecutar el proyecto

1. Clona el repositorio:

   ```bash
   git clone https://github.com/tuusuario/api-foro.git
   ```

2. Configura la base de datos en `application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/foro
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   api.security.secret=clave_secreta
   ```

3. Instala las dependencias:

   ```bash
   mvn install
   ```

4. Ejecuta la aplicación:

   ```bash
   mvn spring-boot:run
   ```

5. Accede a la documentación de la API en Swagger:

   ```
   http://localhost:8080/swagger-ui/index.html
   ```

---

## 🧱 Estructura del proyecto

```
# 🌐 Estructura Completa del Proyecto ForoHub

```bash
ForoHubChallerger/
├── src/
│   ├── main/
│   │   ├── java/com/alura/Forohub/
│   │   │   ├── controller/                  # Controladores REST
│   │   │   │   ├── AutenticacionController.java  # Endpoints de auth
│   │   │   │   ├── TopicoController.java   # Gestión de tópicos
│   │   │   │   └── UsuarioController.java  # Gestión de usuarios
│   │   │   │
│   │   │   ├── domain/                     # Dominio y persistencia
│   │   │   │   ├── curso/
│   │   │   │   │   ├── Categoria.java      # Enum de categorías
│   │   │   │   │   ├── Curso.java          # Entidad JPA
│   │   │   │   │   └── CursoRepository.java # Repositorio Spring Data
│   │   │   │   │
│   │   │   │   ├── perfiles/
│   │   │   │   │   ├── Perfil.java         # Entidad de perfiles
│   │   │   │   │   └── PerfilRepository.java
│   │   │   │   │
│   │   │   │   ├── respuesta/
│   │   │   │   │   ├── Respuesta.java      # Entidad de respuestas
│   │   │   │   │   └── RespuestaRepository.java
│   │   │   │   │
│   │   │   │   ├── topico/
│   │   │   │   │   ├── validaciones/       # Validaciones custom
│   │   │   │   │   │   ├── ValidadorDeTopicos.java
│   │   │   │   │   │   └── ValidaTopicoUnico.java
│   │   │   │   │   │
│   │   │   │   │   ├── Estado.java         # Enum de estados
│   │   │   │   │   ├── Topico.java         # Entidad principal
│   │   │   │   │   ├── TopicoRepository.java
│   │   │   │   │   └── TopicoService.java  # Lógica de negocio
│   │   │   │   │
│   │   │   │   ├── usuarios/
│   │   │   │   │   ├── AuthenticationService.java
│   │   │   │   │   ├── Usuario.java        # Entidad de usuarios
│   │   │   │   │   ├── UsuarioRepository.java
│   │   │   │   │   └── UsuarioService.java
│   │   │   │   │
│   │   │   │   └── infra/
│   │   │   │       ├── exceptions/         # Manejo de errores
│   │   │   │       │   ├── ManejoDeErrores.java
│   │   │   │       │   └── ManejoDeExepciones.java
│   │   │   │       │
│   │   │   │       └── security/           # Configuración JWT
│   │   │   │           ├── DatosTokenJWT.java
│   │   │   │           ├── SecurityConfig.java
│   │   │   │           ├── SecurityFilter.java
│   │   │   │           └── TokenService.java
│   │   │   │
│   │   │   ├── dto/                        # Objetos de transferencia
│   │   │   │   ├── DatosAutenticacion.java
│   │   │   │   ├── DatosDetalleUsuario.java
│   │   │   │   ├── DatosListaUsuarios.java
│   │   │   │   ├── DatosRegistroUsuario.java
│   │   │   │   ├── DatosActualizarTopico.java
│   │   │   │   ├── DatosBusquedaTopicos.java
│   │   │   │   ├── DatosCrearTopico.java
│   │   │   │   ├── DatosDetalleTopico.java
│   │   │   │   └── DatosListaTopicos.java
│   │   │   │
│   │   │   └── ForoHubChallergerApplication.java # Main class
│   │   │
│   │   └── resources/
│   │       ├── db/migration/               # Migraciones Flyway
│   │       │   ├── V1_crear_table_perfiles.sql
│   │       │   ├── V2_crear_table_usuarios.sql
│   │       │   ├── V3_crear_table_topicos.sql
│   │       │   ├── V4_crear_table_respuestas.sql
│   │       │   ├── V5_crear_table_cursos.sql
│   │       │   ├── V6_insertar_perfiles.sql
│   │       │   ├── V7_insertar_cursos.sql
│   │       │   ├── V8_agregar_column_curso_id_a_topicos.sql
│   │       │   ├── V9_borrar_columnacurso.sql
│   │       │   └── V10_agregar_columnactivo_topicos.sql
│   │       │
│   │       ├── application.yml             # Configuración principal
│   │       └── static/                     # Recursos estáticos (vacío)
│   │
│   └── test/                              # Pruebas (pendiente)
│       └── java/com/alura/Forohub/
│           └── ForoHubChallergerApplicationTests.java
│
├── .mvn/                                  # Configuración Maven
├── target/                                # Build output
├── .gitignore
├── pom.xml
└── README.md
```

---

## 📊 Ejemplos de uso

### 1️⃣ Autenticación y obtención de token JWT

**Petición:**
```http
POST /login
Content-Type: application/json

{
  "correoElectronico": "usuario@foro.com",
  "password": "123456"
}
```

**Respuesta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5..."
}
```

---

### 2️⃣ Crear un nuevo tópico

**Petición:**
```http
POST /topicos
Authorization: Bearer {token}
Content-Type: application/json

{
  "titulo": "Problema con Spring Security",
  "mensaje": "No puedo acceder a los endpoints protegidos",
  "nombreCurso": "Spring Boot",
  "correoElectronicoAutor": "usuario@foro.com"
}
```

**Respuesta:**
```json
{
  "id": 12,
  "titulo": "Problema con Spring Security",
  "mensaje": "No puedo acceder a los endpoints protegidos",
  "fechaCreacion": "2025-08-08T14:35:20",
  "status": "ABIERTO",
  "autor": "usuario@foro.com",
  "curso": "Spring Boot"
}
```

---

### 3️⃣ Listar tópicos paginados

**Petición:**
```http
GET /topicos?page=0&size=5
Authorization: Bearer {token}
```

**Respuesta:**
```json
{
  "content": [
    {
      "id": 1,
      "titulo": "Error en configuración de Maven",
      "mensaje": "El proyecto no compila",
      "fechaCreacion": "2025-08-01T10:15:00",
      "status": "ABIERTO",
      "autor": "juan@foro.com",
      "curso": "Java Avanzado"
    },
    {
      "id": 2,
      "titulo": "Duda sobre JPA",
      "mensaje": "¿Cómo mapear una relación ManyToMany?",
      "fechaCreacion": "2025-08-02T16:50:00",
      "status": "RESPONDIDO",
      "autor": "ana@foro.com",
      "curso": "Hibernate"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 5
  },
  "totalPages": 3,
  "totalElements": 15
}
```

---

## 📅 Estado actual del proyecto

✅ Funcionalidades implementadas:
- CRUD de tópicos ✅
- CRUD de cursos ✅
- Gestión de usuarios y roles ✅
- Seguridad JWT ✅
- Documentación con Swagger ✅

🛠️ Mejoras previstas:
- Implementar sistema de comentarios por tópico.
- Agregar notificaciones por eventos.
- Mejorar filtrado y búsqueda avanzada de tópicos.
- Implementar pruebas unitarias y de integración.

---

## 📄 Licencia

Este proyecto está bajo la licencia **MIT**:

```text
MIT License

Copyright (c) 2025 Jesús Medina Casas
...
```

---

## 👨‍💻 Desarrollador

**Jesús Medina Casas**

- 🎓 Estudiante de Oracle Next Education (ONE)
- 💻 Apasionado por el desarrollo backend con Java y Spring Boot
- 🌐 [LinkedIn - Jesús MC](https://www.linkedin.com/in/jesus-medina-casas/)
- 🧑‍💻 [GitHub - Jesus Code MC](https://github.com/chuyBigger)
