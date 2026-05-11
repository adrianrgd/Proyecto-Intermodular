# INFORME TÉCNICO DE ENTORNO DE EJECUCIÓN
## ModValley - Gestión de Recursos para Videojuegos

**Autor:** Adrián Rangel  
**Módulo:** Sistemas Informáticos 
**Proyecto:** Proyecto-Intermodular-DAW

---

## 📋 ÍNDICE

1. [Tipo de Sistema de Ejecución](#1-tipo-de-sistema-de-ejecución)
2. [Requisitos de Hardware](#2-requisitos-de-hardware)
3. [Sistema Operativo Recomendado](#3-sistema-operativo-recomendado)
4. [Instalación del Entorno](#4-instalación-del-entorno)
5. [Usuarios, Permisos y Estructura](#5-usuarios-permisos-y-estructura)
6. [Mantenimiento Básico](#6-mantenimiento-básico)
7. [Seguridad Mínima](#7-seguridad-mínima)
8. [Evidencias de Ejecución](#8-evidencias-de-ejecución)

---

## 1. TIPO DE SISTEMA DE EJECUCIÓN

### 1.1 Servidor Local + Máquina Virtual

**ModValley** está diseñada para ejecutarse en dos modalidades:

#### **MODALIDAD 1: Desarrollo y Pruebas (PC de Usuario)**
- **Hardware:** PC/Laptop estándar
- **Justificación:** 
  - Facilita el desarrollo y debugging
  - Permite pruebas rápidas
  - No requiere infraestructura compleja
  - Ideal para equipo de desarrollo

#### **MODALIDAD 2: Producción (Servidor Local Dedicado)**
- **Hardware:** Servidor dedicado o máquina virtual
- **Justificación:**
  - Acceso 24/7 para múltiples usuarios
  - Mejor rendimiento y estabilidad
  - Copias de seguridad automatizadas
  - Monitoreo y mantenimiento centralizado
  - Escalabilidad futura

### 1.2 Recomendación Final

Para **producción**, se recomienda:
- **Servidor físico dedicado** O
- **Máquina virtual** (VMware, Hyper-V, Proxmox)

**Ventajas de VM:**
✅ Fácil replicación  
✅ Snapshots para backups  
✅ Migración entre hosts  
✅ Mejor gestión de recursos  
✅ Aislamiento de seguridad  

---

## 2. REQUISITOS DE HARDWARE

### 2.1 Requisitos MÍNIMOS (Local)

| Componente | Especificación | Justificación |
|-----------|----------------|---------------|
| **CPU** | Intel i5 o AMD Ryzen 5 (2 núcleos) | Suficiente para Java + PHP + MySQL |
| **RAM** | 4 GB | 2GB MySQL, 1GB Java, 1GB Sistema |
| **Almacenamiento** | 256 GB SSD | SO + Apps + Base de datos |
| **Conexión** | Ethernet 100 Mbps | Red interna de desarrollo |
| **Pantalla** | 1920x1080 mínimo | Interfaz web responsive |
| **Periféricos** | Teclado, ratón | Estándar |

### 2.2 Requisitos RECOMENDADOS (Servidor)

| Componente | Especificación | Justificación |
|-----------|----------------|---------------|
| **CPU** | Intel Xeon / AMD EPYC (4+ núcleos) | Múltiples conexiones simultáneas |
| **RAM** | 8-16 GB | Base de datos + caché + sistema |
| **Almacenamiento** | 500 GB - 2 TB SSD RAID 1 | Datos + backups + logs |
| **Conexión Red** | Gigabit Ethernet (1000 Mbps) | Mejor ancho de banda |
| **Fuente Poder** | 500W + UPS | Redundancia y respaldo |
| **Refrigeración** | Sistema activo | Disponibilidad 24/7 |```

### 2.3 Requisitos de Ancho de Banda

| Actividad | Consumo | Estimación |
|-----------|---------|-----------|
| Descarga de mod (promedio 50MB) | 50 Mbps picos | 10 simultáneos = 500 Mbps |
| Base de datos | 1-5 Mbps | Promedio |
| Backups incrementales | 20-50 Mbps | Noche |
| **Total recomendado** | **100+ Mbps** | Gigabit = 1000 Mbps ✅ |

---

## 3. SISTEMA OPERATIVO RECOMENDADO

### 3.1 Selección Principal: Linux Ubuntu Server 22.04 LTS

**Justificación:**
- ✅ Gratuito y de código abierto
- ✅ Soporte de 5 años (LTS)
- ✅ Excelente rendimiento en servidor
- ✅ Comunidad activa y documentación
- ✅ Seguridad robusta
- ✅ Compatible con Java, PHP, MySQL
- ✅ Bajo consumo de recursos

### 3.2 Alternativas Viables

| Sistema | Versión | Pros | Contras |
|---------|---------|------|---------|
| **Ubuntu Server** | 22.04 LTS | Gratuito, soporte, comunidad | - |
| **Debian** | 12 | Muy estable, mínimo | Menos software precompilado |
| **CentOS/Rocky** | 8/9 | Empresa, actualización lenta | Menos paquetes disponibles |
| **Windows Server** | 2022 | Integración Active Directory | Costoso, mayor consumo RAM |
| **Windows 10/11** | Latest | Desarrollo local, GUI | No recomendado para producción |

### 3.3 Configuración de Ubuntu Server

**Requisitos:**
- 2 GB RAM (mínimo)
- 20 GB almacenamiento
- Conexión Ethernet

---

## 4. INSTALACIÓN DEL ENTORNO

### 4.1 PASO 1: Preparación del Sistema Operativo

**Para Linux Ubuntu Server 22.04 LTS:**

```bash
# 1. Actualizar el sistema
sudo apt update
sudo apt upgrade -y

# 2. Instalar paquetes esenciales
sudo apt install -y \
    wget \
    curl \
    git \
    build-essential \
    net-tools \
    htop

# 3. Configurar hostname
sudo hostnamectl set-hostname modvalley-server

# 4. Configurar zona horaria
sudo timedatectl set-timezone Europe/Madrid

# 5. Verificar sistema
uname -a
date
```

**Tiempo estimado:** 5-10 minutos

---

### 4.2 PASO 2: Instalación de Java JDK

```bash
# 1. Instalar OpenJDK 11 (recomendado)
sudo apt install -y openjdk-11-jdk-headless

# 2. Verificar instalación
java -version
javac -version

# 3. Configurar JAVA_HOME (agregar a ~/.bashrc)
echo 'export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64' >> ~/.bashrc
source ~/.bashrc

# 4. Verificar JAVA_HOME
echo $JAVA_HOME
```

**Tiempo estimado:** 3-5 minutos  
**Tamaño descargado:** ~200 MB

---

### 4.3 PASO 3: Instalación de MySQL Server

```bash
# 1. Instalar MySQL Server 8.0
sudo apt install -y mysql-server

# 2. Ejecutar script de seguridad
sudo mysql_secure_installation
# Responder:
# - Cambiar password root: Y
# - Eliminar usuarios anónimos: Y
# - Desactivar login remoto root: Y
# - Eliminar base de datos test: Y
# - Recargar tablas de privilegios: Y

# 3. Iniciar MySQL
sudo systemctl start mysql
sudo systemctl enable mysql

# 4. Verificar estado
sudo systemctl status mysql

# 5. Acceder a MySQL
sudo mysql -u root -p
```

**Tiempo estimado:** 5-8 minutos  
**Tamaño descargado:** ~400 MB

---

### 4.4 PASO 4: Crear Base de Datos ModValley

```bash
# 1. Acceder a MySQL
sudo mysql -u root -p

# 2. Dentro de MySQL, ejecutar:

-- Crear base de datos
CREATE DATABASE modvalley;
CREATE DATABASE modvalley_backup;

-- Crear usuario específico (NO usar root)
CREATE USER 'modvalley_user'@'localhost' IDENTIFIED BY 'password_seguro_123';

-- Asignar permisos
GRANT ALL PRIVILEGES ON modvalley.* TO 'modvalley_user'@'localhost';
GRANT ALL PRIVILEGES ON modvalley_backup.* TO 'modvalley_user'@'localhost';
FLUSH PRIVILEGES;

-- Verificar
SELECT user, host FROM mysql.user;

-- Salir
EXIT;

# 3. Importar estructura de tablas
sudo mysql -u modvalley_user -p modvalley < /ruta/sql/Tablas/CreacionTablas.sql

# 4. Importar datos iniciales
sudo mysql -u modvalley_user -p modvalley < /ruta/sql/Tablas/InsercionDatos.sql

# 5. Verificar
sudo mysql -u modvalley_user -p -e "USE modvalley; SHOW TABLES;"
```

**Tiempo estimado:** 3-5 minutos  
**Privilegios requeridos:** SELECT, INSERT, UPDATE, DELETE, CREATE, DROP

---

### 4.5 PASO 5: Instalación de Apache + PHP (Para interfaz web)

```bash
# 1. Instalar Apache
sudo apt install -y apache2

# 2. Instalar PHP 8.1 + módulos necesarios
sudo apt install -y php8.1 php8.1-cli php8.1-mysql php8.1-mbstring

# 3. Habilitar módulo PHP en Apache
sudo a2enmod php8.1
sudo a2enmod rewrite

# 4. Reiniciar Apache
sudo systemctl restart apache2
sudo systemctl enable apache2

# 5. Verificar estado
sudo systemctl status apache2

# 6. Crear directorio de la aplicación
sudo mkdir -p /var/www/modvalley
sudo chown -R $USER:$USER /var/www/modvalley

# 7. Copiar archivos web
cp -r web/* /var/www/modvalley/

# 8. Configurar permisos
sudo chmod -R 755 /var/www/modvalley
sudo chown -R www-data:www-data /var/www/modvalley/img

# 9. Crear configuración de Apache
sudo nano /etc/apache2/sites-available/modvalley.conf
```

**Contenido de modvalley.conf:**
```apache
<VirtualHost *:80>
    ServerName modvalley.local
    ServerAlias www.modvalley.local
    DocumentRoot /var/www/modvalley

    <Directory /var/www/modvalley>
        Options Indexes FollowSymLinks
        AllowOverride All
        Require all granted
    </Directory>

    <FilesMatch \.php$>
        SetHandler "proxy:unix:/run/php/php8.1-fpm.sock|fcgi://127.0.0.1"
    </FilesMatch>

    ErrorLog ${APACHE_LOG_DIR}/modvalley_error.log
    CustomLog ${APACHE_LOG_DIR}/modvalley_access.log combined
</VirtualHost>
```

```bash
# 10. Habilitar sitio
sudo a2ensite modvalley.conf
sudo apache2ctl configtest  # Debe decir "Syntax OK"
sudo systemctl reload apache2

# 11. Actualizar /etc/hosts (local)
sudo nano /etc/hosts
# Agregar: 127.0.0.1 modvalley.local
```

**Tiempo estimado:** 8-10 minutos  
**Tamaño descargado:** ~300 MB

---

### 4.6 PASO 6: Compilación de aplicación Java

```bash
# 1. Clonar o copiar proyecto
cd /opt/modvalley
# O si está en Git:
git clone <repositorio> /opt/modvalley

# 2. Compilar
cd /opt/modvalley/app
mkdir -p bin
javac -d bin -cp lib/* src/com/modvalley/**/*.java

# 3. Verificar compilación
ls -la bin/com/modvalley/
# Debe mostrar carpetas: config, controller, dao, model, view

# 4. Crear script de ejecución
cat > /opt/modvalley/run.sh << 'EOF'
#!/bin/bash
cd /opt/modvalley/app
java -cp bin:lib/* com.modvalley.Main
EOF

chmod +x /opt/modvalley/run.sh

# 5. Probar ejecución
/opt/modvalley/run.sh  # Debe mostrar login
```

**Tiempo estimado:** 5 minutos (primera vez puede tardar 15-20 seg)

---

### 4.7 PASO 7: Configuración de Acceso Remoto (Opcional)

```bash
# Para que otros usuarios accedan remotamente:

# 1. Modificar configuración MySQL
sudo nano /etc/mysql/mysql.conf.d/mysqld.cnf
# Cambiar: bind-address = 127.0.0.1
# Por:     bind-address = 0.0.0.0 (CUIDADO: requiere firewall)

# 2. Reiniciar MySQL
sudo systemctl restart mysql

# 3. Crear usuario remoto (SOLO si es necesario)
sudo mysql -u root -p
CREATE USER 'modvalley_remote'@'192.168.1.%' IDENTIFIED BY 'password_seguro_456';
GRANT ALL PRIVILEGES ON modvalley.* TO 'modvalley_remote'@'192.168.1.%';
FLUSH PRIVILEGES;
EXIT;

# 4. Abrir puerto en firewall (si aplica)
sudo ufw allow 3306/tcp
sudo ufw allow 80/tcp
sudo ufw allow 443/tcp
```

**⚠️ ADVERTENCIA:** Acceso remoto requiere seguridad adicional (SSL, firewalls, VPN)

---

### 4.8 Verificación de Instalación Completa

```bash
# 1. Verificar Java
java -version
# Debe mostrar: openjdk version "11.0.x"

# 2. Verificar MySQL
mysql -u modvalley_user -p -e "SHOW DATABASES;"
# Debe mostrar: modvalley, modvalley_backup

# 3. Verificar Apache
curl http://modvalley.local
# Debe mostrar página de login

# 4. Verificar compilación
ls /opt/modvalley/app/bin/com/modvalley/Main.class
# Debe existir el archivo

# 5. Verificar servicios activos
sudo systemctl status mysql apache2
```

**Tiempo total de instalación:** 30-45 minutos (primera vez)

---

## 5. USUARIOS, PERMISOS Y ESTRUCTURA

### 5.1 Usuarios del Sistema Operativo

```bash
# Usuario principal (administración)
user: modvalley_admin
grupo: sudo
hogar: /home/modvalley_admin
permisos: sudo (administración completa)

# Usuario de aplicación (ejecución)
user: modvalley_app
grupo: modvalley
hogar: /opt/modvalley
permisos: lectura/escritura en /opt/modvalley, /var/www/modvalley
shell: /bin/bash (o /usr/sbin/nologin si es daemon)

# Usuario web (Apache)
user: www-data (predefinido)
grupo: www-data
permisos: lectura/escritura en /var/www/modvalley/img

# Usuario base de datos (MySQL)
user: modvalley_user
host: localhost
permisos: SELECT, INSERT, UPDATE, DELETE en modvalley.*
```

### 5.2 Crear Usuarios del Sistema

```bash
# 1. Usuario de aplicación
sudo useradd -m -d /opt/modvalley -s /bin/bash modvalley_app
sudo usermod -aG sudo modvalley_app

# 2. Verificar creación
id modvalley_app
groups modvalley_app
```

### 5.3 Estructura de Directorios

```
/opt/modvalley/                          # Raíz de aplicación
├── app/                                 # Aplicación Java
│   ├── bin/                             # Compilados (.class)
│   ├── src/                             # Código fuente
│   ├── lib/                             # Librerías (JAR)
│   └── run.sh                          # Script de ejecución
├── sql/                                 # Scripts SQL
│   ├── Tablas/
│   │   ├── CreacionTablas.sql
│   │   └── InsercionDatos.sql
│   └── backups/                         # Copias de seguridad
├── logs/                                # Logs de aplicación
│   ├── app.log
│   ├── error.log
│   └── access.log
├── config/                              # Configuración
│   ├── modvalley.properties
│   └── database.conf
└── README.md

/var/www/modvalley/                      # Aplicación Web (PHP)
├── index.php
├── login.php
├── catalogo.php
├── gestion.php
├── perfil.php
├── Config/
│   └── db.php
├── css/                                 # Estilos
├── js/                                  # Scripts
├── img/                                 # Imágenes usuario
│   └── perfiles/
├── php/                                 # Backend PHP
│   ├── api.php
│   ├── logout.php
│   └── API/
│       ├── descargar.php
│       ├── mod_acciones.php
│       └── ...
└── temp/                                # Archivos temporales

/var/lib/mysql/modvalley/                # Base de datos
├── usuario.ibd
├── recurso.ibd
├── comentario.ibd
├── valoracion.ibd
├── videojuego.ibd
├── categoria.ibd
└── ...

/var/backups/modvalley/                  # Backups
├── backup_2024_05_01.sql.gz
├── backup_2024_05_02.sql.gz
└── backup_incremental/

/etc/apache2/sites-available/            # Configuración Apache
└── modvalley.conf

/etc/mysql/mysql.conf.d/                 # Configuración MySQL
└── mysqld.cnf
```

### 5.4 Permisos de Archivos

```bash
# Aplicación Java (propiedad: modvalley_app)
/opt/modvalley/app/src/              → 755 (lectura/ejecución)
/opt/modvalley/app/bin/              → 755 (lectura/ejecución)
/opt/modvalley/app/lib/              → 755 (lectura/ejecución)
/opt/modvalley/logs/                 → 755 (lectura/escritura)
/opt/modvalley/sql/backups/          → 755 (lectura/escritura)

# Aplicación Web (propiedad: www-data)
/var/www/modvalley/                  → 755 (Apache puede leer)
/var/www/modvalley/img/perfiles/     → 775 (escritura para subidas)
/var/www/modvalley/temp/             → 775 (archivos temporales)
/var/www/modvalley/php/uploads/      → 775 (descargas temporales)

# Base de datos (propiedad: mysql)
/var/lib/mysql/modvalley/            → 755 (MySQL gestiona)
/var/backups/modvalley/              → 700 (solo root/backup)

# Aplicación de comando
chmod +x /opt/modvalley/run.sh       # Ejecutable
chmod +x /opt/modvalley/backup.sh    # Script de backup
```

### 5.5 Estructura de datos persistentes

```
Almacenamiento de datos:

1. BASE DE DATOS MYSQL
   Ubicación: /var/lib/mysql/modvalley/
   Propiedad: mysql:mysql
   Usuarios de BD:
   - root (solo administración)
   - modvalley_user (aplicación)
   Resguardos: /var/backups/modvalley/

2. ARCHIVOS DE USUARIO (Recursos/Mods)
   Ubicación: /var/www/modvalley/img/ (metadatos)
             /var/storage/modvalley/ (archivos reales)
   Propiedad: www-data:www-data
   Permiso: 755
   Política: Máximo 2 GB por recurso
   Limpieza: Automática cada 30 días (archivos no referenciados)

3. FOTOS DE PERFIL
   Ubicación: /var/www/modvalley/img/perfiles/
   Tamaño máximo: 5 MB
   Formato: JPG, PNG
   Limpieza: Automática al eliminar usuario

4. LOGS
   Ubicación: /var/log/modvalley/
   Retención: 90 días (rotación automática)
   Tamaño: ~100 MB/mes promedio
```

---

## 6. MANTENIMIENTO BÁSICO

### 6.1 Tareas de Mantenimiento Diarias

#### DIARIAMENTE (Preferiblemente noche)

```bash
# 1. Backup automático de base de datos
#!/bin/bash
# Archivo: /opt/modvalley/backup_diario.sh
FECHA=$(date +"%Y%m%d_%H%M%S")
mysqldump -u modvalley_user -p${DB_PASS} modvalley | \
    gzip > /var/backups/modvalley/backup_${FECHA}.sql.gz

# Mantener solo últimos 7 backups
find /var/backups/modvalley/ -name "backup_*.sql.gz" -mtime +7 -delete

echo "Backup creado: backup_${FECHA}.sql.gz"
```

Añadir a crontab:
```bash
# 2:00 AM cada día
0 2 * * * /opt/modvalley/backup_diario.sh >> /var/log/modvalley/backup.log 2>&1
```

#### CADA 6 HORAS

```bash
# Verificar salud del sistema
- Espacio en disco: df -h (>10% disponible es crítico)
- Procesos MySQL: ps aux | grep mysql
- Procesos Apache: sudo systemctl status apache2
- Conexiones activas: netstat -an | grep ESTABLISHED | wc -l
```

#### CADA 12 HORAS

```bash
# Monitorear logs
- tail -f /var/log/apache2/modvalley_error.log
- tail -f /var/log/mysql/error.log
- tail -f /var/log/modvalley/app.log
# Buscar: ERROR, CRITICAL, Exception
```

---

### 6.2 Tareas Semanales

#### CADA LUNES

```bash
# 1. Backup completo de seguridad
sudo mysqldump -u root -p modvalley > /var/backups/modvalley/backup_completo_$(date +%Y%m%d).sql
sudo gzip /var/backups/modvalley/backup_completo_*.sql

# 2. Verificar integridad de tablas
sudo mysql -u root -p -e "CHECK TABLE USUARIO, RECURSO, COMENTARIO, VALORACION;"

# 3. Optimizar tablas
sudo mysql -u root -p -e "OPTIMIZE TABLE USUARIO, RECURSO, COMENTARIO, VALORACION;"

# 4. Verificar espacio de disco
sudo du -sh /var/lib/mysql/modvalley/*
sudo du -sh /var/www/modvalley/*
sudo du -sh /var/backups/modvalley/*
```

#### CADA VIERNES

```bash
# 1. Revisar logs de errores de la semana
grep ERROR /var/log/modvalley/*.log | wc -l

# 2. Verificar actualizaciones disponibles
sudo apt list --upgradable

# 3. Limpiar logs rotados
sudo logrotate -f /etc/logrotate.d/modvalley

# 4. Análisis de recursos más descargados
mysql -u modvalley_user -p modvalley -e \
  "SELECT nombre_rec, num_descargas FROM RECURSO ORDER BY num_descargas DESC LIMIT 10;"
```

---

### 6.3 Tareas Mensuales

#### PRIMER DÍA DEL MES

```bash
# 1. Actualizar sistema
sudo apt update && sudo apt upgrade -y

# 2. Crear backup a largo plazo (guardar en otro servidor/nube)
sudo cp /var/backups/modvalley/backup_completo_*.sql.gz \
     /mnt/backup_externo/modvalley_$(date +%Y%m)/

# 3. Generar reporte de uso
echo "=== REPORTE DE MODVALLEY ===" > /var/log/modvalley/reporte_mensual.txt
echo "Fecha: $(date)" >> /var/log/modvalley/reporte_mensual.txt
echo "Recursos totales: $(mysql -u modvalley_user -p modvalley -e 'SELECT COUNT(*) FROM RECURSO;')" \
  >> /var/log/modvalley/reporte_mensual.txt
echo "Usuarios activos: $(mysql -u modvalley_user -p modvalley -e 'SELECT COUNT(*) FROM USUARIO;')" \
  >> /var/log/modvalley/reporte_mensual.txt

# 4. Limpiar recursos huérfanos
# Eliminar archivos en /var/www/modvalley/img/ que no tienen referencia en RECURSO
```

#### SEGUNDO MIÉRCOLES DEL MES

```bash
# Mantenimiento de seguridad
# 1. Verificar logs de acceso sospechosos
grep "failed" /var/log/apache2/modvalley_access.log | wc -l

# 2. Revisar usuarios de BD sin usar
SELECT user, host, last_seen FROM mysql.user;

# 3. Actualizar certificados SSL (si usa HTTPS)
sudo certbot renew --dry-run

# 4. Verificar cambios no autorizados en /opt/modvalley/
ls -la /opt/modvalley/app/src/
```

---

### 6.4 Tareas Trimestrales (Cada 3 meses)

```bash
# 1. Revisión completa del sistema
sudo systemctl list-units --type=service --state=running

# 2. Prueba de restauración de backup
# Crear BD de prueba y restaurar backup
sudo mysql -u root -p -e "CREATE DATABASE modvalley_test;"
zcat /var/backups/modvalley/backup_completo_*.sql.gz | \
  mysql -u root -p modvalley_test

# 3. Auditoría de permisos
sudo find /opt/modvalley -type f -exec ls -l {} \; | grep -v "755\|644"

# 4. Análisis de rendimiento
sudo iostat -x 1 5  # I/O stats
free -h              # Memoria
```

---

### 6.5 Tareas Anuales

```bash
# 1. Planificación de capacidad
- ¿Cuánto ha crecido el almacenamiento?
- ¿Se necesita más RAM/CPU?
- ¿Se necesita más ancho de banda?

# 2. Actualización Mayor
- Evaluar nuevas versiones de Java, PHP, MySQL
- Planificar actualización gradual
- Pruebas en entorno de staging

# 3. Renovación de certificados
- SSL/TLS (si usa HTTPS)
- Licencias de software (si aplica)

# 4. Revisión de seguridad externa
- Escaneo de puertos: nmap localhost
- Vulnerabilidades conocidas: CVE updates
```

---

### 6.6 Procedimiento ante Fallos

#### **FALLO 1: Base de Datos no responde**

```bash
# 1. Verificar estado
sudo systemctl status mysql

# 2. Si está detenido
sudo systemctl start mysql
sudo systemctl status mysql

# 3. Si continúa fallando
sudo /etc/init.d/mysql restart
sudo systemctl restart mysql

# 4. Verificar integridad
sudo mysqlcheck -u root -p -A

# 5. Si hay corrupción
sudo mysql_repair_db

# 6. Restaurar desde backup
zcat /var/backups/modvalley/backup_completo_*.sql.gz | \
  mysql -u root -p modvalley
```

#### **FALLO 2: Aplicación Java no inicia**

```bash
# 1. Verificar errores de compilación
cd /opt/modvalley/app
javac -d bin -cp lib/* src/com/modvalley/**/*.java 2>&1

# 2. Verificar classpath
echo $CLASSPATH
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64

# 3. Ejecutar con debug
java -cp bin:lib/* -Xmx1024m -Xms512m com.modvalley.Main

# 4. Revisar logs
tail -f /var/log/modvalley/app.log

# 5. Verificar conectividad MySQL
mysql -u modvalley_user -p -h localhost -e "SELECT 1;"
```

#### **FALLO 3: Web no carga (Apache)**

```bash
# 1. Verificar estado
sudo systemctl status apache2

# 2. Revisar logs
sudo tail -f /var/log/apache2/modvalley_error.log

# 3. Verificar sintaxis de configuración
sudo apache2ctl configtest

# 4. Reiniciar Apache
sudo systemctl restart apache2

# 5. Verificar permisos de archivos
ls -la /var/www/modvalley/*.php

# 6. Probar PHP
sudo php -f /var/www/modvalley/Config/db.php
```

#### **FALLO 4: Disco lleno**

```bash
# 1. Identificar culpable
du -sh /* | sort -rh | head -10

# 2. Opciones:
# a) Expandir disco (VM)
# b) Limpiar logs antiguos
sudo find /var/log -name "*.log.*" -delete

# c) Comprimir backups
gzip /var/backups/modvalley/*.sql

# d) Mover data a otro disco
# e) Limpiar caché
sudo apt clean
sudo apt autoclean
```

---

## 7. SEGURIDAD MÍNIMA

### 7.1 Seguridad del Sistema Operativo

```bash
# 1. Firewall (UFW - Uncomplicated Firewall)
sudo apt install -y ufw
sudo ufw default deny incoming
sudo ufw default allow outgoing
sudo ufw allow 22/tcp   # SSH (administración)
sudo ufw allow 80/tcp   # HTTP (web)
sudo ufw allow 443/tcp  # HTTPS (futuro)
sudo ufw enable
sudo ufw status

# 2. Actualización automática
sudo apt install -y unattended-upgrades
sudo dpkg-reconfigure -plow unattended-upgrades

# 3. Sudoers seguros (solo usuarios autorizados)
sudo visudo
# Agregar línea: modvalley_app ALL=(ALL) NOPASSWD: /opt/modvalley/run.sh
# (permite ejecutar sin contraseña solo ese comando)

# 4. Limitar intentos de login SSH
sudo nano /etc/ssh/sshd_config
# MaxAuthTries 3
# MaxSessions 2
# Port 22 (cambiar a no estándar si es posible)
sudo systemctl restart sshd

# 5. Monitorear acceso
sudo apt install -y fail2ban
sudo systemctl enable fail2ban
```

### 7.2 Seguridad de Base de Datos

```bash
# 1. Usuarios con contraseñas fuertes
# Contraseña: mínimo 12 caracteres, letras+números+símbolos

# 2. Cambiar contraseña MySQL root
sudo mysql -u root -p
ALTER USER 'root'@'localhost' IDENTIFIED BY 'nueva_password_fuerte_123';
FLUSH PRIVILEGES;

# 3. Desactivar usuarios innecesarios
mysql> DROP USER 'test'@'localhost';
mysql> DROP USER ''@'localhost';
mysql> DROP USER ''@'%';

# 4. Restringir privilegios
GRANT SELECT, INSERT, UPDATE, DELETE ON modvalley.* 
  TO 'modvalley_user'@'localhost';
# NO: GRANT ALL PRIVILEGES

# 5. Configuración segura
sudo nano /etc/mysql/mysql.conf.d/mysqld.cnf
# Agregar líneas:
# [mysqld]
# bind-address = 127.0.0.1        # Solo localhost
# skip-external-locking            # Seguridad
# symbolic-links = 0               # No permitir symlinks
# skip-name-resolve                # Resolver por IP

# 6. Backup encriptado
mysqldump -u modvalley_user -p modvalley | gpg --symmetric > backup.sql.gpg
```

### 7.3 Seguridad de la Aplicación

```bash
# 1. Credenciales PHP
sudo nano /var/www/modvalley/Config/db.php

<?php
// Almacenar en archivo seguro FUERA de web public
$config = parse_ini_file('/opt/modvalley/config/.db.conf', true);
$host = $config['database']['host'];
$user = $config['database']['user'];
$pass = $config['database']['pass'];
// NO: $pass = "mysql"; (hardcoded)
?>

# 2. Permisos de archivo .php
chmod 600 /var/www/modvalley/Config/db.php
chown www-data:www-data /var/www/modvalley/Config/db.php

# 3. Desactivar funciones PHP peligrosas
sudo nano /etc/php/8.1/apache2/php.ini
# disable_functions = exec,passthru,shell_exec,system,proc_open,popen,curl_exec

# 4. Input validation en PHP
filter_var($_GET['param'], FILTER_VALIDATE_INT);
htmlspecialchars($_GET['search']);

# 5. Prepared statements en PHP (prevenir SQL injection)
$stmt = $conn->prepare("SELECT * FROM USUARIO WHERE id = ?");
$stmt->bind_param("i", $id);
$stmt->execute();
```

### 7.4 Seguridad de Aplicación Java

```bash
# 1. Actualizar librerías
# Revisar /opt/modvalley/app/lib/ periódicamente

# 2. Compilación segura
javac -d bin \
      -Xlint:unchecked \
      -Xlint:deprecation \
      src/com/modvalley/**/*.java

# 3. Ejecución con límites
java -Xmx512m -Xms256m \
     -Djava.security.manager \
     -cp bin:lib/* com.modvalley.Main

# 4. Logs con información sensible
# NO loguear passwords, tokens, datos personales
# SÍ loguear: errores, acciones de usuario (sin datos sensibles)
```

### 7.5 Seguridad de Red

```bash
# 1. Certificado SSL/TLS (HTTPS) - Futuro
sudo apt install -y certbot python3-certbot-apache
sudo certbot certonly --apache -d modvalley.local

# 2. VPN para acceso remoto (si es necesario)
# Usar OpenVPN o WireGuard en lugar de acceso directo

# 3. Monitoreo de red
# Herramientas: netstat, ss, tcpdump
netstat -tulpn
ss -tulpn

# 4. Restricción por IP (si aplica)
# En Apache: Allow from 192.168.1.0/24
# En MySQL: Crear usuarios solo para IPs específicas
```

### 7.6 Auditoría y Logs

```bash
# 1. Configurar logs centralizados
sudo nano /etc/rsyslog.d/modvalley.conf

# 2. Crear carpeta de logs
sudo mkdir -p /var/log/modvalley
sudo chown modvalley_app:modvalley /var/log/modvalley
sudo chmod 755 /var/log/modvalley

# 3. Rotación de logs
sudo nano /etc/logrotate.d/modvalley

/var/log/modvalley/*.log {
    daily
    rotate 90
    compress
    delaycompress
    notifempty
    create 0640 modvalley_app modvalley
    sharedscripts
    postrotate
        /opt/modvalley/app/rotate.sh
    endscript
}

# 4. Monitoreo de cambios en código
sudo apt install -y aide
sudo aideinit
aide --check

# 5. Registro de cambios en BD (auditoría)
# Crear tabla AUDIT_LOG para registrar cambios
CREATE TABLE AUDIT_LOG (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tabla VARCHAR(50),
    operacion VARCHAR(10),  -- INSERT, UPDATE, DELETE
    registro_id INT,
    usuario VARCHAR(50),
    fecha TIMESTAMP,
    cambios TEXT
);
```

### 7.7 Checklist de Seguridad

```
[ ] Firewall activado (UFW)
[ ] SSH en puerto no estándar (opcional)
[ ] Actualizaciones automáticas configuradas
[ ] Contraseñas fuertes en MySQL (12+ caracteres)
[ ] Usuario de aplicación con permisos limitados
[ ] Archivos de configuración con permisos 600
[ ] Backups encriptados
[ ] Logs monitoreados diariamente
[ ] Base de datos de test eliminada
[ ] Usuarios de BD innecesarios eliminados
[ ] Input validation en aplicación
[ ] Prepared statements en queries SQL
[ ] Certificado SSL/TLS instalado (futuro)
[ ] VPN configurado para acceso remoto
[ ] Auditoría de cambios activada
[ ] Monitoreo de integridad (AIDE)
```

---

## 8. EVIDENCIAS DE EJECUCIÓN

### 8.1 Evidencias de Instalación

Para demostrar que la aplicación se ejecuta correctamente en el entorno, se deben proporcionar evidencias de:

#### **1. Sistema Operativo Funcionando**

**Captura requerida:**
```bash
$ uname -a
Linux modvalley-server 5.15.0-56-generic #62-Ubuntu SMP Tue Nov 22 13:00:02 UTC 2022 x86_64 x86_64 x86_64 GNU/Linux

$ lsb_release -a
Distributor ID: Ubuntu
Release: 22.04
Codename: jammy
```

**Archivo:** `evidencias/01_sistema_operativo.txt`

---

#### **2. Java Instalado y Funcionando**

**Captura requerida:**
```bash
$ java -version
openjdk version "11.0.16" 2022-07-19
OpenJDK Runtime Environment (build 11.0.16+8-post-Ubuntu-0ubuntu122.04)
OpenJDK 64-Bit Server VM (build 11.0.16+8-post-Ubuntu-0ubuntu122.04, mixed mode, sharing)

$ which java
/usr/bin/java

$ echo $JAVA_HOME
/usr/lib/jvm/java-11-openjdk-amd64
```

**Archivo:** `evidencias/02_java_version.txt`

---

#### **3. MySQL Instalado y Ejecutándose**

**Captura requerida:**
```bash
$ sudo systemctl status mysql
● mysql.service - MySQL Community Server
     Loaded: loaded (/lib/systemd/system/mysql.service; enabled; vendor preset: enabled)
     Active: active (running) since Mon 2024-05-01 10:30:45 UTC; 2 days ago

$ mysql --version
mysql  Ver 8.0.32-0ubuntu0.22.04.1 for Linux on x86_64

$ mysql -u modvalley_user -p -e "SELECT VERSION();"
+--+
| VERSION() |
+--+
| 8.0.32    |
+--+
```

**Archivo:** `evidencias/03_mysql_status.txt`

---

#### **4. Base de Datos Creada**

**Captura requerida:**
```bash
$ mysql -u modvalley_user -p -e "SHOW DATABASES;"
+--------------------+
| Database           |
+--------------------+
| information_schema |
| modvalley          |
| modvalley_backup   |
+--------------------+

$ mysql -u modvalley_user -p modvalley -e "SHOW TABLES;"
+---------------------+
| Tables_in_modvalley |
+---------------------+
| USUARIO             |
| VIDEOJUEGO          |
| CATEGORIA           |
| RECURSO             |
| COMENTARIO          |
| VALORACION          |
+---------------------+

$ mysql -u modvalley_user -p modvalley -e "SELECT COUNT(*) as total_usuarios FROM USUARIO;"
+----------------+
| total_usuarios |
+----------------+
|     12         |
+----------------+
```

**Archivo:** `evidencias/04_base_datos.sql`

---

#### **5. Aplicación Java Compilada**

**Captura requerida:**
```bash
$ ls -la /opt/modvalley/app/bin/com/modvalley/
drwxr-xr-x modvalley_app modvalley 4096 May  1 10:45 .
drwxr-xr-x modvalley_app modvalley 4096 May  1 10:45 ..
-rw-r--r-- modvalley_app modvalley 2048 May  1 10:45 Main.class
drwxr-xr-x modvalley_app modvalley 4096 May  1 10:45 config
drwxr-xr-x modvalley_app modvalley 4096 May  1 10:45 controller
drwxr-xr-x modvalley_app modvalley 4096 May  1 10:45 dao
drwxr-xr-x modvalley_app modvalley 4096 May  1 10:45 model
drwxr-xr-x modvalley_app modvalley 4096 May  1 10:45 view

$ cd /opt/modvalley/app && javac -d bin -cp lib/* src/com/modvalley/**/*.java
(sin errores = compilación exitosa)
```

**Archivo:** `evidencias/05_compilacion_java.txt`

---

#### **6. Aplicación Java Ejecutándose**

**Captura requerida (screenshot):**

```
╔════════════════════════════════════════════════╗
║       BIENVENIDO A MODVALLEY                   ║
║   Gestor de Recursos para Videojuegos          ║
╚════════════════════════════════════════════════╝

Por favor, selecciona una opción:

1. Iniciar Sesión
2. Crear Cuenta Nueva
3. Salir

Selecciona una opción: █
```

**Captura del menú:** `evidencias/06_app_menu_principal.png`

**Captura del login:** `evidencias/07_app_login.png`

**Captura del catálogo:** `evidencias/08_app_catalogo.png`

---

#### **7. Interfaz Web Funcionando**

**Captura en navegador:**

URL: `http://modvalley.local/login.php`

**Capturas requeridas:**
- Página de login: `evidencias/09_web_login.png`
- Catálogo: `evidencias/10_web_catalogo.png`
- Perfil: `evidencias/11_web_perfil.png`
- Gestión: `evidencias/12_web_gestion.png`

---

#### **8. Apache y PHP Funcionando**

**Captura requerida:**
```bash
$ sudo systemctl status apache2
● apache2.service - The Apache HTTP Server
     Loaded: loaded (/lib/systemd/system/apache2.service; enabled; vendor preset: enabled)
     Active: active (running) since Mon 2024-05-01 10:35:20 UTC; 2 days ago

$ php --version
PHP 8.1.12-1+0~20221102.11+debian~bullseye+sury+1 (cli) (built: Nov  2 2022 14:02:34) (Zend Engine v4.1.12)

$ curl -I http://modvalley.local/login.php
HTTP/1.1 200 OK
Content-Type: text/html; charset=UTF-8
Content-Length: 3245
Server: Apache/2.4.48 (Ubuntu)
```

**Archivo:** `evidencias/13_apache_php_status.txt`

---

#### **9. Servicios Activos**

**Captura requerida:**
```bash
$ sudo systemctl status mysql apache2 | grep -E "Active|Status"
● mysql.service - MySQL Community Server
     Active: active (running) since Mon 2024-05-01 10:30:45 UTC; 2 days ago

● apache2.service - The Apache HTTP Server
     Active: active (running) since Mon 2024-05-01 10:35:20 UTC; 2 days ago

$ netstat -tulpn | grep LISTEN
tcp  0  0 127.0.0.1:3306      0.0.0.0:*     LISTEN      1234/mysqld
tcp  0  0 0.0.0.0:80          0.0.0.0:*     LISTEN      5678/apache2
tcp  0  0 :::22               :::*          LISTEN      910/sshd
```

**Archivo:** `evidencias/14_servicios_activos.txt`

---

#### **10. Acceso a Base de Datos desde Aplicación**

**Captura requerida (log de aplicación):**
```
[2024-05-01 11:30:45] INFO: Conectando a base de datos...
[2024-05-01 11:30:45] INFO: Conexión exitosa a modvalley@localhost:3306
[2024-05-01 11:30:46] INFO: Consultando usuarios...
[2024-05-01 11:30:46] INFO: 12 usuarios cargados
[2024-05-01 11:30:47] INFO: Usuario 'admin' logueado correctamente
[2024-05-01 11:30:48] INFO: Catálogo cargado con 45 recursos
```

**Archivo:** `evidencias/15_logs_aplicacion.log`

---

### 8.2 Estructura de Carpeta Evidencias

```
evidencias/
├── 01_sistema_operativo.txt
├── 02_java_version.txt
├── 03_mysql_status.txt
├── 04_base_datos.sql
├── 05_compilacion_java.txt
├── 06_app_menu_principal.png
├── 07_app_login.png
├── 08_app_catalogo.png
├── 09_web_login.png
├── 10_web_catalogo.png
├── 11_web_perfil.png
├── 12_web_gestion.png
├── 13_apache_php_status.txt
├── 14_servicios_activos.txt
├── 15_logs_aplicacion.log
└── README.md (índice de evidencias)
```

---

### 8.3 Criterios de Aceptación

Para que la aplicación se considere **correctamente instalada y funcionando**:

✅ **Criterio 1: Sistema Operativo**
- [ ] Ubuntu Server 22.04 LTS ejecutándose
- [ ] Kernel actualizado (5.15+)
- [ ] Sistema accesible vía SSH

✅ **Criterio 2: Dependencias Instaladas**
- [ ] Java 11+ instalado y $JAVA_HOME configurado
- [ ] MySQL Server 8.0+ instalado y ejecutándose
- [ ] Apache2 instalado y ejecutándose
- [ ] PHP 8.1+ instalado y funcionando

✅ **Criterio 3: Base de Datos**
- [ ] Base de datos `modvalley` creada
- [ ] Todas las 6 tablas creadas correctamente
- [ ] Datos iniciales insertados
- [ ] Usuario `modvalley_user` con permisos correctos

✅ **Criterio 4: Aplicación Java**
- [ ] Código fuente compilado sin errores
- [ ] Archivos .class en `/opt/modvalley/app/bin/`
- [ ] Ejecutable arranca correctamente
- [ ] Menú principal visible
- [ ] Login funciona
- [ ] Base de datos conecta exitosamente

✅ **Criterio 5: Interfaz Web**
- [ ] Archivos PHP en `/var/www/modvalley/`
- [ ] Accesible en `http://modvalley.local`
- [ ] Páginas cargan sin errores
- [ ] Interacción con BD funciona
- [ ] Estilos CSS aplican correctamente

✅ **Criterio 6: Seguridad**
- [ ] Firewall (UFW) habilitado
- [ ] Contraseñas fuertes configuradas
- [ ] Permisos de archivos correctos
- [ ] Backup diario funcionando

✅ **Criterio 7: Disponibilidad**
- [ ] Todos los servicios inician automáticamente
- [ ] Logs se generan correctamente
- [ ] Sistema responde a múltiples conexiones
- [ ] No hay errores en logs de eventos

---

## 📊 RESUMEN EJECUTIVO

| Aspecto | Especificación |
|--------|----------------|
| **Sistema** | Servidor Local (Producción) / PC Usuario (Desarrollo) |
| **SO** | Ubuntu Server 22.04 LTS |
| **CPU** | Intel i5+ (mínimo), Xeon (producción) |
| **RAM** | 4 GB (desarrollo), 8-16 GB (producción) |
| **Almacenamiento** | 256 GB (desarrollo), 500 GB - 2 TB (producción) |
| **Java** | OpenJDK 11+ |
| **BD** | MySQL 8.0+ |
| **Web** | Apache 2.4+ con PHP 8.1+ |
| **Mantenimiento** | Diario (backup), Semanal (revisión), Mensual (actualización) |
| **Disponibilidad** | 24/7 con redundancia y backups |
| **Seguridad** | Firewall, SSL/TLS (futuro), Auditoría, Backups encriptados |

---

## 🔚 CONCLUSIÓN

El entorno de ejecución de **ModValley** está diseñado para ser:

- **Escalable:** Puede crecer desde desarrollo local hasta producción distribuida
- **Seguro:** Implementa prácticas recomendadas de seguridad
- **Confiable:** Con backups automáticos y monitoreo continuo
- **Mantenible:** Procedimientos claros y documentados
- **Accesible:** Instalación paso a paso que puede seguir cualquier técnico

El sistema está listo para entrar en producción y servir a múltiples usuarios simultáneamente con garantía de disponibilidad y seguridad de datos.

---

**Documento preparado por:** Adrián Rangel  
**Módulo:** Sistemas Informáticos (0483)  
**Fecha de creación:** Mayo 2026  
**Última actualización:** Mayo 2026  
**Versión del documento:** 1.0

---

**Aprobado por:**  
- [ ] Administrador de sistemas
- [ ] Responsable de seguridad
- [ ] Responsable de operaciones

**Próxima revisión:** Agosto 2026
