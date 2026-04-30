<?php 
session_start();
include 'Config/db.php'; 
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login - ModValley</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Bitcount+Grid+Double:wght@100..900&family=Lexend:wght@100..900&family=Peralta&family=Rubik:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">
</head>
<body>
    <canvas id="lienzo-fondo"></canvas>
    <section class="pagina-login">
        <div class="contenedor-doble">
            <div class="seccion-login">
                <h2 class="h2">¡Selecciona tu cuenta!</h2>
                <div class="cuentas">
                    <?php
                    $res = $conn->query("SELECT id_usuario, nickname FROM USUARIO");
                    while ($user = $res->fetch_assoc()): 
                    ?>
                        <button class="btn-usuario" data-id="<?php echo $user['id_usuario']; ?>">
                            <p><i class="fa-solid fa-user"></i> <?php echo $user['nickname']; ?></p>
                        </button>
                    <?php endwhile; ?>
                </div>
            </div>

            <div class="divisor"></div>

            <div class="seccion-registro">
                <h2 class="h2">¿Eres nuevo? Regístrate</h2>
                <form id="form-registro" class="form-generico">
                    <input type="text" id="reg-nickname" placeholder="Tu Nickname" required>
                    <input type="email" id="reg-email" placeholder="Tu Email" required>
                    <textarea id="reg-bio" placeholder="Cuéntanos algo sobre ti... (opcional)"></textarea>
                    <button type="submit" class="btn-registro">Crear Cuenta y Entrar</button>
                </form>
            </div>

        </div>
    </section>
    <script src="js/login.js"></script>
    <script src="js/lienzo.js"></script>
</body>
</html>