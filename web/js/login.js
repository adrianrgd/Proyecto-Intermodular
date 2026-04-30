// Usuario existente
document.querySelectorAll('.btn-usuario').forEach(boton => {
    boton.addEventListener('click', () => {
        const id = boton.getAttribute('data-id');
        enviarLogin({ idUsuario: id }, 'php/api/api.php');
    });
});

// Nuevo Usuario
document.getElementById('form-registro').addEventListener('submit', (e) => {
    e.preventDefault();
    const datos = {
        nickname: document.getElementById('reg-nickname').value,
        email: document.getElementById('reg-email').value,
        biografia: document.getElementById('reg-bio').value
    };
    enviarLogin(datos, 'php/api/registro.php');
});

// Enviar Datos
function enviarLogin(datos, url) {
    fetch(url, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(datos)
    })
    .then(res => res.json())
    .then(data => {
        if (data.success) {
            window.location.href = 'index.php';
        } else {
            alert("Error: " + (data.message || "No se pudo procesar"));
        }
    });
}