<?php
$host = "localhost";
$user = "root";
$pass = "mysql"; 
$db   = "modvalley";

$conn = new mysqli($host, $user, $pass, $db);

if ($conn->connect_error) {
    die("Fallo de conexión: " . $conn->connect_error);
}
?>