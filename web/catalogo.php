<?php 
session_start();
include 'Config/db.php'; 

// Obtener lista de juegos
$sql_juegos = "SELECT * FROM VIDEOJUEGO";
$res_juegos = $conn->query($sql_juegos);
$juegos = $res_juegos->fetch_all(MYSQLI_ASSOC);

// Si hay un juego seleccionado, obtener sus mods
$id_juego_sel = isset($_GET['juego']) ? intval($_GET['juego']) : null;
$mods = [];
$nombre_juego_sel = "";

if ($id_juego_sel) {
    // Consulta para un juego específico: los más recientes primero
    $sql_mods = "SELECT r.*, u.nickname as autor, v.nombre_juego,
                 (SELECT AVG(puntuacion) FROM VALORACION v2 WHERE v2.id_recurso = r.id_recurso) as media_valoracion
                 FROM RECURSO r 
                 JOIN USUARIO u ON r.id_usuario = u.id_usuario 
                 JOIN VIDEOJUEGO v ON r.id_videojuego = v.id_videojuego
                 WHERE r.id_videojuego = ? 
                 ORDER BY r.fecha_subida DESC";
    $stmt = $conn->prepare($sql_mods);
    $stmt->bind_param("i", $id_juego_sel);
    $stmt->execute();
    $res_mods = $stmt->get_result();
    $mods = $res_mods->fetch_all(MYSQLI_ASSOC);

    foreach ($juegos as $j) {
        if ($j['id_videojuego'] == $id_juego_sel) {
            $nombre_juego_sel = $j['nombre_juego'];
            break;
        }
    }
} else {
    // Consulta para "Todos los juegos": mostrar los más descargados de todo el catálogo
    $sql_mods = "SELECT r.*, u.nickname as autor, v.nombre_juego,
                 (SELECT AVG(puntuacion) FROM VALORACION v2 WHERE v2.id_recurso = r.id_recurso) as media_valoracion
                 FROM RECURSO r 
                 JOIN USUARIO u ON r.id_usuario = u.id_usuario 
                 JOIN VIDEOJUEGO v ON r.id_videojuego = v.id_videojuego
                 ORDER BY r.num_descargas DESC LIMIT 12";
    $res_mods = $conn->query($sql_mods);
    if ($res_mods) {
        $mods = $res_mods->fetch_all(MYSQLI_ASSOC);
    }
}

?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catálogo - ModValley</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lexend:wght@100..900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/catalogo.css">
</head>
<body>
    <canvas id="lienzo-fondo"></canvas>
    
    <header class="header">
        <nav class="nav">
            <ul>
                <li><a href="index.php"><i class="fa-solid fa-home"></i> Inicio</a></li> 
                <li><a href="catalogo.php" class="active"><i class="fa-solid fa-book"></i> Catálogo</a></li>
                <li><a href="subir_contenido.html"><i class="fa-solid fa-cloud-arrow-up"></i> Gestion</a></li>
                <li><a href="perfil.php"><i class="fa-solid fa-user"></i> Perfil</a></li>
                <li><a href="php/logout.php"><i class="fa-solid fa-right-from-bracket"></i></a></li>
            </ul>
        </nav>
    </header>

    <main class="catalogo-container">
        <!-- Sidebar de Juegos -->
        <aside class="catalogo-sidebar">
            <h3><i class="fa-solid fa-gamepad"></i> Juegos</h3>
            <div class="lista-juegos">
                <a href="catalogo.php" class="juego-item <?php echo !$id_juego_sel ? 'active' : ''; ?>">
                    Todos los Juegos
                </a>
                <?php foreach ($juegos as $juego): ?>
                    <a href="catalogo.php?juego=<?php echo $juego['id_videojuego']; ?>" 
                       class="juego-item <?php echo $id_juego_sel == $juego['id_videojuego'] ? 'active' : ''; ?>">
                        <?php echo htmlspecialchars($juego['nombre_juego']); ?>
                    </a>
                <?php endforeach; ?>
            </div>
        </aside>

        <!-- Feed de Contenido -->
        <section class="catalogo-feed">
            <div class="feed-header">
                <h2><?php echo $id_juego_sel ? "Mods para " . htmlspecialchars($nombre_juego_sel) : "Los más populares"; ?></h2>

                <div class="search-bar">
                    <i class="fa-solid fa-magnifying-glass"></i>
                    <input type="text" placeholder="Buscar mods..." id="search-input">
                </div>
            </div>

            <div class="mods-grid" id="mods-grid">
                <?php if (empty($mods)): ?>
                    <div class="empty-state">
                        <i class="fa-solid fa-ghost"></i>
                        <p>No hay mods disponibles en este momento.</p>
                    </div>
                <?php else: ?>
                    <?php foreach ($mods as $mod): ?>
                        <div class="mod-card">
                            <div class="mod-card-header">
                                <span class="mod-game-label"><?php echo htmlspecialchars($mod['nombre_juego']); ?></span>
                                <span class="mod-author">por <?php echo htmlspecialchars($mod['autor']); ?></span>
                            </div>

                            <h3><?php echo htmlspecialchars($mod['nombre_rec']); ?></h3>
                            <p class="mod-desc"><?php echo htmlspecialchars($mod['descripcion']); ?></p>
                            <div class="mod-card-footer">
                                <div class="mod-stats">
                                    <span id="num_descargas-<?php echo $mod['id_recurso']; ?>">
                                        <i class="fa-solid fa-download"></i> <?php echo $mod['num_descargas']; ?>
                                    </span>
                                    <span id="media_valoracion-<?php echo $mod['id_recurso']; ?>">
                                        <i class="fa-solid fa-star"></i> <?php echo $mod['media_valoracion'] ? number_format($mod['media_valoracion'], 1) : '0.0'; ?>
                                    </span>
                                </div>
                                <button class="btn-download" onclick="descargarMod(<?php echo $mod['id_recurso']; ?>)">
                                    <i class="fa-solid fa-download"></i>
                                </button>

                            </div>
                        </div>
                    <?php endforeach; ?>
                <?php endif; ?>
            </div>
        </section>
    </main>

    <script src="js/lienzo.js"></script>
    <script src="js/catalogo.js"></script>
</body>
</html>
