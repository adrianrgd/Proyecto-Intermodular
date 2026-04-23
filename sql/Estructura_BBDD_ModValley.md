# Documentación de Base de Datos: ModValley

## 1. Plan de Estructura
ModValley funciona como un ecosistema donde los **Videojuegos** son los contenedores principales. Cada **Recurso (Mod)** se clasifica obligatoriamente en un juego y en un tipo (Categoría). El sistema permite el seguimiento de interacciones (descargas, valoraciones y comentarios) vinculadas a los usuarios.

## 2. Diccionario de Datos Principal

| Tabla | Propósito |
| :--- | :--- |
| **USUARIO** | Gestión de cuentas para login y autoría de mods. |
| **VIDEOJUEGO** | Catálogo de juegos filtrables por género y plataforma. |
| **CATEGORIA** | Tipos de archivos (Texturas, Scripts, Mapas, etc.). |
| **RECURSO** | El Mod en sí, vinculado a un autor, un juego y un tipo. |

## 3. Lógica de Interacciones
Para cumplir con los requisitos de **Programación**, se han implementado tablas de registro de actividad:
* **Descargas:** Cada acción de bajar un archivo genera un registro con fecha.
* **Valoraciones:** Sistema numérico (1-5) para feedback.
* **Comentarios:** Registro de texto para la comunidad.

---
*Nota: La fecha de subida y creación se gestiona automáticamente mediante `DEFAULT CURRENT_DATE` en el servidor SQL.*