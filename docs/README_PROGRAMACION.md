# Proyecto-Intermodular-DAW - Adrián Rangel

## 📋 Descripción del Proyecto

### ¿Qué es ModValley?

Se trata de una aplicación que ayuda a gestionar en un mismo lugar todos los archivos, mods, texturas y recursos creados por los usuarios de la comunidad para videojuegos de todo el mundo. Es una plataforma centralizada para la gestión y distribución de contenido personalizado de juegos.

### ¿Para qué sirve?

Sirve para tener un lugar centralizado donde poder encontrar y descargar recursos para juegos, sin tener que buscar en muchas páginas diferentes, evitar páginas dudosas y colaborar en la preservación de contenido comunitario de calidad.

### ¿Qué problemas soluciona?

- Las búsquedas infinitas en múltiples plataformas
- La frustración de no encontrar lo que buscas
- La pérdida de contenido por el paso del tiempo
- La falta de un lugar centralizado y seguro para recursos de juegos
- La necesidad de descargar contenido de fuentes dudosas

---

## 🚀 Cómo se ejecuta

### Requisitos previos
- **Java**: JDK 8 o superior
- **MySQL**: MySQL Server 5.7 o superior
- **Base de datos**: Crear la base de datos `modvalley` usando los scripts SQL

### Pasos de instalación

1. **Configurar la base de datos:**
   - Abre MySQL y ejecuta los scripts SQL en orden:
     - `sql/Tablas/CreacionTablas.sql` - Crea la estructura de las tablas
     - `sql/Tablas/InsercionDatos.sql` - Inserta datos iniciales

2. **Compilar el proyecto:**
   ```bash
   cd app
   javac -d bin src/com/modvalley/**/*.java
   ```

3. **Ejecutar la aplicación:**
   ```bash
   java -cp bin:lib/* com.modvalley.Main
   ```

### Notas de configuración
- La aplicación se conecta a MySQL en `localhost:3306/modvalley`
- Usuario por defecto: `root`
- Contraseña por defecto: `mysql`
- Modifica estos valores en `app/src/com/modvalley/config/Conexion.java` si es necesario

---

## ✨ Funcionalidades principales

### 1. **Autenticación de Usuarios**
- Inicio de sesión seguro
- Registro de nuevos usuarios
- Gestión de credenciales

### 2. **Catálogo de Recursos**
- Explorar todos los mods y recursos disponibles
- Filtrar recursos por videojuego
- Filtrar recursos por categoría
- Buscar recursos específicos
- Ver detalles de cada recurso
- Descargar recursos

### 3. **Gestión de Contenido Personal**
- Subir nuevos recursos (mods, texturas, etc.)
- Editar recursos propios
- Eliminar recursos propios
- Asignar categoría y videojuego a cada recurso
- Administrar archivos personales

### 4. **Interacción Comunitaria**
- Dejar comentarios en recursos
- Valorar y calificar recursos
- Ver valoraciones de otros usuarios
- Leer comentarios de otros usuarios

### 5. **Perfil de Usuario**
- Ver y editar perfil personal
- Cambiar nickname
- Actualizar biografía
- Cambiar foto de perfil
- Ver historial de descargas
- Ver recursos subidos propios

### 6. **Estadísticas y Gestión**
- Ver valoraciones promedio de recursos
- Registrar descargas de recursos
- Información de usuarios creadores

---

## 📦 Entidades que gestiona

La aplicación maneja las siguientes entidades principales:

### **1. Usuario**
- `idUsuario`: Identificador único
- `nickname`: Nombre de usuario
- `email`: Correo electrónico
- `fecha_registro`: Fecha de registro
- `biografía`: Descripción del perfil
- `foto_perfil`: Imagen de perfil

**Acciones:**
- Crear perfil, actualizar perfil, eliminar cuenta
- Subir recursos, descargar recursos
- Comentar y valorar recursos

### **2. Videojuego**
- `idVideojuego`: Identificador único
- Nombre del juego
- Información del juego

**Relación:** Un videojuego puede tener múltiples recursos

### **3. Recurso**
- `idRecurso`: Identificador único
- Título y descripción del recurso
- Tipo de categoría (mod, textura, sound, etc.)
- Videojuego asociado
- Usuario creador
- Fecha de creación
- Archivo descargable

**Acciones:**
- Crear, editar, eliminar recursos
- Recibir comentarios y valoraciones
- Ser descargado por usuarios

### **4. Categoría**
- `idCategoria`: Identificador único
- Nombre de categoría
- Descripción

**Tipos:** Mods, Texturas, Soundtracks, Gráficos, etc.

**Relación:** Una categoría puede contener múltiples recursos

### **5. Comentario**
- `idComentario`: Identificador único
- Texto del comentario
- Usuario creador
- Recurso comentado
- Fecha de creación

**Relación:** Vincula usuarios y recursos

### **6. Valoración**
- `idValoracion`: Identificador único
- Puntuación (1-5 estrellas)
- Usuario que valora
- Recurso valorado
- Fecha de valoración

**Relación:** Vincula usuarios y recursos con ratings

### **7. Descarga**
- `idDescarga`: Identificador único
- Usuario descargador
- Recurso descargado
- Fecha de descarga
- Número de descargas por recurso

**Relación:** Registra el historial de descargas

---

## 🗄️ Integración con Base de Datos

La aplicación utiliza MySQL para la persistencia de datos. El acceso a la base de datos se realiza mediante el patrón **DAO (Data Access Object)**:

### Componentes de acceso a datos:

- **`config/Conexion.java`** - Gestiona la conexión a MySQL
- **DAO (Data Access Objects):**
  - `UsuarioDAO.java` - Operaciones CRUD de usuarios
  - `VideojuegoDAO.java` - Gestión de videojuegos
  - `RecursoDAO.java` - Operaciones con recursos (crear, editar, eliminar, descargar)
  - `CategoriaDAO.java` - Gestión de categorías
  - `InteraccionDAO.java` - Comentarios y valoraciones

### Operaciones de base de datos:

- **Lectura:** Obtener recursos, usuarios, comentarios
- **Creación:** Registrar usuarios, subir recursos, agregar comentarios
- **Actualización:** Editar perfil, modificar recursos, actualizar valoraciones
- **Eliminación:** Eliminar recursos, eliminar comentarios, desactivar usuarios
- **Consultas complejas:** Filtrar por categoría/juego, obtener estadísticas

### Esquema de base de datos:
Consulta `sql/Estructura_BBDD_ModValley.md` para ver el diagrama entidad-relación completo.

---

## 📁 Estructura del proyecto

```
app/
├── src/com/modvalley/
│   ├── Main.java                 # Punto de entrada principal
│   ├── Custom.java               # Utilidades y constantes
│   ├── config/
│   │   └── Conexion.java         # Conexión a MySQL
│   ├── controller/               # Controladores de lógica
│   │   ├── UsuarioController.java
│   │   ├── JuegoController.java
│   │   ├── RecursoController.java
│   │   ├── CategoriaController.java
│   │   └── InteraccionController.java
│   ├── dao/                      # Data Access Objects
│   │   ├── UsuarioDAO.java
│   │   ├── VideojuegoDAO.java
│   │   ├── RecursoDAO.java
│   │   ├── CategoriaDAO.java
│   │   └── InteraccionDAO.java
│   ├── model/                    # Clases de modelo/entidades
│   │   ├── Usuario.java
│   │   ├── Videojuego.java
│   │   ├── Recurso.java
│   │   ├── Categoria.java
│   │   ├── Comentario.java
│   │   ├── Valoracion.java
│   │   └── OpinionDTO.java
│   └── view/                     # Interfaz de usuario (consola)
│       ├── Login.java
│       ├── MenuUsuario.java
│       ├── Catalogo.java
│       ├── PerfilMenu.java
│       ├── GestionarContenido.java
│       └── ...
└── lib/                          # Librerías externas

web/                             # Interfaz web alternativa (PHP)
└── ...

sql/                             # Scripts de base de datos
├── Tablas/
│   ├── CreacionTablas.sql
│   └── InsercionDatos.sql
└── DrawIO/                       # Diagramas

docs/                            # Documentación
```

---

## 👨‍💻 Tecnologías utilizadas

- **Lenguaje:** Java 8+
- **Base de datos:** MySQL 5.7+
- **Arquitectura:** MVC (Model-View-Controller) con patrón DAO
- **Interfaz:** Aplicación de consola + Web alternativa (PHP)
- **Conector JDBC:** MySQL Connector/J

---

## 📝 Autor

**Adrián Rangel** - Proyecto-Intermodular-DAW