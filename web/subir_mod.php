<?php
error_reporting(E_ALL);
ini_set('display_errors', 1);
session_start();
require_once 'Config/db.php';

// Verificación de sesión
if (!isset($_SESSION['usuario_logueado'])) {
    header('Location: login.php');
    exit();
}

$mensaje = '';
$tipo_mensaje = '';

if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['titulo'])) {
    // 1. Recoger datos del formulario
    $titulo = $conn->real_escape_string(trim($_POST['titulo']));
    $descripcion = $conn->real_escape_string(trim($_POST['descripcion']));
    $version = $conn->real_escape_string(trim($_POST['version'] ?? '1.0'));
    $categoria_id = (int)$_POST['categoria'];
    $juego_id = (int)$_POST['juego'];
    $usuario_id = $_SESSION['usuario_logueado']['id_usuario'];
    
    /* Manejo de subida de mod con archivo (Desactivado para el proyecto actual)
    if (!isset($_FILES['archivo']) || $_FILES['archivo']['error'] != 0) {
        $mensaje = 'Por favor selecciona un archivo .zip válido.';
        $tipo_mensaje = 'error';
    } else {
        $nombre_archivo = $_FILES['archivo']['name'];
        $ext = strtolower(pathinfo($nombre_archivo, PATHINFO_EXTENSION));
        
        if ($ext == 'zip') {
            $directorio = 'uploads/mods/';
            if (!is_dir($directorio)) mkdir($directorio, 0755, true);
            
            $nombre_unico = time() . '_' . basename($nombre_archivo);
            $ruta_archivo = $directorio . $nombre_unico;
            
            if (move_uploaded_file($_FILES['archivo']['tmp_name'], $ruta_archivo)) {*/
    $query = "INSERT INTO RECURSO (nombre_rec, descripcion, version, id_usuario, id_videojuego, id_categoria) 
              VALUES ('$titulo', '$descripcion', '$version', $usuario_id, $juego_id, $categoria_id)";
    
    if ($conn->query($query)) {
        $mensaje = '¡Mod publicado con éxito en el catálogo!';
        $tipo_mensaje = 'success';
    } else {
        $mensaje = 'Error al guardar: ' . $conn->error;
        $tipo_mensaje = 'error';
    }
}

// Carga de selects[cite: 12]
$categorias = $conn->query("SELECT id_categoria AS id, nombre_cat AS nombre FROM CATEGORIA")->fetch_all(MYSQLI_ASSOC);
$juegos = $conn->query("SELECT id_videojuego AS id, nombre_juego FROM VIDEOJUEGO")->fetch_all(MYSQLI_ASSOC);
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Subir Mod - ModValley</title>
    <link rel="stylesheet" href="css/mod_detalle.css"> <!-- Usamos el CSS de detalle -->
    <link rel="stylesheet" href="css/mods_gestion.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Lexend:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>
    <canvas id="lienzo-fondo"></canvas>

    <div class="mod-detalle-wrapper"> <!-- Contenedor estilo detalle -->
        <button class="btn-back" onclick="window.location.href='gestion.php'">
            <i class="fa-solid fa-arrow-left"></i> Volver a Gestión
        </button>

        <div class="mod-detalle-header"> <!-- Cabecera estilo detalle -->
            <div class="mod-header-main">
                <div class="mod-game-pill">Nueva Publicación</div>
                <h1>Subir Nuevo Mod</h1>
                <p style="color: rgba(255,255,255,0.6)">Tu contenido aparecerá en el catálogo tras la subida.</p>
            </div>
            <div class="mod-header-stats">
                <div class="stat-big"><i class="fa-solid fa-file-export"></i></div>
            </div>
        </div>

        <?php if ($mensaje): ?>
            <div class="mensaje <?php echo $tipo_mensaje; ?>" style="margin-bottom: 20px; padding: 15px; border-radius: 10px; background: rgba(0,0,0,0.3); color: <?php echo $tipo_mensaje == 'success' ? '#caffc3' : '#ff6b6b'; ?>;">
                <?php echo $mensaje; ?>
            </div>
        <?php endif; ?>

        <div class="mod-detalle-grid"> <!-- Rejilla de detalle -->
            <section class="mod-info-principal">
                <div class="glass-section">
                    <h3><i class="fa-solid fa-pen-to-square"></i> Información del Mod</h3>
                    <form action="subir_mod.php" method="POST" enctype="multipart/form-data" class="comentario-form">
                        <div class="form-group" style="margin-bottom: 15px;">
                            <label style="color: #caffc3;">Título:</label>
                            <input type="text" name="titulo" style="width: 100%; background: rgba(0,0,0,0.3); border: 1px solid rgba(255,255,255,0.1); color: white; padding: 10px; border-radius: 8px;" required>
                        </div>
                        <div class="form-group" style="margin-bottom: 15px;">
                            <label style="color: #caffc3;">Descripción:</label>
                            <textarea name="descripcion" style="width: 100%; background: rgba(0,0,0,0.3); border: 1px solid rgba(255,255,255,0.1); color: white; padding: 10px; border-radius: 8px;" rows="5" required></textarea>
                        </div>
                        <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 15px;">
                            <div class="form-group">
                                <label style="color: #caffc3;">Juego:</label>
                                <select name="juego" style="width: 100%; background: #222; color: white; padding: 10px; border-radius: 8px;" required>
                                    <?php foreach ($juegos as $j): ?>
                                        <option value="<?php echo $j['id']; ?>"><?php echo $j['nombre_juego']; ?></option>
                                    <?php endforeach; ?>
                                </select>
                            </div>
                            <div class="form-group">
                                <label style="color: #caffc3;">Categoría:</label>
                                <select name="categoria" style="width: 100%; background: #222; color: white; padding: 10px; border-radius: 8px;" required>
                                    <?php foreach ($categorias as $cat): ?>
                                        <option value="<?php echo $cat['id']; ?>"><?php echo $cat['nombre']; ?></option>
                                    <?php endforeach; ?>
                                </select>
                            </div>
                        </div>
                        <div class="form-group" style="margin-top: 15px;">
                            <label style="color: #caffc3;">Archivo (.zip):</label>
                            <input type="file" name="archivo" accept=".zip" disabled>
                        </div>
                        <button type="submit" class="btn-download-full" style="margin-top: 20px;">
                            <i class="fa-solid fa-cloud-arrow-up"></i> Publicar en ModValley
                        </button>
                    </form> 
                </div>
            </section>

            <aside class="mod-info-lateral">
                <div class="glass-section">
                    <h3>Instrucciones</h3>
                    <div class="file-info">
                        <p><i class="fa-solid fa-circle-check"></i> Formato ZIP requerido.</p>
                        <p><i class="fa-solid fa-circle-check"></i> Maximo 10GB.</p>
                        <p><i class="fa-solid fa-circle-check"></i> Describe bien tu mod!</p>
                    </div>
                </div>
            </aside>
        </div>
    </div>
    <script src="js/lienzo.js"></script>
</body>
</html>