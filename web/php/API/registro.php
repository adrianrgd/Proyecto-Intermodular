<?php
session_start();
include '../Config/db.php';

$data = json_decode(file_get_contents("php://input"), true);

$nickname = $data['nickname'];
$email = $data['email'];
$bio = $data['biografia'] ?: "Sin biografía";

$sql = "INSERT INTO USUARIO (nickname, email, biografia) VALUES (?, ?, ?)";
$stmt = $conn->prepare($sql);
$stmt->bind_param("sss", $nickname, $email, $bio);

if ($stmt->execute()) {
    $nuevoId = $conn->insert_id;
    
    $res = $conn->query("SELECT * FROM USUARIO WHERE id_usuario = $nuevoId");
    $_SESSION['usuario_logueado'] = $res->fetch_assoc();
    
    echo json_encode(["success" => true]);
} else {
    echo json_encode(["success" => false, "message" => "El email ya existe"]);
}
?>