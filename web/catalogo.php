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
    $sql_mods = "SELECT r.*, u.nickname as autor, v.nombre_juego,
                 (SELECT AVG(puntuacion) FROM VALORACION v2 WHERE v2.id_recurso = r.id_recurso) as media_valoracion
                 FROM RECURSO r 
                 JOIN USUARIO u ON r.id_usuario = u.id_usuario 
                 JOIN VIDEOJUEGO v ON r.id_videojuego = v.id_videojuego
                 WHERE r.id_videojuego = ?
                 ORDER BY r.num_descargas DESC";
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

$vista = $id_juego_sel ? 'mods' : 'juegos';
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
    <link rel="stylesheet" href="css/mod_detalle.css">
</head>
<body>
    <canvas id="lienzo-fondo"></canvas>
    
    <header class="header">
        <nav class="nav">
            <ul>
                <li><a href="index.php"><i class="fa-solid fa-home"></i> Inicio</a></li> 
                <li><a href="catalogo.php" class="active"><i class="fa-solid fa-book"></i> Catálogo</a></li>
                <li><a href="gestion.php"><i class="fa-solid fa-cloud-arrow-up"></i> Gestion</a></li>
                <li><a href="perfil.php"><i class="fa-solid fa-user"></i> Perfil</a></li>
                <li><a href="php/logout.php"><i class="fa-solid fa-right-from-bracket"></i></a></li>
            </ul>
        </nav>
    </header>

    <main class="catalogo-wrapper">

        <!-- ASIDE: Filtros + Búsqueda -->
        <aside class="catalogo-aside">
            <div class="aside-section">
                <h3 class="aside-title"><i class="fa-solid fa-magnifying-glass"></i> Buscar</h3>
                <div class="search-bar">
                    <input type="text" placeholder="Buscar mods..." id="search-input">
                </div>
            </div>

            <div class="aside-section">
                <h3 class="aside-title"><i class="fa-solid fa-gamepad"></i> Juegos</h3>
                <nav class="lista-juegos">
                    <a href="catalogo.php"
                       class="juego-item <?php echo !$id_juego_sel ? 'active' : ''; ?>"
                       data-juego="">
                        <i class="fa-solid fa-fire-flame-curved"></i>
                        <span>Todos los Juegos</span>
                    </a>
                    <?php foreach ($juegos as $juego): ?>
                        <a href="catalogo.php?juego=<?php echo $juego['id_videojuego']; ?>"
                           class="juego-item <?php echo $id_juego_sel == $juego['id_videojuego'] ? 'active' : ''; ?>"
                           data-juego="<?php echo $juego['id_videojuego']; ?>"
                           data-nombre="<?php echo htmlspecialchars($juego['nombre_juego']); ?>">
                            <i class="fa-solid fa-chevron-right"></i>
                            <span><?php echo htmlspecialchars($juego['nombre_juego']); ?></span>
                        </a>
                    <?php endforeach; ?>
                </nav>
            </div>
        </aside>

        <!-- CONTENIDO PRINCIPAL -->
        <section class="catalogo-content">

            <!-- Título encima del grid -->
            <div class="content-header">
                <h2 class="content-title">
                    <?php if ($id_juego_sel): ?>
                        <span class="title-label">Mods para</span>
                        <?php echo htmlspecialchars($nombre_juego_sel); ?>
                    <?php else: ?>
                        <span class="title-label">Explorar</span>
                        Lo más popular
                    <?php endif; ?>
                </h2>
                <span class="content-count">
                    <?php echo count($mods); ?> resultado<?php echo count($mods) !== 1 ? 's' : ''; ?>
                </span>
            </div>

            <!-- GRID según vista -->
            <?php if ($vista === 'juegos'): ?>
                <!-- Vista: todos los juegos → 1 columna -->
                <div class="juegos-grid" id="mods-grid">
                    <?php if (empty($mods)): ?>
                        <div class="empty-state">
                            <i class="fa-solid fa-ghost"></i>
                            <p>No hay mods disponibles en este momento.</p>
                        </div>
                    <?php else: ?>
                        <?php foreach ($mods as $mod): ?>
                            <div class="mod-row-card">
                                <div class="mod-row-info">
                                    <div class="mod-row-meta">
                                        <span class="mod-game-label"><?php echo htmlspecialchars($mod['nombre_juego']); ?></span>
                                        <span class="mod-author">por <?php echo htmlspecialchars($mod['autor']); ?></span>
                                    </div>
                                    <h3><?php echo htmlspecialchars($mod['nombre_rec']); ?></h3>
                                    <p class="mod-desc"><?php echo htmlspecialchars($mod['descripcion']); ?></p>
                                </div>
                                <div class="mod-row-aside">
                                    <div class="mod-stats">
                                        <span id="num_descargas-<?php echo $mod['id_recurso']; ?>">
                                            <i class="fa-solid fa-download"></i>
                                            <?php echo $mod['num_descargas']; ?>
                                        </span>
                                        <span id="media_valoracion-<?php echo $mod['id_recurso']; ?>">
                                            <i class="fa-solid fa-star"></i>
                                            <?php echo $mod['media_valoracion'] ? number_format($mod['media_valoracion'], 1) : '0.0'; ?>
                                        </span>
                                    </div>
                                    <button class="btn-view" onclick="verDetallesMod(<?php echo $mod['id_recurso']; ?>)">
                                        <i class="fa-solid fa-eye"></i> Ver
                                    </button>
                                </div>
                            </div>
                        <?php endforeach; ?>
                    <?php endif; ?>
                </div>

            <?php else: ?>
                <!-- Vista: mods de un juego → 4 columnas fijas -->
                <div class="mods-grid" id="mods-grid">
                    <?php if (empty($mods)): ?>
                        <div class="empty-state">
                            <i class="fa-solid fa-ghost"></i>
                            <p>No hay mods para este juego todavía.</p>
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
                                    <div class="mod-card-actions">
                                        <button class="btn-view" onclick="verDetallesMod(<?php echo $mod['id_recurso']; ?>)">
                                            <i class="fa-solid fa-eye"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        <?php endforeach; ?>
                    <?php endif; ?>
                </div>
            <?php endif; ?>

        </section>
    </main>

    <script src="js/lienzo.js"></script>
    <script src="js/catalogo.js"></script>
</body>
</html>
