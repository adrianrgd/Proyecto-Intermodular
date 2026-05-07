<?php
$host = "localhost";
$user = "root";
$pass = "mysql";
$db   = "ModValley";

$conn = new mysqli($host, $user, $pass, $db);

if ($conn->connect_error) {
    die(json_encode(["error" => "Fallo de conexión: " . $conn->connect_error]));
}
?>