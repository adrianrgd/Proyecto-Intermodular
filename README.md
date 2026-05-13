# 🎮 ModValley

> **Plataforma centralizada para la gestión y distribución de mods, texturas y recursos para videojuegos.**

ModValley nace de la necesidad de tener un único lugar donde la comunidad pueda encontrar, subir, valorar y comentar contenido personalizado para sus juegos favoritos, sin depender de múltiples páginas de dudosa procedencia.

---

## 🧱 Arquitectura del Proyecto

El proyecto está dividido en **dos aplicaciones** que comparten la misma base de datos MySQL:

```
Proyecto-Intermodular-DAW/
├── app/                    # Aplicación de consola (Java)
│   └── src/com/modvalley/
│       ├── config/         # Conexión JDBC
│       ├── controller/     # Lógica de negocio
│       ├── dao/            # Acceso a datos
│       ├── model/          # Entidades
│       └── view/           # Interfaz de consola
│
├── web/                    # Interfaz web (PHP + CSS + JS)
│   ├── Config/             # Configuración de BD
│   ├── php/API/            # Endpoints AJAX
│   ├── css/                # Estilos
│   └── js/                 # Scripts
│
├── sql/                    # Scripts de base de datos
│   └── Tablas/
│       ├── CreacionTablas.sql
│       └── InsercionDatos.sql
│
└── docs/                   # Documentación
```

---

## ✨ Funcionalidades

### 🔐 Autenticación
- Inicio de sesión por selección de usuario
- Registro de nuevas cuentas con nickname, email y biografía

### 📚 Catálogo
- Explorar mods filtrados por videojuego
- Búsqueda en tiempo real sin recarga de página
- Ordenación por descargas y valoración media
- Vista detalle de cada mod con descripción, autor y fecha

### 📤 Gestión de Contenido
- Subir nuevos mods (nombre, descripción, versión, juego y categoría)
- Eliminar mods propios con confirmación
- Los mods publicados aparecen en el catálogo al instante

### Interacción 
- Sistema de valoraciones de 1 a 5 estrellas
- Comentarios por mod, con nombre de usuario y fecha
- Visualización de todas las opiniones en el detalle del mod

### Perfil de Usuario
- Ver y editar nickname, nombre, biografía y foto de perfil
- Historial de mods publicados con estadísticas de descargas
- Historial de valoraciones y reseñas propias
- Eliminación de cuenta

---

## 🛠️ Tecnologías

| Capa | Tecnología |
|------|-----------|
| Aplicación de consola | Java 25, JDBC, MySQL Connector/J |
| Interfaz web | PHP 8.1, HTML5, CSS3, JavaScript |
| Base de datos | MySQL 8.0 |
| Servidor web | Apache 2.4 |
| Iconos | Font Awesome 6 |

---

## 🗄️ Base de Datos

| Tabla | Descripción |
|-------|-------------|
| `USUARIO` | Cuentas registradas en la plataforma |
| `VIDEOJUEGO` | Catálogo de juegos soportados |
| `CATEGORIA` | Tipos de recurso (Mods, Texturas, Mapas…) |
| `RECURSO` | Mods y recursos subidos por usuarios |
| `COMENTARIO` | Comentarios de usuarios sobre recursos |
| `VALORACION` | Puntuaciones (1-5) de usuarios sobre recursos |

---

## 📖 Documentación

| Documento | Descripción |
|-----------|-------------|
| [`docs/README_PROGRAMACION.md`](docs/README_PROGRAMACION.md) | Guía técnica de la app Java |
| [`docs/README_LDM.md`](docs/README_LDM.md) | Guía de la interfaz web |
| [`docs/DISEÑO_BBDD.md`](docs/DISEÑO_BBDD.md) | Diseño y normalización de la base de datos |
| [`docs/INFORME_ENTORNO_EJECUCION.md`](docs/INFORME_ENTORNO_EJECUCION.md) | Guía de instalación en servidor y seguridad |

---

## 👨‍💻 Autor

**Adrián Rangel** — Proyecto Intermodular DAW  
