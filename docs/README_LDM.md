# ModValley - Interfaz Web

## 🏢 Empresa que representa

**ModValley** es una plataforma digital comunitaria dedicada a la centralización, gestión y distribución de contenido personalizado (mods, texturas, sonidos, gráficos, etc.) para videojuegos. 

La empresa ModValley actúa como intermediaria confiable entre creadores de contenido y jugadores, ofreciendo:
- Un repositorio seguro y organizado de recursos
- Una comunidad activa de usuarios creadores y consumidores
- Herramientas para compartir, valorar y comentar contenido
- Un sistema de descargas controlado y seguro

---

## 📄 Páginas incluidas

La interfaz web de ModValley incluye las siguientes páginas:

### 1. **Login** (`login.php`)
- Página de autenticación inicial
- Muestra lista de usuarios disponibles
- Permite seleccionar cuenta sin contraseña (para demostración)
- Gestiona sesiones de usuario
- Redirige al inicio al autenticarse

### 2. **Inicio** (`index.php`)
- Página principal después del login
- Panel de bienvenida con carrusel de imágenes
- Información destacada de la plataforma
- Acceso rápido a secciones principales
- Navegación central del sitio

### 3. **Catálogo** (`catalogo.php`)
- Visualización de todos los recursos disponibles
- Filtrado por videojuego
- Filtrado por categoría
- Búsqueda y ordenamiento por popularidad (descargas)
- Muestra información del recurso (autor, valoración, descargas)
- Acceso a detalles de cada mod
- Opción de descargar recursos

### 4. **Gestión de Contenido** (`gestion.php`)
- Panel para usuarios que suben contenido
- Subir nuevos recursos/mods
- Ver recursos propios subidos
- Editar recursos personales
- Eliminar recursos propios
- Formulario para especificar:
  - Nombre y descripción del recurso
  - Videojuego asociado
  - Categoría del recurso
  - Archivo para descargar

### 5. **Detalles de Mod** (`mod_detalle.php`)
- Vista completa de un recurso específico
- Información detallada del mod:
  - Autor y fecha de creación
  - Descripción completa
  - Videojuego y categoría
  - Valoración promedio
  - Número de descargas
- Sección de comentarios comunitarios
- Sistema de valoración (1-5 estrellas)
- Botón para descargar el recurso

### 6. **Perfil de Usuario** (`perfil.php`)
- Ver información del perfil propio
- Editar datos personales:
  - Cambiar nickname
  - Actualizar biografía
  - Cambiar foto de perfil
- Ver recursos que has subido
- Ver tu historial de descargas
- Estadísticas personales

### 7. **Eliminar Contenido** (`eliminar_mod.php`)
- Página de confirmación para eliminar recursos
- Gestión segura de eliminaciones
- Evita eliminaciones accidentales

### 8. **Logout** (`php/logout.php`)
- Cierra la sesión de usuario
- Limpia las variables de sesión
- Redirige al login

---

## 🚀 Cómo abrirla/visualizarla

### Requisitos previos
- **Servidor PHP**: PHP 7.0 o superior
- **Servidor web**: Apache o equivalente
- **MySQL**: MySQL 5.7 o superior (con base de datos `modvalley`)
- **Navegador web**: Chrome, Firefox, Safari, Edge (versiones actuales)

### Opción 1: Con XAMPP/LAMPP (Recomendado para desarrollo)

1. **Instala XAMPP/LAMPP** desde [https://www.apachefriends.org](https://www.apachefriends.org)

2. **Inicia los servicios:**
   - Apache: ✓ Encendido
   - MySQL: ✓ Encendido

3. **Copia la carpeta `web` a la carpeta raíz:**
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

### Opción 2: Servidor PHP integrado (Para pruebas rápidas)

```bash
cd web
php -S localhost:8000
```

Luego accede a: `http://localhost:8000/login.php`

### Opción 3: Servidor PHP en Linux/macOS

```bash
# Con Apache
sudo cp -r web /var/www/html/

# Accede a
http://localhost/web/login.php
```

---

## ⚙️ Configuración necesaria

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

## 🎨 Recursos visuales

### Carpeta de estilos (`css/`)
- `index.css` - Estilos de la página de inicio
- `login.css` - Estilos de autenticación
- `catalogo.css` - Estilos del catálogo
- `perfil.css` - Estilos del perfil
- `gestion.css` - Estilos de gestión
- `header.css` - Estilos de navegación
- `mod_detalle.css` - Estilos de detalles del mod
- Y otros estilos específicos...

### Carpeta de scripts (`js/`)
- `catalogo.js` - Funcionalidad del catálogo
- `perfil.js` - Funcionalidad del perfil
- `login.js` - Funcionalidad del login
- `lienzo.js` - Efectos de animación (canvas)
- `img_panel_slide.js` - Carrusel de imágenes
- Y otros scripts...

### Carpeta de imágenes (`img/`)
- Imágenes de perfil de usuarios
- Recursos visuales del sitio
- Portadas y banners

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

## 🔐 Seguridad

- Las sesiones se gestiona con `session_start()`
- Conexión a BD mediante MySQLi preparado
- Validación de usuarios autenticados en cada página
- Protección contra inyección SQL
- Las contraseñas se almacenan en base de datos

---

## 🌐 Flujo de navegación

```
Login (login.php)
    ↓
Inicio (index.php) ← Página central
    ├→ Catálogo (catalogo.php)
    │   └→ Detalles Mod (mod_detalle.php)
    │       ├→ Descargar (API/descargar.php)
    │       └→ Comentar/Valorar
    │
    ├→ Gestión (gestion.php)
    │   ├→ Subir Contenido (subir_mod.php)
    │   ├→ Editar Recurso
    │   └→ Eliminar (eliminar_mod.php)
    │
    └→ Perfil (perfil.php)
        ├→ Editar Perfil
        ├→ Ver Recursos
        └→ Ver Descargas
    
    └→ Logout (php/logout.php)
        ↓
    Vuelve a Login
```

---

## 💡 Notas importantes

- La web necesita un servidor PHP en funcionamiento (no funciona con archivos locales)
- La base de datos debe estar activa (MySQL ejecutándose)
- Asegúrate de que los permisos de archivos están correctos
- Algunas funcionalidades requieren que esté logueado el usuario
- Las imágenes deben guardarse en la carpeta `img/`

---

## 📝 Autor

**Adrián Rangel** - Proyecto-Intermodular-DAW
