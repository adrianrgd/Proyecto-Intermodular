-- Modelo Relacional de la base de datos ModValley

-- TABLAS NO DEPENDIENTES DE NADIE

USUARIO (id_usuario, nickname, nombre, email, fecha_registro, biografia, foto_perfil)
VIDEOJUEGO (id_juego, nombre_juego, genero, plataforma)
CATEGORIA (id_categoria, nombre_cat)

-- TABLA PRINCIPAL

RECURSO (PK id_recurso, nombre_rec, descripcion, version, num_descargas, fecha_subida, FK id_usuario, FK id_juego, FK id_categoria)

-- TABLAS DE INTERACCION

COMENTARIO (PK id_comentario, comentario, fecha, FK ID_usuario, FK id_recurso)
VALORACION (PK id_valoracion, puntuacion, FK id_usuario, FK id_recurso)

