DROP DATABASE IF EXISTS ModValley;

CREATE DATABASE IF NOT EXISTS ModValley;
USE ModValley;

-- 1. Tablas No Dependientes
CREATE TABLE USUARIO (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nickname VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    fecha_registro DATE DEFAULT (CURRENT_DATE),
    biografia TEXT,
    foto_perfil VARCHAR(50)
);

CREATE TABLE CATEGORIA (
    id_categoria INT PRIMARY KEY AUTO_INCREMENT,
    nombre_cat VARCHAR(50) NOT NULL
);

CREATE TABLE VIDEOJUEGO (
    id_videojuego INT PRIMARY KEY AUTO_INCREMENT,
    nombre_juego VARCHAR(100) NOT NULL,
    genero VARCHAR(50) NOT NULL,      
    plataforma VARCHAR(50) NOT NULL   
);

-- 2. Tabla Principal
CREATE TABLE RECURSO (
    id_recurso INT PRIMARY KEY AUTO_INCREMENT,
    nombre_rec VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL,
    version VARCHAR(20) NOT NULL,
    num_descargas INT DEFAULT 0,
    fecha_subida DATE DEFAULT (CURRENT_DATE),
    id_usuario INT,
    id_videojuego INT NOT NULL,
    id_categoria INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario),
    FOREIGN KEY (id_videojuego) REFERENCES VIDEOJUEGO(id_videojuego),
    FOREIGN KEY (id_categoria) REFERENCES CATEGORIA(id_categoria)
);

-- 3. Tablas de Interacción

CREATE TABLE COMENTARIO (
    id_comentario INT PRIMARY KEY AUTO_INCREMENT,
    comentario TEXT NOT NULL,
    fecha DATE DEFAULT (CURRENT_DATE),
    id_usuario INT NOT NULL,
    id_recurso INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario),
    FOREIGN KEY (id_recurso) REFERENCES RECURSO(id_recurso)
);

CREATE TABLE VALORACION (
    id_valoracion INT PRIMARY KEY AUTO_INCREMENT,
    puntuacion INT NOT NULL CHECK (puntuacion BETWEEN 1 AND 5),
    id_usuario INT NOT NULL,
    id_recurso INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario),
    FOREIGN KEY (id_recurso) REFERENCES RECURSO(id_recurso)
);