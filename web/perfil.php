<?php 
session_start();
include 'Config/db.php'; 

if (!isset($_SESSION['usuario_logueado'])) {
    header("Location: login.php");
    exit;
}

$user = $_SESSION['usuario_logueado'];
$id_usuario = $user['id_usuario'];

// Refrescar datos del usuario por si hubo cambios
$res_user = $conn->query("SELECT * FROM USUARIO WHERE id_usuario = $id_usuario");
$user = $res_user->fetch_assoc();
$_SESSION['usuario_logueado'] = $user;

// Obtener mods del usuario
$sql_mods = "SELECT r.*, v.nombre_juego 
             FROM RECURSO r 
             JOIN VIDEOJUEGO v ON r.id_videojuego = v.id_videojuego 
             WHERE r.id_usuario = ? 
             ORDER BY r.fecha_subida DESC";
$stmt_mods = $conn->prepare($sql_mods);
$stmt_mods->bind_param("i", $id_usuario);
$stmt_mods->execute();
$res_mods = $stmt_mods->get_result();
$mis_mods = $res_mods->fetch_all(MYSQLI_ASSOC);

// Obtener valoraciones del usuario (incluyendo comentario si existe)
$sql_val = "SELECT v.puntuacion, r.nombre_rec, j.nombre_juego, c.comentario, c.fecha as fecha_comentario
            FROM VALORACION v 
            JOIN RECURSO r ON v.id_recurso = r.id_recurso 
            JOIN VIDEOJUEGO j ON r.id_videojuego = j.id_videojuego 
            LEFT JOIN COMENTARIO c ON (v.id_usuario = c.id_usuario AND v.id_recurso = c.id_recurso)
            WHERE v.id_usuario = ?";
$stmt_val = $conn->prepare($sql_val);
$stmt_val->bind_param("i", $id_usuario);
$stmt_val->execute();
$res_val = $stmt_val->get_result();
$mis_valoraciones = $res_val->fetch_all(MYSQLI_ASSOC);

$foto_perfil = !empty($user['foto_perfil']) ? $user['foto_perfil'] : 'img/FotoPerfilPredeterminada.png';
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil - <?php echo htmlspecialchars($user['nickname']); ?> | ModValley</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lexend:wght@100..900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/perfil.css">
</head>
<body>
    <canvas id="lienzo-fondo"></canvas>
    <header class="header">
        <nav class="nav">
            <ul>
                <li><a href="index.php"><i class="fa-solid fa-home"></i> Inicio</a></li> 
                <li><a href="catalogo.php"><i class="fa-solid fa-book"></i> Catálogo</a></li>
                <li><a href="gestion.php"><i class="fa-solid fa-cloud-arrow-up"></i> Gestion</a></li>
                <li><a href="perfil.php" class="active"><i class="fa-solid fa-user"></i> Perfil</a></li>
                <li><a href="php/logout.php"><i class="fa-solid fa-right-from-bracket"></i></a></li>
            </ul>
        </nav>
    </header>

    <main class="perfil-container">
        <!-- Portada / Banner (Gradiente) -->
        <div class="perfil-banner">
            <div class="banner-gradient"></div>
        </div>


        <!-- Cabecera de Perfil -->
        <div class="perfil-header">
            <div class="header-info-container">
                <div class="avatar-grande">
                    <img src="<?php echo htmlspecialchars($foto_perfil); ?>" alt="Avatar" id="avatar-preview">
                </div>

                <div class="user-main-info">
                    <h1 id="display-nombre"><?php echo !empty($user['nombre']) ? htmlspecialchars($user['nombre']) : htmlspecialchars($user['nickname']); ?></h1>
                    <p class="user-handle">@<span id="display-nickname"><?php echo htmlspecialchars($user['nickname']); ?></span></p>
                </div>

            </div>

            <!-- Navegación Horizontal -->
            <nav class="perfil-nav-tabs">
                <button class="tab-btn active" data-tab="info">Información</button>
                <button class="tab-btn" data-tab="mods">Mods <span><?php echo count($mis_mods); ?></span></button>
                <button class="tab-btn" data-tab="valoraciones">Reseñas</button>
                <button class="tab-btn" data-tab="ajustes">Editar</button>
            </nav>
        </div>

        <div class="perfil-grid">
            <!-- Columna Principal -->
            <section class="perfil-col-principal">
                <!-- INFORMACIÓN -->
                <div class="tab-content active" id="tab-info">
                    <div class="feed-section">
                        <h3>Sobre mí</h3>
                        <p class="bio-texto">
                            <?php echo !empty($user['biografia']) ? nl2br(htmlspecialchars($user['biografia'])) : "Este usuario aún no ha escrito su biografía."; ?>
                        </p>
                    </div>
                </div>

                <!-- MIS MODS -->
                <div class="tab-content" id="tab-mods">
                    <div class="feed-section">
                        <h3>Publicaciones</h3>
                        <?php if (empty($mis_mods)): ?>
                            <div class="empty-state">
                                <i class="fa-solid fa-folder-open"></i>
                                <p>No hay mods publicados.</p>
                            </div>
                        <?php else: ?>
                            <div class="mods-feed">
                                <?php foreach ($mis_mods as $mod): ?>
                                    <div class="mod-item-social">
                                        <div class="mod-main">
                                            <div class="mod-header-meta">
                                                <span class="mod-game-pill"><?php echo htmlspecialchars($mod['nombre_juego']); ?></span>
                                                <span class="mod-date"><?php echo date("d M, Y", strtotime($mod['fecha_subida'])); ?></span>
                                            </div>
                                            <h4><?php echo htmlspecialchars($mod['nombre_rec']); ?></h4>
                                            <p><?php echo htmlspecialchars($mod['descripcion']); ?></p>
                                        </div>
                                        <div class="mod-footer-stats">
                                            <span><i class="fa-solid fa-download"></i> <?php echo $mod['num_descargas']; ?></span>
                                            <span><i class="fa-solid fa-code-branch"></i> v<?php echo htmlspecialchars($mod['version']); ?></span>
                                        </div>
                                    </div>
                                <?php endforeach; ?>
                            </div>
                        <?php endif; ?>
                    </div>
                </div>

                <!-- VALORACIONES -->
                <div class="tab-content" id="tab-valoraciones">
                    <div class="feed-section">
                        <h3>Reseñas recientes</h3>
                        <?php if (empty($mis_valoraciones)): ?>
                            <div class="empty-state">
                                <p>No has valorado nada todavía.</p>
                            </div>
                        <?php else: ?>
                            <div class="valoraciones-feed">
                        <?php foreach ($mis_valoraciones as $val): ?>
                            <div class="valoracion-social-card">
                                <div class="val-header-info">
                                    <h4 class="val-mod-name"><?php echo htmlspecialchars($val['nombre_rec']); ?></h4>
                                    <span class="val-game-name"><?php echo htmlspecialchars($val['nombre_juego']); ?></span>
                                </div>
                                <div class="val-stars">
                                    <?php for($i=1; $i<=5; $i++): ?>
                                        <i class="fa-<?php echo $i <= $val['puntuacion'] ? 'solid' : 'regular'; ?> fa-star"></i>
                                    <?php endfor; ?>
                                </div>
                                <?php if (!empty($val['comentario'])): ?>
                                    <p class="val-comment">"<?php echo htmlspecialchars($val['comentario']); ?>"</p>
                                <?php endif; ?>
                                <span class="val-date"><?php echo !empty($val['fecha_comentario']) ? date("d M, Y", strtotime($val['fecha_comentario'])) : "Recientemente"; ?></span>
                            </div>
                        <?php endforeach; ?>
                    </div>

                        <?php endif; ?>
                    </div>
                </div>

                <!-- AJUSTES -->
                <div class="tab-content" id="tab-ajustes">
                    <div class="feed-section">
                        <h3>Configuración</h3>
                        <form id="form-editar-perfil" class="social-form">
                            <div class="input-social">
                                <label>Nombre público</label>
                                <input type="text" name="nombre" value="<?php echo htmlspecialchars($user['nombre'] ?? ''); ?>" placeholder="Tu nombre real o alias">
                            </div>
                            <div class="input-social">
                                <label>Nickname (@usuario)</label>
                                <input type="text" name="nickname" value="<?php echo htmlspecialchars($user['nickname']); ?>" required>
                            </div>
                            <div class="input-social">
                                <label>Biografía</label>
                                <textarea name="biografia" rows="3"><?php echo htmlspecialchars($user['biografia']); ?></textarea>
                            </div>
                            <div class="input-social">
                                <label>Foto de Perfil</label>
                                <input type="file" name="foto_perfil" accept="image/jpeg,image/png,image/webp">
                            </div>
                            <button type="submit" class="btn-save-social">Guardar cambios</button>
                        </form>


                        <div class="danger-section">
                            <p>¿Quieres cerrar tu cuenta?</p>
                            <button id="btn-eliminar-cuenta" class="btn-link-danger">Eliminar permanentemente</button>
                        </div>
                    </div>
                </div>
            </section>

            <!-- Barra lateral (Estadisticas) -->
            <aside class="perfil-col-lateral">
                <div class="lateral-section">
                    <h4>Información</h4>
                    <ul class="stats-list">
                        <li><i class="fa-solid fa-id-card"></i> ID: #<?php echo $user['id_usuario']; ?></li>
                        <li><i class="fa-solid fa-envelope"></i> <?php echo htmlspecialchars($user['email']); ?></li>
                        <li><i class="fa-solid fa-calendar-days"></i> Se unió el <?php echo date("d/m/Y", strtotime($user['fecha_registro'])); ?></li>
                    </ul>
                </div>
                <div class="lateral-section">
                    <h4>Estadísticas</h4>
                    <div class="mini-stats">
                        <div class="mini-stat-item">
                            <strong><?php echo count($mis_mods); ?></strong>
                            <span>Mods</span>
                        </div>
                        <div class="mini-stat-item">
                            <?php 
                            $total_descargas = array_sum(array_column($mis_mods, 'num_descargas'));
                            ?>
                            <strong><?php echo $total_descargas; ?></strong>
                            <span>Descargas</span>
                        </div>
                    </div>
                </div>
            </aside>
        </div>
    </main>


    <script src="js/lienzo.js"></script>
    <script src="js/perfil.js"></script>
</body>
</html>