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
    <title>Panel - ModValley</title>
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
                <li><a href="index.php"><i class="fa-solid fa-home"></i> Inicio</a></li> 
                <li><a href="catalogo.php"><i class="fa-solid fa-book"></i> Catálogo</a></li>
                <li><a href="subir_contenido.html"><i class="fa-solid fa-cloud-arrow-up"></i> Gestion</a></li>
                <li><a href="perfil.php"><i class="fa-solid fa-user"></i> Perfil</a></li>
                <li><a href="php/logout.php"><i class="fa-solid fa-right-from-bracket"></i></a></li>
            </ul>
        </nav>
    </header>
    <section class="panel-principal">
      <div class="bienvenida">
        <h1 class="titulo">Bienvenido a ModValley</h1>
        <p class="subtitulo">Explora y descarga mods para tus juegos favoritos</p>
      </div>
      <div class="panel_imagenes_principal">
        <div class="imagenes_panel">
          <img class="slide active" src="img/SinFoto.png" alt="Portada">
          <img class="slide" src="img/SinFoto2.png" alt="Portada">
          <img class="slide" src="img/SinFoto3.png" alt="Portada">
        </div>
      </div>
      <button class="boton_explorar" onclick="window.location.href='catalogo.html'">Explorar catálogo</button>
    </section>
    <script src="js/lienzo.js"></script>
    <script src="js/img_panel_slide.js"></script>
</body>
</html>