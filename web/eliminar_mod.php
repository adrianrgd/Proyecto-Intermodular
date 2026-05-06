<?php
error_reporting(E_ALL);
ini_set('display_errors', 1);
session_start();
require_once 'Config/db.php';

if (!isset($_SESSION['usuario_logueado'])) {
    header('Location: login.php');
    exit();
}

$usuario_id = $_SESSION['usuario_logueado']['id_usuario'];
$mensaje = '';

if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['mods'])) {
    $mods_a_eliminar = array_map('intval', $_POST['mods']);
    $ids_string = implode(',', $mods_a_eliminar);
    
    // Verificamos propiedad antes de borrar
    $query_delete = "DELETE FROM RECURSO WHERE id_recurso IN ($ids_string) AND id_usuario = $usuario_id";
    if ($conn->query($query_delete)) {
        $mensaje = 'Mods eliminados con éxito.';
    }
}

// Obtener mods del usuario
$mods = $conn->query("SELECT id_recurso AS id, nombre_rec AS titulo, descripcion, fecha_subida FROM RECURSO WHERE id_usuario = $usuario_id ORDER BY fecha_subida DESC")->fetch_all(MYSQLI_ASSOC);
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Eliminar Mods - ModValley</title>
    <link rel="stylesheet" href="css/mod_detalle.css">
    <link rel="stylesheet" href="css/mods_gestion.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Lexend:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>
    <canvas id="lienzo-fondo"></canvas>

    <div class="mod-detalle-wrapper">
        <button class="btn-back" onclick="window.location.href='gestion.php'">
            <i class="fa-solid fa-arrow-left"></i> Volver a Gestión
        </button>

        <div class="mod-detalle-header" style="border-color: rgba(255, 107, 107, 0.3);">
            <div class="mod-header-main">
                <div class="mod-game-pill" style="background: rgba(255,107,107,0.1); color: #ff6b6b;">ELIMINAR PUBLICACIÓN</div>
                <h1>Mis Publicaciones</h1>
                <p style="color: rgba(255,255,255,0.6)">Selecciona los mods que deseas retirar de la plataforma.</p>
            </div>
            <div class="mod-header-stats">
                <div class="stat-big">
                    <i class="fa-solid fa-trash-can" style="color: #ff6b6b;"></i>
                    <span><?php echo count($mods); ?></span>
                </div>
            </div>
        </div>

        <div class="mod-detalle-grid">
            <section class="mod-info-principal">
                <div class="glass-section">
                    <h3>Lista de Contenido</h3>
                    <form action="eliminar_mod.php" method="POST" onsubmit="return confirm('¿Seguro? Esta acción es irreversible.');">
                        <div class="comentarios-list"> <!-- Reutilización de lista de detalle -->
                            <?php if (empty($mods)): ?>
                                <p style="color: #888;">No tienes mods publicados actualmente.</p>
                            <?php else: ?>
                                <?php foreach ($mods as $mod): ?>
                                    <div class="comentario-item" style="align-items: center; gap: 20px;">
                                        <input type="checkbox" name="mods[]" value="<?php echo $mod['id']; ?>" style="width: 20px; height: 20px;">
                                        <div class="com-content">
                                            <div class="com-header">
                                                <strong><?php echo htmlspecialchars($mod['titulo']); ?></strong>
                                                <span>Subido: <?php echo date('d/m/Y', strtotime($mod['fecha_subida'])); ?></span>
                                            </div>
                                            <p style="font-size: 0.85rem;"><?php echo htmlspecialchars(substr($mod['descripcion'], 0, 100)); ?>...</p>
                                        </div>
                                    </div>
                                <?php endforeach; ?>
                                <button type="submit" class="btn-download-full" style="background: linear-gradient(135deg, #ff6b6b, #ee5a52); margin-top: 25px; color: white;">
                                    <i class="fa-solid fa-trash-arrow-up"></i> Eliminar Selección
                                </button>
                            <?php endif; ?>
                        </div>
                    </form>
                </div>
            </section>

            <aside class="mod-info-lateral">
                <div class="glass-section">
                    <h3>Importante</h3>
                    <p style="font-size: 0.9rem; color: #ff6b6b; line-height: 1.4;">
                        Borrar un mod eliminará permanentemente todos sus archivos, valoraciones y comentarios asociados de la base de datos.
                    </p>
                </div>
            </aside>
        </div>
    </div>
    <script src="js/lienzo.js"></script>
</body>
</html>