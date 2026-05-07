<?php
include 'db.php';
session_start();

// Leemos el JSON enviado por Fetch
$data = json_decode(file_get_contents("php://input"), true);
$id = $data['idUsuario'];

$sql = "SELECT * FROM USUARIO WHERE id_usuario = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $id);
$stmt->execute();
$usuario = $stmt->get_result()->fetch_assoc();

if ($usuario) {
    $_SESSION['usuario_logueado'] = $usuario; 
    echo json_encode(["success" => true]);
} else {
    echo json_encode(["success" => false]);
}
?>