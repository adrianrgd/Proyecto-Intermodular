# INFORME TÉCNICO DE ENTORNO DE EJECUCIÓN

## 1. TIPO DE SISTEMA DE EJECUCIÓN

**¿En qué tipo de equipo debería ejecutarse?**

Se recomienda un **servidor dedicado** (físico o máquina virtual).

- **Para desarrollo:** PC/Laptop normal con Windows, Linux o Mac
- **Para producción:** Servidor Linux dedicado o máquina virtual

**¿Por qué servidor?**
- Acceso 24/7 para múltiples usuarios
- Mejor rendimiento y estabilidad
- Facilita copias de seguridad automáticas
- Acceso remoto desde cualquier lugar  

---

## 2. REQUISITOS DE HARDWARE

### Mínimos (para desarrollo local)
| Componente | Especificación |
|-----------|----------------|
| **CPU** | 2 núcleos (Intel i5 / Ryzen 5 o similar) |
| **RAM** | 4 GB |
| **Almacenamiento** | 256 GB SSD |
| **Red** | Ethernet (mínimo 100 Mbps) |

### Recomendados (para servidor producción)
| Componente | Especificación |
|-----------|----------------|
| **CPU** | 4+ núcleos (Intel Xeon / AMD EPYC) |
| **RAM** | 8-16 GB |
| **Almacenamiento** | 500 GB - 2 TB SSD |
| **Red** | Gigabit Ethernet (1000 Mbps) |

---

## 3. SISTEMA OPERATIVO RECOMENDADO

**Sistema principal:** Ubuntu Server (Linux)

**Versión recomendada:** 22.04 LTS

**¿Por qué Linux Ubuntu?**
- Gratuito (sin licencias)
- Rendimiento
- Compatible con Java, PHP y MySQL
- Bajo consumo de recursos

**Alternativas:** 
- Windows Server (caro, consume más recursos)
- Debian (similar a Ubuntu, más estable)

---

## 4. INSTALACIÓN DEL ENTORNO

### Lo que necesitas instalar (en orden)

1. **Java JDK 25**
2. **MySQL 8.0**
3. **Apache + PHP**
4. **Compilar la aplicación Java**

### Paso 1: Instalar Java
```
Descargar e instalar Java JDK 25 desde oracle.com u otras paginas.
Verificar: abrir terminal y escribir "java -version"
```

### Paso 2: Instalar MySQL
```
Descargar MySQL Community Server desde mysql.com
Instalar con contraseña para usuario root
Verificar: abrir aplicación "MySQL Workbench"
```

### Paso 3: Crear Base de Datos
```
Crear base de datos llamada "modvalley"
Crear usuario "modvalley_user" con contraseña
Importar archivo sql/Tablas/CreacionTablas.sql
Importar archivo sql/Tablas/InsercionDatos.sql
```

### Paso 4: Instalar Apache + PHP
```
Descargar Apache desde apache.org
Descargar PHP 8.1 desde php.net
Instalar módulo PHP en Apache
Copiar archivos de web/ a carpeta de Apache
```

### Paso 5: Compilar la Aplicación Java
```
Abrir terminal en carpeta app/
Ejecutar: javac -d bin -cp lib/* src/com/modvalley/**/*.java
Verificar: que exista carpeta bin/com/modvalley/
```

### Paso 6: Ejecutar
```
En la aplicación Java: java -cp bin:lib/* com.modvalley.Main
En el navegador: http://localhost (para acceder a la web PHP)
```

---

## 5. USUARIOS, PERMISOS Y ESTRUCTURA

### Usuarios que existen

| Usuario | Función | Permisos |
|---------|---------|----------|
| **root / admin** | Administrador del sistema | Todo: instalar, actualizar, eliminar |
| **modvalley_user** | Usuario de base de datos | Acceso a base de datos "modvalley" |
| **www-data** | Usuario de servidor web | Lectura/escritura en carpeta web |
| **modvalley_app** | Usuario que ejecuta la app | Acceso a archivos de la aplicación |

### Estructura de carpetas

```
C:\ModValley\  (o /opt/modvalley/ en Linux)
├── app/                    → Código Java compilado y fuente
├── web/                    → Interfaz wbb (PHP, CSS y JS)
├── sql/                    → Scripts de base de datos
└── backups/                → Copias de seguridad

C:\ProgramData\MySQL\       (o /var/lib/mysql/ en Linux)
└── modvalley/              → Base de datos
```

### Donde se guardan los datos

- **Base de datos:** En la carpeta de MySQL (automático)
- **Imágenes de usuarios:** En web/img/perfiles/
- **Logs de aplicación:** En app/logs/
- **Archivos subidos (mods):** En web/descargas/ o similar

### Donde se guardan las copias de seguridad

```
Recomendado: carpeta backups/
- backup_2026-05-13.sql    (diaria)
- backup_completo.zip     (semanal)
```

---

## 6. MANTENIMIENTO

### ¿Qué se debería actualizar?

| Componente | Frecuencia | Cómo |
|-----------|-----------|------|
| **Sistema Operativo** | Mensual | Instalar actualizaciones de seguridad |
| **MySQL** | Semestral | Actualizar a versión estable más nueva |
| **PHP** | Semestral | Si es necesario por seguridad |

### ¿Qué revisar regularmente?

- **Base de datos:** ¿Tiene espacio? ¿Funciona rápido?
- **Logs de errores:** ¿Hay problemas sin resolver?
- **Usuarios activos:** ¿Se conectan todos correctamente?
- **Seguridad:** ¿Hay accesos sospechosos?

### Si algo falla

| Problema | Solución |
|----------|----------|
| App no inicia | Verificar que Java esté instalado, revisar logs |
| Web no funciona | Verificar que Apache esté ejecutándose |
| Base de datos lenta | Hacer copia de seguridad y "limpiar" base de datos |
| Se llena el almacenamiento | Eliminar logs antiguos, revisar carpetas de descargas |
| Fallo de energía | UPS evita apagones; MySQL se recupera automáticamente |

---

## 7. SEGURIDAD

### Contraseñas
- ✅ Cambiar contraseña por defecto de MySQL
- ✅ Usar contraseña fuerte para usuario "modvalley_user"
- ✅ Cambiar contraseña de administrador regularmente

### Base de datos
- ✅ Usuario de MySQL NO es root; es "modvalley_user" con permisos limitados
- ✅ Hacer copia de seguridad
- ✅ Guardar copias en otra unidad o servidor externo

### Archivos
- ✅ Solo usuario "modvalley_app" puede modificar app/
- ✅ Solo usuario "www-data" puede escribir en web/img/
- ✅ Logs en carpeta protegida

---

**Autor:** Adrián Rangel  
