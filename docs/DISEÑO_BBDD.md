# Diseño de Base de Datos ModValley

## 📊 Descripción General

La base de datos **ModValley** está diseñada siguiendo el modelo **Entidad-Relación (E-R)** con normalización hasta la **Tercera Forma Normal (3NF)**. Utiliza un enfoque relacional estructurado para gestionar recursos de videojuegos, usuarios, interacciones comunitarias y metadatos.

### Características principales:
- **Tipo de BD:** MySQL Relacional
- **Normalización:** 3NF (Tercera Forma Normal)
- **Total de tablas:** 6 tablas principales
- **Relaciones:** 9 relaciones (Foreign Keys)
- **Integridad referencial:** Sí (mediante FOREIGN KEY)

---

## 📋 Entidades y Cardinalidades

### 1. **USUARIO** (Entidad Independiente)

**Propósito:** Almacenar información de usuarios registrados en la plataforma.

| Campo | Tipo | Restricción | Descripción |
|-------|------|-------------|-------------|
| `id_usuario` | INT | PK, AUTO_INCREMENT | Identificador único |
| `nickname` | VARCHAR(50) | NOT NULL | Nombre de usuario único |
| `nombre` | VARCHAR(100) | DEFAULT NULL | Nombre completo |
| `email` | VARCHAR(100) | UNIQUE, NOT NULL | Correo electrónico |
| `fecha_registro` | DATE | DEFAULT CURRENT_DATE | Fecha de registro |
| `biografia` | TEXT | DEFAULT 'Sin Biografía' | Descripción del perfil |
| `foto_perfil` | VARCHAR(50) | DEFAULT 'foto_perfil_default.png' | Ruta de foto |

**Cardinalidades de USUARIO:**
- **USUARIO (1) → (N) RECURSO** - Un usuario puede crear múltiples recursos
- **USUARIO (1) → (N) COMENTARIO** - Un usuario puede escribir múltiples comentarios
- **USUARIO (1) → (N) VALORACION** - Un usuario puede dar múltiples valoraciones

---

### 2. **CATEGORIA** (Entidad Independiente)

**Propósito:** Clasificar los tipos de recursos disponibles.

| Campo | Tipo | Restricción | Descripción |
|-------|------|-------------|-------------|
| `id_categoria` | INT | PK, AUTO_INCREMENT | Identificador único |
| `nombre_cat` | VARCHAR(50) | NOT NULL | Nombre de la categoría |

**Valores típicos:** 
- Mods
- Texturas
- Soundtracks
- Gráficos
- Skins
- Mapas
- Scripts

**Cardinalidades de CATEGORIA:**
- **CATEGORIA (1) → (N) RECURSO** - Una categoría contiene múltiples recursos

---

### 3. **VIDEOJUEGO** (Entidad Independiente)

**Propósito:** Mantener registro de los videojuegos soportados en la plataforma.

| Campo | Tipo | Restricción | Descripción |
|-------|------|-------------|-------------|
| `id_videojuego` | INT | PK, AUTO_INCREMENT | Identificador único |
| `nombre_juego` | VARCHAR(100) | NOT NULL | Nombre del juego |
| `genero` | VARCHAR(50) | NOT NULL | Género (RPG, FPS, etc.) |
| `plataforma` | VARCHAR(50) | NOT NULL | Plataforma (PC, PS5, etc.) |

**Ejemplos de datos:**
- Skyrim (RPG, PC/Consolas)
- Minecraft (Sandbox, Multiplataforma)
- The Witcher 3 (RPG, PC/Consolas)

**Cardinalidades de VIDEOJUEGO:**
- **VIDEOJUEGO (1) → (N) RECURSO** - Un videojuego puede tener múltiples recursos

---

### 4. **RECURSO** (Entidad Principal - Dependiente)

**Propósito:** Almacenar información de mods, texturas y otros recursos creados por usuarios.

| Campo | Tipo | Restricción | Descripción |
|-------|------|-------------|-------------|
| `id_recurso` | INT | PK, AUTO_INCREMENT | Identificador único |
| `nombre_rec` | VARCHAR(100) | NOT NULL | Nombre del recurso |
| `descripcion` | TEXT | NOT NULL | Descripción detallada |
| `version` | VARCHAR(20) | NOT NULL | Versión del recurso |
| `num_descargas` | INT | DEFAULT 0 | Contador de descargas |
| `fecha_subida` | DATE | DEFAULT CURRENT_DATE | Fecha de creación |
| `id_usuario` | INT | FK → USUARIO | Quién subió el recurso |
| `id_videojuego` | INT | FK → VIDEOJUEGO | Videojuego asociado |
| `id_categoria` | INT | FK → CATEGORIA | Tipo de recurso |

**Cardinalidades de RECURSO:**
- **USUARIO (1) ← → (N) RECURSO** - Relación 1:N desde USUARIO
- **VIDEOJUEGO (1) ← → (N) RECURSO** - Relación 1:N desde VIDEOJUEGO
- **CATEGORIA (1) ← → (N) RECURSO** - Relación 1:N desde CATEGORIA
- **RECURSO (1) → (N) COMENTARIO** - Un recurso puede tener múltiples comentarios
- **RECURSO (1) → (N) VALORACION** - Un recurso puede recibir múltiples valoraciones

---

### 5. **COMENTARIO** (Entidad Dependiente)

**Propósito:** Almacenar comentarios de usuarios sobre recursos específicos.

| Campo | Tipo | Restricción | Descripción |
|-------|------|-------------|-------------|
| `id_comentario` | INT | PK, AUTO_INCREMENT | Identificador único |
| `comentario` | TEXT | NOT NULL | Contenido del comentario |
| `fecha` | DATE | DEFAULT CURRENT_DATE | Fecha de comentario |
| `id_usuario` | INT | FK → USUARIO | Quién comenta |
| `id_recurso` | INT | FK → RECURSO | Recurso comentado |

**Cardinalidades de COMENTARIO:**
- **USUARIO (1) ← → (N) COMENTARIO** - Relación 1:N desde USUARIO
- **RECURSO (1) ← → (N) COMENTARIO** - Relación 1:N desde RECURSO

---

### 6. **VALORACION** (Entidad Dependiente - Tabla de Intersección)

**Propósito:** Registrar las valoraciones (ratings) que usuarios dan a recursos.

| Campo | Tipo | Restricción | Descripción |
|-------|------|-------------|-------------|
| `id_valoracion` | INT | PK, AUTO_INCREMENT | Identificador único |
| `puntuacion` | INT | CHECK (1-5), NOT NULL | Calificación de 1 a 5 |
| `id_usuario` | INT | FK → USUARIO | Quién valora |
| `id_recurso` | INT | FK → RECURSO | Recurso valorado |

**Restricciones:**
- Las puntuaciones deben estar entre 1 y 5 estrellas
- Un usuario puede valorar un recurso una sola vez (idealmente con UNIQUE)

**Cardinalidades de VALORACION:**
- **USUARIO (1) ← → (N) VALORACION** - Relación 1:N desde USUARIO
- **RECURSO (1) ← → (N) VALORACION** - Relación 1:N desde RECURSO

---

## 📈 Cardinalidades Completas

| Relación | De | A | Cardinalidad | Descripción |
|----------|-------|-----------|--------------|-------------|
| 1 | USUARIO | RECURSO | 1:N | Un usuario crea N recursos |
| 2 | USUARIO | COMENTARIO | 1:N | Un usuario escribe N comentarios |
| 3 | USUARIO | VALORACION | 1:N | Un usuario da N valoraciones |
| 4 | CATEGORIA | RECURSO | 1:N | Una categoría contiene N recursos |
| 5 | VIDEOJUEGO | RECURSO | 1:N | Un juego tiene N recursos |
| 6 | RECURSO | COMENTARIO | 1:N | Un recurso recibe N comentarios |
| 7 | RECURSO | VALORACION | 1:N | Un recurso recibe N valoraciones |
| 8 | COMENTARIO | USUARIO | N:1 | N comentarios de 1 usuario (inverso) |
| 9 | VALORACION | USUARIO | N:1 | N valoraciones de 1 usuario (inverso) |

---

## 🔑 Claves Primarias y Foráneas

### Claves Primarias (PK)
```sql
USUARIO.id_usuario          -- PK AUTO_INCREMENT
CATEGORIA.id_categoria      -- PK AUTO_INCREMENT
VIDEOJUEGO.id_videojuego    -- PK AUTO_INCREMENT
RECURSO.id_recurso          -- PK AUTO_INCREMENT
COMENTARIO.id_comentario    -- PK AUTO_INCREMENT
VALORACION.id_valoracion    -- PK AUTO_INCREMENT
```

### Claves Foráneas (FK)
```sql
RECURSO.id_usuario       → USUARIO.id_usuario
RECURSO.id_videojuego    → VIDEOJUEGO.id_videojuego
RECURSO.id_categoria     → CATEGORIA.id_categoria

COMENTARIO.id_usuario    → USUARIO.id_usuario
COMENTARIO.id_recurso    → RECURSO.id_recurso

VALORACION.id_usuario    → USUARIO.id_usuario
VALORACION.id_recurso    → RECURSO.id_recurso
```

---

## 🎯 Integridad Referencial

### Restricciones implementadas:
1. **No hay eliminación en cascada** - Las referencias se mantienen intactas
2. **Valores NOT NULL** - Campos obligatorios en tablas principales
3. **UNIQUE** - Email de usuario y Nickname (idealmente)
4. **CHECK** - Puntuaciones entre 1-5 en VALORACION
5. **AUTO_INCREMENT** - Generación automática de IDs

### Consideraciones de integridad:
- Si un usuario se elimina, sus RECURSOS, COMENTARIOS y VALORACIONES quedan huérfanos
- Si un RECURSO se elimina, sus COMENTARIOS y VALORACIONES se pierden
- Los videjuegos y categorías son entidades maestras (estables)

---

## 📊 Análisis de Normalización

### Objetivo: Tercera Forma Normal (3NF)

✅ **Primera Forma Normal (1NF)**
- Todos los atributos contienen valores atómicos
- No hay grupos repetitivos
- Cada celda contiene un único valor

✅ **Segunda Forma Normal (2NF)**
- Cumple 1NF
- Todos los atributos no clave dependen completamente de la clave primaria
- No hay dependencias parciales

✅ **Tercera Forma Normal (3NF)**
- Cumple 2NF
- No hay dependencias transitivas
- Cada atributo no clave depende únicamente de la clave primaria

### Eliminación de redundancias:
- VIDEOJUEGO como tabla separada (no duplicar datos de juegos)
- CATEGORIA como tabla separada (no duplicar tipos)
- USUARIO como tabla separada (información centralizada)
- Tablas de intersección para relaciones M:N (COMENTARIO, VALORACION)

---

## 💾 Flujo de Datos

### Caso de uso: Usuario descarga un mod

```
1. Usuario selecciona Recurso desde Catálogo
   └─ Consulta: SELECT * FROM RECURSO WHERE id_videojuego = ?

2. Sistema verifica que recurso existe
   └─ Consulta: SELECT COUNT(*) FROM RECURSO WHERE id_recurso = ?

3. Sistema incrementa contador de descargas
   └─ Update: UPDATE RECURSO SET num_descargas = num_descargas + 1

4. Sistema registra descarga (opcional con tabla DESCARGA)
   └─ Insert: INSERT INTO DESCARGA (id_usuario, id_recurso, fecha)

5. Usuario descarga archivo asociado
```

### Caso de uso: Usuario comenta un recurso

```
1. Usuario escribe comentario en Recurso
   └─ Insert: INSERT INTO COMENTARIO (comentario, id_usuario, id_recurso, fecha)

2. Sistema verifica integridad referencial
   └─ FK CHECK: id_usuario EXISTS in USUARIO
   └─ FK CHECK: id_recurso EXISTS in RECURSO

3. Comentario aparece en vista de recurso
   └─ Select: SELECT c.*, u.nickname FROM COMENTARIO c 
              JOIN USUARIO u ON c.id_usuario = u.id_usuario
              WHERE c.id_recurso = ?
```

---

## 🔄 Relaciones Inversas (Lectura desde el otro lado)

| Desde | Hacia | Cardinalidad Inversa |
|-------|-------|----------------------|
| RECURSO | USUARIO | N:1 (muchos recursos de 1 usuario) |
| RECURSO | CATEGORIA | N:1 (muchos recursos en 1 categoría) |
| RECURSO | VIDEOJUEGO | N:1 (muchos recursos del 1 videojuego) |
| COMENTARIO | USUARIO | N:1 (muchos comentarios de 1 usuario) |
| COMENTARIO | RECURSO | N:1 (muchos comentarios en 1 recurso) |
| VALORACION | USUARIO | N:1 (muchas valoraciones de 1 usuario) |
| VALORACION | RECURSO | N:1 (muchas valoraciones de 1 recurso) |

---

## 🎓 Ejemplos de Consultas por Cardinalidad

### Relación USUARIO → RECURSO (1:N)
```sql
-- Obtener todos los recursos de un usuario
SELECT r.* FROM RECURSO r
WHERE r.id_usuario = 5;

-- Obtener usuario con su cantidad de recursos
SELECT u.nickname, COUNT(r.id_recurso) as total_recursos
FROM USUARIO u
LEFT JOIN RECURSO r ON u.id_usuario = r.id_usuario
GROUP BY u.id_usuario;
```

### Relación VIDEOJUEGO → RECURSO (1:N)
```sql
-- Obtener todos los mods de Skyrim
SELECT r.* FROM RECURSO r
JOIN VIDEOJUEGO v ON r.id_videojuego = v.id_videojuego
WHERE v.nombre_juego = 'Skyrim'
ORDER BY r.num_descargas DESC;
```

### Relación RECURSO → COMENTARIO (1:N)
```sql
-- Obtener comentarios de un recurso con autor
SELECT c.comentario, c.fecha, u.nickname
FROM COMENTARIO c
JOIN USUARIO u ON c.id_usuario = u.id_usuario
WHERE c.id_recurso = 15
ORDER BY c.fecha DESC;
```

### Relación RECURSO → VALORACION (1:N)
```sql
-- Obtener valoración promedio de un recurso
SELECT 
  r.nombre_rec,
  AVG(v.puntuacion) as media_valoracion,
  COUNT(v.id_valoracion) as total_valoraciones
FROM RECURSO r
LEFT JOIN VALORACION v ON r.id_recurso = v.id_recurso
WHERE r.id_recurso = 10
GROUP BY r.id_recurso;
```

---

## 📝 Mejoras Futuras

### Tablas que podrían agregarse:

1. **DESCARGA** - Tabla de auditoría (opcional)
   ```sql
   CREATE TABLE DESCARGA (
       id_descarga INT PRIMARY KEY AUTO_INCREMENT,
       id_usuario INT NOT NULL,
       id_recurso INT NOT NULL,
       fecha_descarga DATE DEFAULT CURRENT_DATE,
       FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario),
       FOREIGN KEY (id_recurso) REFERENCES RECURSO(id_recurso)
   );
   ```

2. **ETIQUETA** - Sistema de tags flexible
   ```sql
   CREATE TABLE ETIQUETA (
       id_etiqueta INT PRIMARY KEY AUTO_INCREMENT,
       nombre_etiqueta VARCHAR(50) NOT NULL UNIQUE
   );
   
   CREATE TABLE RECURSO_ETIQUETA (
       id_recurso INT,
       id_etiqueta INT,
       PRIMARY KEY (id_recurso, id_etiqueta),
       FOREIGN KEY (id_recurso) REFERENCES RECURSO(id_recurso),
       FOREIGN KEY (id_etiqueta) REFERENCES ETIQUETA(id_etiqueta)
   );
   ```

3. **REPORTE** - Sistema de reporte de contenido inapropiado

---

## 📊 Estadísticas de Base de Datos

- **Total de tablas:** 6
- **Total de campos:** 28
- **Total de claves primarias:** 6
- **Total de claves foráneas:** 7
- **Total de relaciones 1:N:** 7
- **Entidades independientes:** 3 (USUARIO, CATEGORIA, VIDEOJUEGO)
- **Entidades dependientes:** 3 (RECURSO, COMENTARIO, VALORACION)

---

## 🎯 Conclusión

El diseño de base de datos ModValley sigue principios SOLID de normalización, garantizando:
- ✅ **Consistencia:** Integridad referencial mediante Foreign Keys
- ✅ **Eficiencia:** Eliminación de redundancias
- ✅ **Escalabilidad:** Estructura preparada para crecimiento
- ✅ **Mantenibilidad:** Esquema claro y bien documentado

La arquitectura soporta todas las funcionalidades requeridas: gestión de usuarios, recursos, interacciones comunitarias y búsquedas complejas.

---

**Autor:** Adrián Rangel  
