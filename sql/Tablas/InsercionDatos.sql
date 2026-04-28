-- Insercion de datos en la base de datos ModValley

INSERT INTO USUARIO (nickname, email, fecha_registro, biografia, foto_perfil) VALUES
('adrianrgd', 'adrianrgd@gmail.com', NOW(), null, null),
('Modder67', 'modder67@gmail.com', NOW(), null, null),
('LuisitoComunica', 'luisito@gmail.com', NOW(), null, null),
('ElColetas12', 'elcoletas@gmail.com', NOW(), null, null),
('Paco_Perez', 'pacoperez@gmail.com', NOW(), null, null);

INSERT INTO CATEGORIA (nombre_cat) VALUES
('Texturas'),
('Mods'),
('Mapas'),
('Herramientas');

INSERT INTO VIDEOJUEGO (nombre_juego, genero, plataforma) VALUES
('Minecraft Java', 'Sandbox', 'PC'),
('Grand Theft Auto V', 'Acción', 'PC'),
('Cities Skylines I', 'Simulación', 'PC');


INSERT INTO RECURSO (nombre_rec, descripcion, version, num_descargas, fecha_subida, id_usuario, id_videojuego, id_categoria) VALUES
('Aether', 'Un nuevo mundo en los cielos', '1.12.2', 0, NOW(), 1, 1, 2),
('Sodium', 'Optimizacion y rendimiento al maximo!', '26.1.2', 0, NOW(), 2, 1, 4),
('Xaero s Minimap', 'Minimapa y brújula para explorar el mundo', '26.1.2', 0, NOW(), 2, 1, 4),
('Fusion', 'Uso más recursos y unifica tus texturas!', '1.21.1', 0, NOW(), 2, 1, 1),
('Script Hook V', 'Mejora tu experiencia de juego con este mod!', 'Todas las versiones', 0, NOW(), 3, 2, 4),
('GTA V Remastered', 'Disfrute de un mapa mejorado!', 'Todas las versiones', 0, NOW(), 3, 2, 3),
('Simple Zombies', 'Agrega zombies a tu mundo!', 'Todas las versiones', 0, NOW(), 3, 2, 2),
('Move It', 'Herramienta para mover edificios y objetos', '1.20.1-f1', 0, NOW(), 4, 3, 4),
('Anarchy', 'Lo que quieras donde quieras!', '1.20.1-f1', 0, NOW(), 4, 3, 4),
('Better Industries', 'Mejora tus industrias!', '1.20.1-f1', 0, NOW(), 4, 3, 4), 
('Sunrise', 'Mejora el shader del juego', '1.20.1-f1', 0, NOW(), 4, 3, 1);


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


