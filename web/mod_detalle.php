<?php
session_start();
include 'Config/db.php';

if (!isset($_GET['id'])) {
    echo "ID de mod no proporcionado.";
    exit;
}

$id_recurso = intval($_GET['id']);
$id_usuario_actual = isset($_SESSION['usuario_logueado']) ? $_SESSION['usuario_logueado']['id_usuario'] : null;

// Obtener información del mod
$sql_mod = "SELECT r.*, u.nickname as autor, u.foto_perfil, v.nombre_juego,
            (SELECT AVG(puntuacion) FROM VALORACION v2 WHERE v2.id_recurso = r.id_recurso) as media_valoracion
            FROM RECURSO r 
            JOIN USUARIO u ON r.id_usuario = u.id_usuario 
            JOIN VIDEOJUEGO v ON r.id_videojuego = v.id_videojuego
            WHERE r.id_recurso = ?";
$stmt = $conn->prepare($sql_mod);
$stmt->bind_param("i", $id_recurso);
$stmt->execute();
$mod = $stmt->get_result()->fetch_assoc();

if (!$mod) {
    echo "Mod no encontrado.";
    exit;
}

// Obtener comentarios
$sql_comentarios = "SELECT c.*, u.nickname, u.foto_perfil, val.puntuacion 
                    FROM COMENTARIO c 
                    JOIN USUARIO u ON c.id_usuario = u.id_usuario 
                    LEFT JOIN VALORACION val ON (val.id_recurso = c.id_recurso AND val.id_usuario = c.id_usuario)
                    WHERE c.id_recurso = ? 
                    ORDER BY c.fecha DESC";
$stmt_com = $conn->prepare($sql_comentarios);
$stmt_com->bind_param("i", $id_recurso);
$stmt_com->execute();
$comentarios = $stmt_com->get_result()->fetch_all(MYSQLI_ASSOC);

// Obtener valoración del usuario actual si existe
$valoracion_usuario = null;
if ($id_usuario_actual) {
    $sql_val = "SELECT puntuacion FROM VALORACION WHERE id_usuario = ? AND id_recurso = ?";
    $stmt_val = $conn->prepare($sql_val);
    $stmt_val->bind_param("ii", $id_usuario_actual, $id_recurso);
    $stmt_val->execute();
    $res_val = $stmt_val->get_result();
    if ($res_val->num_rows > 0) {
        $valoracion_usuario = $res_val->fetch_assoc()['puntuacion'];
    }
}

$foto_autor = !empty($mod['foto_perfil']) ? $mod['foto_perfil'] : 'img/FotoPerfilPredeterminada.png';
$media = $mod['media_valoracion'] ? number_format($mod['media_valoracion'], 1) : '0.0';
?>

<div class="mod-detalle-wrapper">
    <button class="btn-back" onclick="volverAlCatalogo()">
        <i class="fa-solid fa-arrow-left"></i> Volver al Catálogo
    </button>

    <div class="mod-detalle-header">
        <div class="mod-header-main">
            <div class="mod-game-pill"><?php echo htmlspecialchars($mod['nombre_juego']); ?></div>
            <h1><?php echo htmlspecialchars($mod['nombre_rec']); ?></h1>
            <div class="mod-meta">
                <span class="mod-version">v<?php echo htmlspecialchars($mod['version']); ?></span>
                <span class="mod-author-info">
                    <img src="<?php echo htmlspecialchars($foto_autor); ?>" alt="Autor" class="mini-avatar">
                    por <strong><?php echo htmlspecialchars($mod['autor']); ?></strong>
                </span>
                <span class="mod-date"><i class="fa-solid fa-calendar"></i> <?php echo date("d M, Y", strtotime($mod['fecha_subida'])); ?></span>
            </div>
        </div>
        <div class="mod-header-stats">
            <div class="stat-big">
                <i class="fa-solid fa-star"></i>
                <span><?php echo $media; ?></span>
            </div>
            <div class="stat-big">
                <i class="fa-solid fa-download"></i>
                <span id="detalle-descargas"><?php echo $mod['num_descargas']; ?></span>
            </div>
        </div>
    </div>

    <div class="mod-detalle-grid">
        <section class="mod-info-principal">
            <div class="glass-section">
                <h3>Descripción</h3>
                <p class="mod-description-text">
                    <?php echo nl2br(htmlspecialchars($mod['descripcion'])); ?>
                </p>
            </div>

            <div class="glass-section">
                <h3>Comentarios (<?php echo count($comentarios); ?>)</h3>
                
                <?php if ($id_usuario_actual): ?>
                    <form id="form-comentario" class="comentario-form">
                        <input type="hidden" name="id_recurso" value="<?php echo $id_recurso; ?>">
                        <div class="rating-input">
                            <span>Tu valoración:</span>
                            <div class="stars-selector">
                                <?php for($i=1; $i<=5; $i++): ?>
                                    <i class="fa-<?php echo ($valoracion_usuario && $i <= $valoracion_usuario) ? 'solid' : 'regular'; ?> fa-star star-opt" data-value="<?php echo $i; ?>"></i>
                                <?php endfor; ?>
                                <input type="hidden" name="puntuacion" id="puntuacion-input" value="<?php echo $valoracion_usuario ?? 0; ?>">
                            </div>
                        </div>
                        <div class="textarea-wrapper">
                            <textarea name="comentario" placeholder="Escribe un comentario..." required></textarea>
                            <button type="submit" class="btn-send-comment">
                                <i class="fa-solid fa-paper-plane"></i>
                            </button>
                        </div>
                    </form>
                <?php else: ?>
                    <p class="login-msg">Debes <a href="login.php">iniciar sesión</a> para comentar y valorar.</p>
                <?php endif; ?>

                <div class="comentarios-lista">
                            <?php if (empty($comentarios)): ?>
                                <p>No hay comentarios aún.</p>
                            <?php else: ?>
                                <?php foreach ($comentarios as $com): ?>
                                    <div class="comentario-item">
                                        <img src="<?php echo !empty($com['foto_perfil']) ? htmlspecialchars($com['foto_perfil']) : 'img/FotoPerfilPredeterminada.png'; ?>" alt="User" class="mini-avatar">
                                        <div class="com-content">
                                            <div class="com-header">
                                                <strong><?php echo htmlspecialchars($com['nickname']); ?></strong>
                                                
                                                <div class="com-stars">
                                                    <?php 
                                                    $puntos = isset($com['puntuacion']) ? intval($com['puntuacion']) : 0;
                                                    for ($i = 1; $i <= 5; $i++): ?>
                                                        <i class="fa-star <?php echo ($i <= $puntos) ? 'fa-solid star' : 'fa-regular'; ?>"></i>
                                                    <?php endfor; ?>
                                                </div>

                                                <span><?php echo date("d/m/Y H:i", strtotime($com['fecha'])); ?></span>
                                            </div>
                                            <p><?php echo nl2br(htmlspecialchars($com['comentario'])); ?></p>
                                        </div>
                                    </div>
                                <?php endforeach; ?>
                            <?php endif; ?>
                        </div>
                    </div>
                </section>

        <aside class="mod-info-lateral">
            <div class="glass-section">
                <button class="btn-download-full" onclick="descargarMod(<?php echo $id_recurso; ?>)">
                    <i class="fa-solid fa-download"></i> Descargar Mod
                </button>
                <div class="file-info">
                    <p><i class="fa-solid fa-file-zipper"></i> Formato: .zip</p>
                    <p><i class="fa-solid fa-shield-halved"></i> Verificado por ModValley</p>
                </div>
            </div>
        </aside>
    </div>
</div>