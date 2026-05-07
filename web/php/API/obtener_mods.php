<?php
session_start();
include '../../Config/db.php';

$id_juego_sel = isset($_GET['juego']) && $_GET['juego'] !== '' ? intval($_GET['juego']) : null;
$mods = [];

if ($id_juego_sel) {
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
    $mods = $stmt->get_result()->fetch_all(MYSQLI_ASSOC);
} else {
    $sql_mods = "SELECT r.*, u.nickname as autor, v.nombre_juego,
                 (SELECT AVG(puntuacion) FROM VALORACION v2 WHERE v2.id_recurso = r.id_recurso) as media_valoracion
                 FROM RECURSO r 
                 JOIN USUARIO u ON r.id_usuario = u.id_usuario 
                 JOIN VIDEOJUEGO v ON r.id_videojuego = v.id_videojuego
                 ORDER BY r.num_descargas DESC LIMIT 12";
    $res = $conn->query($sql_mods);
    $mods = $res ? $res->fetch_all(MYSQLI_ASSOC) : [];
}

if (empty($mods)) {
    echo '<div class="empty-state">
            <i class="fa-solid fa-ghost"></i>
            <p>No hay mods disponibles en este momento.</p>
          </div>';
    exit;
}

// ── Vista lista (todos los juegos) → mod-row-card ─────────────
if (!$id_juego_sel) {
    foreach ($mods as $mod) {
        $media = $mod['media_valoracion'] ? number_format($mod['media_valoracion'], 1) : '0.0';
        echo '
        <div class="mod-row-card">
            <div class="mod-row-info">
                <div class="mod-row-meta">
                    <span class="mod-game-label">'.htmlspecialchars($mod['nombre_juego']).'</span>
                    <span class="mod-author">por '.htmlspecialchars($mod['autor']).'</span>
                </div>
                <h3>'.htmlspecialchars($mod['nombre_rec']).'</h3>
                <p class="mod-desc">'.htmlspecialchars($mod['descripcion']).'</p>
            </div>
            <div class="mod-row-aside">
                <div class="mod-stats">
                    <span id="num_descargas-'.$mod['id_recurso'].'">
                        <i class="fa-solid fa-download"></i> '.$mod['num_descargas'].'
                    </span>
                    <span id="media_valoracion-'.$mod['id_recurso'].'">
                        <i class="fa-solid fa-star"></i> '.$media.'
                    </span>
                </div>
                <button class="btn-view" onclick="verDetallesMod('.$mod['id_recurso'].')">
                    <i class="fa-solid fa-eye"></i> Ver
                </button>
            </div>
        </div>';
    }

// ── Vista grid (juego concreto) → mod-card ────────────────────
} else {
    foreach ($mods as $mod) {
        $media = $mod['media_valoracion'] ? number_format($mod['media_valoracion'], 1) : '0.0';
        echo '
        <div class="mod-card">
            <div class="mod-card-header">
                <span class="mod-game-label">'.htmlspecialchars($mod['nombre_juego']).'</span>
                <span class="mod-author">por '.htmlspecialchars($mod['autor']).'</span>
            </div>
            <h3>'.htmlspecialchars($mod['nombre_rec']).'</h3>
            <p class="mod-desc">'.htmlspecialchars($mod['descripcion']).'</p>
            <div class="mod-card-footer">
                <div class="mod-stats">
                    <span id="num_descargas-'.$mod['id_recurso'].'">
                        <i class="fa-solid fa-download"></i> '.$mod['num_descargas'].'
                    </span>
                    <span id="media_valoracion-'.$mod['id_recurso'].'">
                        <i class="fa-solid fa-star"></i> '.$media.'
                    </span>
                </div>
                <div class="mod-card-actions">
                    <button class="btn-view" onclick="verDetallesMod('.$mod['id_recurso'].')">
                        <i class="fa-solid fa-eye"></i>
                    </button>
                </div>
            </div>
        </div>';
    }
}
?>
