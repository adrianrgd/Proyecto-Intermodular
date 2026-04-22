-- Insercion de datos en la base de datos ModValley

INSERT INTO USUARIO (nickname, email, password) VALUES
('Juanito_008', 'juanelcrack@gmail.com', 'juan008'),
('Modder67', 'modder67@gmail.com', 'modder67'),
('LuisitoComunica', 'luisito@gmail.com', 'luisito'),
('ElColetas12', 'elcoletas@gmail.com', 'coletas12'),
('Paco_Perez', 'pacoperez@gmail.com', 'paco123');

INSERT INTO CATEGORIA (nombre_cat) VALUES
('Texturas'),
('Mods'),
('Mapas'),
('Herramientas');

INSERT INTO VIDEOJUEGO (nombre_juego, genero, plataforma) VALUES
('Minecraft Java', 'Sandbox', 'PC'),
('Grand Theft Auto V', 'Acción', 'PC');

INSERT INTO RECURSO (nombre_rec, descripcion, version, id_usuario, id_videojuego, id_categoria) VALUES
('Aether', 'Descripcion Temporal', '1.12.2', 1, 1, 2),
('Sodium', 'Optimizacion y rendimiento al maximo!', '26.1.2', 2, 1, 4),
('Xaero s Minimap', 'Minimapa y brújula para explorar el mundo', '26.1.2', 2, 1, 4),
('Fusion', 'Uso más recursos y unifica tus texturas!', '1.21.1', 2, 1, 1),
('Script Hook V', 'Mejora tu experiencia de juego con este mod!', 'Todas las versiones', 3, 2, 4),
('GTA V Remastered', 'Disfrute de un mapa mejorado!', 'Todas las versiones', 3, 2, 3),
('Simple Zombies', 'Agrega zombies a tu mundo!', 'Todas las versiones', 3, 2, 2);

INSERT INTO DESCARGA (fecha_descarga, id_recurso) VALUES
( NOW(), 1),
( NOW(), 2),
( NOW(), 3),
( NOW(), 4),
( NOW(), 5),
( NOW(), 6),
( NOW(), 7);

INSERT INTO COMENTARIO (comentario, id_usuario, id_recurso) VALUES
('El mejor mod de la historia!', 1, 1),
('La optimizacion es excelente y tiene muchas opciones.', 3, 2),
('El mapa es estupendo!', 2, 3),
('Excelente, ya puedo ver mi juego a mi manera.', 1, 4),
('Se me bugea el menu de vez en cuando, pero va bien.', 4, 5),
('Hay que mejorar algunas cosas, se me peta mucho en muchas zonas.', 2, 6),
('Gracias! Al fin ya no me aburrire del online.', 5, 7);

INSERT INTO VALORACION (puntuacion, id_usuario, id_recurso) VALUES
(5, 1, 1),
(4, 3, 2),
(4, 2, 3),
(5, 1, 4),
(3, 4, 5),
(2, 2, 6),
(5, 5, 7);


