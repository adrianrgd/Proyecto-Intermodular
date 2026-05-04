<?php
session_start();
include '../../Config/db.php';

header('Content-Type: application/json');

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $id_recurso = $_POST['id_recurso'] ?? null;

    if (!$id_recurso) {
        $json = json_decode(file_get_contents('php://input'), true);
        $id_recurso = $json['id_recurso'] ?? null;
    }

    if ($id_recurso) {
        $id_recurso = intval($id_recurso);
        
        // Incrementar descargas
        $sql = "UPDATE RECURSO SET num_descargas = num_descargas + 1 WHERE id_recurso = ?";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("i", $id_recurso);
        
        if ($stmt->execute()) {
            $res = $conn->query("SELECT num_descargas FROM RECURSO WHERE id_recurso = $id_recurso");
            $data = $res->fetch_assoc();
            
            echo json_encode([
                'success' => true, 
                'num_descargas' => $data['num_descargas']
            ]);
        } else {
            echo json_encode(['success' => false, 'message' => 'Error al actualizar']);
        }
    } else {
        echo json_encode(['success' => false, 'message' => 'ID de recurso no proporcionado']);
    }
} else {
    echo json_encode(['success' => false, 'message' => 'Método no permitido']);
}
