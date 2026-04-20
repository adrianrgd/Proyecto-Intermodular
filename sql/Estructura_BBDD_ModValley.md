# ESTRUCTURA Y DISEÑO DE LA BBBDD DEL PROYECTO MODVALLEY

## 1. OBJETIVO

El objetivo de este documento es detallar la estructura y el diseño de la base de datos relacional que se utilizará en el proyecto ModValley. Se incluirán las tablas, sus campos, relaciones y restricciones.

## 2. MODELO RELACIONAL

A continuación se muestra el modelo relacional de la base de datos:

```mermaid
    USUARIO >> Sube >> RECURSO
    VIDEOJUEGO >> Tiene >> RECURSO
    CATEGORIA >> Clasifica >> RECURSO
    RECURSO >> Tiene >> DESCARGA
    RECURSO >> Tiene >> COMENTARIO
    RECURSO >> Tiene >> VALORACION
    USUARIO >> Realiza >> DESCARGA
    USUARIO >> Escribe >> COMENTARIO
    USUARIO >> Pone >> VALORACION

    USUARIO {
        int id_usuario PK
        varchar nickname
        varchar email
        varchar password
    }

    VIDEOJUEGO {
        int id_juego PK
        varchar nombre_juego
        varchar genero
        varchar plataforma
    }

    CATEGORIA {
        int id_categoria PK
        varchar nombre_cat
    }

    RECURSO {
        int id_recurso PK
        varchar nombre_rec
        varchar descripcion
        varchar version
        date fecha_subida
        int FK id_usuario
        int FK id_juego
        int FK id_categoria
    }

    DESCARGA {
        int id_descarga PK
        date fecha_descarga
        int FK id_usuario
        int FK id_recurso
    }

    COMENTARIO {
        int id_comentario PK
        varchar comentario
        date fecha
        int FK ID_usuario
        int FK id_recurso
    }

    VALORACION {
        int id_valoracion PK
        int puntuacion
        int FK id_usuario
        int FK id_recurso
    }
```

## 3. DESCRIPCIÓN DE TABLAS

### 3.1. USUARIO

| Campo | Tipo | Descripción |
|-------|------|-------------|
| id_usuario | INT | Clave primaria |
| nickname | VARCHAR | Nickname del usuario |
| email | VARCHAR | Email del usuario |
| password | VARCHAR | Contraseña del usuario |

### 3.2. VIDEOJUEGO

| Campo | Tipo | Descripción |
|-------|------|-------------|
| id_juego | INT | Clave primaria |
| nombre_juego | VARCHAR | Nombre del videojuego |
| genero | VARCHAR | Género del videojuego |
| plataforma | VARCHAR | Plataforma del videojuego |

### 3.3. CATEGORIA

| Campo | Tipo | Descripción |
|-------|------|-------------|
| id_categoria | INT | Clave primaria |
| nombre_cat | VARCHAR | Nombre de la categoría |

### 3.4. RECURSO

| Campo | Tipo | Descripción |
|-------|------|-------------|
| id_recurso | INT | Clave primaria |
| nombre_rec | VARCHAR | Nombre del recurso |
| descripcion | VARCHAR | Descripción del recurso |
| version | VARCHAR | Versión del recurso |
| fecha_subida | DATE | Fecha de subida |
| FK id_usuario | INT | Clave foránea de USUARIO |
| FK id_juego | INT | Clave foránea de VIDEOJUEGO |
| FK id_categoria | INT | Clave foránea de CATEGORIA |

### 3.5. DESCARGA

| Campo | Tipo | Descripción |
|-------|------|-------------|
| id_descarga | INT | Clave primaria |
| fecha_descarga | DATE | Fecha de descarga |
| FK id_usuario | INT | Clave foránea de USUARIO |
| FK id_recurso | INT | Clave foránea de RECURSO |

### 3.6. COMENTARIO

| Campo | Tipo | Descripción |
|-------|------|-------------|
| id_comentario | INT | Clave primaria |
| comentario | VARCHAR | Comentario |
| fecha | DATE | Fecha del comentario |
| FK ID_usuario | INT | Clave foránea de USUARIO |
| FK id_recurso | INT | Clave foránea de RECURSO |

### 3.7. VALORACION

| Campo | Tipo | Descripción |
|-------|------|-------------|
| id_valoracion | INT | Clave primaria |
| puntuacion | INT | Puntuación |
| FK id_usuario | INT | Clave foránea de USUARIO |
| FK id_recurso | INT | Clave foránea de RECURSO |

## 4. RELACIONES

- Un usuario puede subir muchos recursos
- Un videojuego puede tener muchos recursos
- Una categoría puede tener muchos recursos
- Un recurso puede tener muchas descargas
- Un recurso puede tener muchos comentarios
- Un recurso puede tener muchas valoraciones
- Un usuario puede realizar muchas descargas
- Un usuario puede realizar muchos comentarios
- Un usuario puede realizar muchas valoraciones