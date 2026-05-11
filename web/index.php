<?php 
session_start();
include 'Config/db.php'; 

if (!isset($_SESSION['usuario_logueado'])) {
    header("Location: login.php");
    exit;
}

$user = $_SESSION['usuario_logueado'];
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio - ModValley</title>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lexend:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>
    <canvas id="lienzo-fondo"></canvas>

    <header class="header">
        <nav class="nav">
            <ul>
                <li><a href="index.php" class="active"><i class="fa-solid fa-home"></i> Inicio</a></li> 
                <li><a href="catalogo.php"><i class="fa-solid fa-book"></i> Catálogo</a></li>
                <li><a href="gestion.php"><i class="fa-solid fa-cloud-arrow-up"></i> Gestión</a></li>
                <li><a href="perfil.php"><i class="fa-solid fa-user"></i> Perfil</a></li>
                <li><a href="php/logout.php"><i class="fa-solid fa-right-from-bracket"></i></a></li>
            </ul>
        </nav>
    </header>

    <main class="index-container">
        
        <section class="panel-principal">
            <div class="panel_imagenes_principal">
                <div class="imagenes_panel">
                    <img class="slide active" src="img/Minecraft_Portada.png" alt="Portada">
                    <img class="slide" src="img/GTAV_Portada.jpg" alt="Portada">
                    <img class="slide" src="img/CitiesSkylines_Portada.jpg" alt="Portada">
                </div>
            </div>
            
            <div class="bienvenida">
                <h1 class="titulo">Bienvenido a ModValley</h1>
                <p class="subtitulo">Explora y descarga mods para tus juegos favoritos</p>
                <button class="boton_explorar" onclick="window.location.href='catalogo.php'">Explorar catálogo</button>
            </div>
        </section>

        <section class="feed-informativo">
            <div class="glass-bloque">
                <div class="bloque-texto">
                    <h2>Preservando Cultura, Historia y Creatividad</h2>
                    <p>Bienvenido a ModValley, tu destino principal para descubrir y compartir mods. Explora nuestro catálogo en constante crecimiento, descarga herramientas de alta calidad y únete a nuestra comunidad de entusiastas del modding.</p>
                </div>
                <div class="bloque-icono">
                    <i class="fa-solid fa-earth-americas"></i>
                </div>
            </div>

            <div class="glass-bloque inverso">
                <div class="bloque-icono">
                    <i class="fa-solid fa-code-branch"></i>
                </div>
                <div class="bloque-texto">
                    <h2>De Modders para Modders</h2>
                    <p>¿Has creado una textura, un mapa o un script increíble? Súbelo en segundos a la plataforma. Recibe feedback de otros jugadores, gestiona tus versiones y contribuye a llevar los juegos al siguiente nivel.</p>
                </div>
            </div>
        </section>

        <footer class="footer-basico">
            <div class="footer-content">
                <div class="footer-logo">ModValley</div>
                <div class="footer-social">
                    <a href="https://github.com/adrianrgd/Proyecto-Intermodular" target="_blank"><i class="fa-brands fa-github"></i></a>
                    <a href="#"><i class="fa-brands fa-discord"></i></a>
                    <a href="#"><i class="fa-solid fa-envelope"></i></a>
                </div>
                <p class="footer-copy">&copy; 2026 - Proyecto Intermodular DAW</p>
            </div>
        </footer>

    </main>

    <script src="js/lienzo.js"></script>
    <script src="js/img_panel_slide.js"></script>
</body>
</html>