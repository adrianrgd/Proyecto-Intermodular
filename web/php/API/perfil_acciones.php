<?php
session_start();
include '../../Config/db.php';

header('Content-Type: application/json');

if (!isset($_SESSION['usuario_logueado'])) {
    echo json_encode(['success' => false, 'message' => 'No has iniciado sesión']);
    exit;
}

$id_usuario = $_SESSION['usuario_logueado']['id_usuario'];
$accion = isset($_GET['accion']) ? $_GET['accion'] : '';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    
    // Actualiza los datos del usuario en la base de datos
    if ($accion === 'actualizar') {
        $nombre = $_POST['nombre'] ?? '';
        $nickname = $_POST['nickname'] ?? '';
        $biografia = $_POST['biografia'] ?? '';
        
        // Mantener la foto actual por defecto
        $foto_perfil = $_SESSION['usuario_logueado']['foto_perfil'];

        // Manejo de subida de archivo
        if (isset($_FILES['foto_perfil']) && $_FILES['foto_perfil']['error'] === UPLOAD_ERR_OK) {
            $fileTmpPath = $_FILES['foto_perfil']['tmp_name'];
            $fileName = $_FILES['foto_perfil']['name'];
            $fileNameCmps = explode(".", $fileName);
            $fileExtension = strtolower(end($fileNameCmps));

            $allowedfileExtensions = array('jpg', 'gif', 'png', 'jpeg', 'webp');
            if (in_array($fileExtension, $allowedfileExtensions)) {
                $uploadFileDir = '../../img/perfiles/';
                if (!is_dir($uploadFileDir)) {
                    mkdir($uploadFileDir, 0777, true);
                }
                $newFileName = md5(time() . $fileName) . '.' . $fileExtension;
                $dest_path = $uploadFileDir . $newFileName;

                if(move_uploaded_file($fileTmpPath, $dest_path)) {
                    $foto_perfil = 'img/perfiles/' . $newFileName;
                }
            }
        }

        $sql = "UPDATE USUARIO SET nombre = ?, nickname = ?, biografia = ?, foto_perfil = ? WHERE id_usuario = ?";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("ssssi", $nombre, $nickname, $biografia, $foto_perfil, $id_usuario);

        if ($stmt->execute()) {
            $_SESSION['usuario_logueado']['nombre'] = $nombre;
            $_SESSION['usuario_logueado']['nickname'] = $nickname;
            $_SESSION['usuario_logueado']['biografia'] = $biografia;
            $_SESSION['usuario_logueado']['foto_perfil'] = $foto_perfil;
            
            echo json_encode(['success' => true, 'foto_perfil' => $foto_perfil]);
        } else {
            echo json_encode(['success' => false, 'message' => 'Error al actualizar la base de datos']);
        }
    } 
    
    // Se elimina la cuenta del usuario, pero en BD quedan los comentarios y mods 
    // asociados a un id_usuario que ya no existe.
    else if ($accion === 'eliminar') {
        $sql = "DELETE FROM USUARIO WHERE id_usuario = ?";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("i", $id_usuario);

        if ($stmt->execute()) {
            // Se destruye la sesión
            session_destroy();
            echo json_encode(['success' => true]);
        } else {
            echo json_encode(['success' => false, 'message' => 'Error al eliminar la cuenta']);
        }
    }
} else {
    echo json_encode(['success' => false, 'message' => 'Método no permitido']);
}
?>
