# ModValley - Interfaz Web

## Empresa que representa

**ModValley** es una plataforma digital comunitaria dedicada a la centralización, gestión y distribución de contenido personalizado (mods, texturas, sonidos, gráficos, etc.) para videojuegos. 

La empresa ModValley actúa como intermediaria confiable entre creadores de contenido y jugadores, ofreciendo:
- Un repositorio seguro y organizado de recursos
- Una comunidad activa de usuarios creadores y consumidores
- Herramientas para compartir, valorar y comentar contenido
- Un sistema de descargas controlado y seguro

---

## Páginas incluidas

La interfaz web de ModValley incluye las siguientes páginas:

### 1. **Login** (`login.php`)
- Página de autenticación inicial
- Muestra lista de usuarios disponibles
- Gestiona sesiones de usuario
- Redirige al inicio al autenticarse

### 2. **Inicio** (`index.php`)
- Página principal después del login
- Información destacada de la plataforma
- Acceso rápido a secciones principales

### 3. **Catálogo** (`catalogo.php`)
- Visualización de todos los recursos disponibles
- Filtrado por videojuego
- Búsqueda y ordenamiento por popularidad
- Informacion de los mods
- Opción de descargar mods

### 4. **Gestión de Contenido** (`gestion.php`)
- Panel para usuarios que suben contenido
- Subir recursos
- Eliminar recursos
- Formulario de subida:
  - Nombre y descripción del recurso
  - Videojuego asociado
  - Categoría del recurso

### 5. **Detalles de Mod** (`mod_detalle.php`)
- Vista completa de un recurso específico
- Información detallada del mod:
- Comentarios
- Valoraciones (1-5 estrellas)
- Descargar

### 6. **Perfil de Usuario** (`perfil.php`)
- Ver información del usuario
- Editar datos personales:
  - Cambiar nickname
  - Actualizar biografía
  - Cambiar foto de perfil
- Ver recursos
- Historial de descargas

### 7. **Eliminar Contenido** (`eliminar_mod.php`)
- Página de confirmación para eliminar recursos
- Gestión segura de eliminaciones

---

## Visualizar la web

### Requisitos previos
- **Servidor PHP**: PHP 7.0 o superior
- **Servidor web**: Apache o equivalente
- **MySQL**: MySQL 5.7 o superior (con base de datos `modvalley`)
- **Navegador web**: Chrome, Firefox, Safari, Edge, etc...

### XAMPP - Servidor Local

1. **Instala XAMPP/LAMPP** desde su pagina oficial.

2. **Inicia los servicios:**
   - Apache: ✓ Encendido
   - MySQL: ✓ Encendido

3. **Copia la carpeta `web`:**
   ```
   C:\xampp\htdocs\web\    (Windows)
   /opt/lampp/htdocs/web/  (Linux)
   ```

4. **Asegúrate que la base de datos está configurada:**
   - Ejecuta los scripts SQL: 
     - `sql/Tablas/CreacionTablas.sql`
     - `sql/Tablas/InsercionDatos.sql`

5. **Abre en el navegador:**
   ```
   http://localhost/web/login.php
   ```

## Configuración

### Conexión a Base de datos
El archivo de configuración está en: `Config/db.php`

```php
$host = "localhost";
$user = "root";
$pass = "mysql"; 
$db   = "modvalley";
```

**Modifica estos valores según tu instalación de MySQL:**

### Crear la base de datos

1. Abre phpMyAdmin: `http://localhost/phpmyadmin`
2. Crea una nueva base de datos llamada `modvalley`
3. Ejecuta los scripts SQL en orden:
   - Primero: `sql/Tablas/CreacionTablas.sql`
   - Después: `sql/Tablas/InsercionDatos.sql`

---

## 📁 Estructura de archivos

```
web/
├── index.php                 # Página de inicio
├── login.php                 # Autenticación
├── catalogo.php              # Catálogo de recursos
├── gestion.php               # Gestión de contenido
├── perfil.php                # Perfil de usuario
├── mod_detalle.php           # Detalles del mod
├── eliminar_mod.php          # Eliminar contenido
├── subir_mod.php             # Subir nuevo recurso
│
├── Config/
│   └── db.php                # Configuración de BD
│
├── php/
│   ├── api.php               # API general
│   ├── logout.php            # Logout
│   ├── API/
│   │   ├── api.php           # API adicional
│   │   ├── descargar.php     # Descargas
│   │   ├── mod_acciones.php  # Acciones de mods
│   │   ├── obtener_mods.php  # Obtener listado
│   │   ├── perfil_acciones.php # Acciones de perfil
│   │   └── registro.php      # Registro de usuarios
│   └── Config/
│       └── ...
│
├── css/                      # Estilos CSS
│   ├── index.css
│   ├── login.css
│   ├── catalogo.css
│   ├── header.css
│   ├── perfil.css
│   └── ...
│
├── js/                       # Scripts JavaScript
│   ├── catalogo.js
│   ├── login.js
│   ├── perfil.js
│   ├── lienzo.js
│   └── ...
│
├── img/                      # Imágenes
│   └── perfiles/             # Fotos de perfil
│
└── README.md                 # Este archivo
```

---

## 📝 Autor

**Adrián Rangel** - Proyecto-Intermodular-DAW
