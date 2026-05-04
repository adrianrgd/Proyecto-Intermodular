<?php
session_start();
include '../../Config/db.php';

header('Content-Type: application/json');

if (!isset($_SESSION['usuario_logueado'])) {
    echo json_encode(['success' => false, 'message' => 'Debes iniciar sesión.']);
    exit;
}

$id_usuario = $_SESSION['usuario_logueado']['id_usuario'];
$id_recurso = isset($_POST['id_recurso']) ? intval($_POST['id_recurso']) : null;
$puntuacion = isset($_POST['puntuacion']) ? intval($_POST['puntuacion']) : 0;
$comentario = isset($_POST['comentario']) ? trim($_POST['comentario']) : '';

if (!$id_recurso) {
    echo json_encode(['success' => false, 'message' => 'ID de recurso no válido.']);
    exit;
}

// Iniciar transacción para asegurar que ambos se guarden o ninguno
$conn->begin_transaction();

try {
    // 1. Guardar o actualizar valoración
    if ($puntuacion > 0) {
        $sql_check_val = "SELECT id_valoracion FROM VALORACION WHERE id_usuario = ? AND id_recurso = ?";
        $stmt_check = $conn->prepare($sql_check_val);
        $stmt_check->bind_param("ii", $id_usuario, $id_recurso);
        $stmt_check->execute();
        $res_check = $stmt_check->get_result();

        if ($res_check->num_rows > 0) {
            // Actualizar
            $sql_upd_val = "UPDATE VALORACION SET puntuacion = ? WHERE id_usuario = ? AND id_recurso = ?";
            $stmt_upd = $conn->prepare($sql_upd_val);
            $stmt_upd->bind_param("iii", $puntuacion, $id_usuario, $id_recurso);
            $stmt_upd->execute();
        } else {
            // Insertar
            $sql_ins_val = "INSERT INTO VALORACION (id_usuario, id_recurso, puntuacion) VALUES (?, ?, ?)";
            $stmt_ins = $conn->prepare($sql_ins_val);
            $stmt_ins->bind_param("iii", $id_usuario, $id_recurso, $puntuacion);
            $stmt_ins->execute();
        }
    }

    // 2. Guardar comentario si no está vacío
    if (!empty($comentario)) {
        $sql_ins_com = "INSERT INTO COMENTARIO (id_usuario, id_recurso, comentario, fecha) VALUES (?, ?, ?, NOW())";
        $stmt_com = $conn->prepare($sql_ins_com);
        $stmt_com->bind_param("iis", $id_usuario, $id_recurso, $comentario);
        $stmt_com->execute();
    }

    $conn->commit();
    echo json_encode(['success' => true]);

} catch (Exception $e) {
    $conn->rollback();
    echo json_encode(['success' => false, 'message' => 'Error en la base de datos: ' . $e->getMessage()]);
}
?>
