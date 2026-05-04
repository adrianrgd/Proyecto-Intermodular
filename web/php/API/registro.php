<?php
include '../Config/db.php';
session_start();

$data = json_decode(file_get_contents("php://input"), true);

$nickname = $data['nickname'];
$email = $data['email'];
$bio = $data['biografia'] ?: "Sin biografía";
$nombre = $nickname;

$sql = "INSERT INTO USUARIO (nickname, nombre, email, biografia) VALUES (?, ?, ?, ?)";
$stmt = $conn->prepare($sql);
$stmt->bind_param("ssss", $nickname, $nombre, $email, $bio);

if ($stmt->execute()) {
    $nuevoId = $conn->insert_id;
    $res = $conn->query("SELECT * FROM USUARIO WHERE id_usuario = $nuevoId");
    $_SESSION['usuario_logueado'] = $res->fetch_assoc();
    echo json_encode(["success" => true]);
} else {
    echo json_encode(["success" => false, "message" => "Error al registrar: el email o nick ya existen"]);
}
?>