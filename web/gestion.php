<?php
session_start();

// Verificar que el usuario esté autenticado
if (!isset($_SESSION['user_id']) && !isset($_SESSION['usuario_logueado'])) {
    header('Location: login.php');
    exit();
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión - ModValley</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lexend:wght@100..900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/gestion.css">
</head>
<body>
    <canvas id="lienzo-fondo"></canvas>

    <header class="header">
        <nav class="nav">
            <ul>
                <li><a href="index.php"><i class="fa-solid fa-home"></i> Inicio</a></li>
                <li><a href="catalogo.php"><i class="fa-solid fa-book"></i> Catálogo</a></li>
                <li><a href="gestion.php" class="active"><i class="fa-solid fa-cloud-arrow-up"></i> Gestion</a></li>
                <li><a href="perfil.php"><i class="fa-solid fa-user"></i> Perfil</a></li>
                <li><a href="php/logout.php"><i class="fa-solid fa-right-from-bracket"></i></a></li>
            </ul>
        </nav>
    </header>

    <main class="catalogo-container full-width">
        <section class="gestion-feed">
            <div class="gestion-header">
                <h1><i class="fa-solid fa-database"></i> Panel de Gestión</h1>
                <p>Administra tu contenido y contribuye a la comunidad</p>
            </div>

            <div class="gestion-contenido">
                <a href="subir_mod.php" class="gestion-item">
                    <div class="item-icon-wrapper">
                        <i class="fa-solid fa-plus"></i>
                    </div>
                    <div class="item-text">
                        <span>Subir Nuevo Mod</span>
                        <p>Publica tus mods y compártelos!</p>
                    </div>
                </a>

                <a href="eliminar_mod.php" class="gestion-item danger">
                    <div class="item-icon-wrapper">
                        <i class="fa-solid fa-trash"></i>
                    </div>
                    <div class="item-text">
                        <span>Eliminar Mod</span>
                        <p>Retira tus publicaciones de la plataforma.</p>
                    </div>
                </a>
            </div>
        </section>
    </main>

    <script src="js/lienzo.js"></script>
</body>
</html>