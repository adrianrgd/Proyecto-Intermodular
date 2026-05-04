document.addEventListener('DOMContentLoaded', () => {
    // Cambio de pestañas
    const tabs = document.querySelectorAll('.tab-btn');
    const contents = document.querySelectorAll('.tab-content');

    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            const target = tab.getAttribute('data-tab');

            // Actualizar pestaña
            tabs.forEach(t => t.classList.remove('active'));
            tab.classList.add('active');

            // Actualizar contenido
            contents.forEach(content => {
                content.classList.remove('active');
                if (content.id === `tab-${target}`) {
                    content.classList.add('active');
                }
            });
        });
    });

    // Formulario de editar perfil
    const formEditar = document.getElementById('form-editar-perfil');
    if (formEditar) {
        formEditar.addEventListener('submit', (e) => {
            e.preventDefault();
            
            const formData = new FormData(formEditar);

            fetch('php/API/perfil_acciones.php?accion=actualizar', {
                method: 'POST',
                body: formData
            })
            .then(res => res.json())
            .then(data => {
                if (data.success) {
                    alert('¡Perfil actualizado correctamente!');
                    // Actualizar elementos
                    const nickname = formData.get('nickname');
                    const nombre = formData.get('nombre');
                    if (nombre) {
                        document.getElementById('display-nombre').textContent = nombre;
                    } else if (nickname) {
                        document.getElementById('display-nombre').textContent = nickname;
                    }
                    if (nickname) {
                        document.getElementById('display-nickname').textContent = nickname;
                        document.title = `Perfil - ${nickname} | ModValley`;
                    }
                    if (data.foto_perfil) {
                        document.getElementById('avatar-preview').src = data.foto_perfil;
                    }

                } else {
                    alert('Error: ' + data.message);
                }
            })
            .catch(err => {
                console.error('Error:', err);
                alert('Ocurrió un error al procesar la solicitud.');
            });
        });
    }

    // Eliminar cuenta
    const btnEliminar = document.getElementById('btn-eliminar-cuenta');
    if (btnEliminar) {
        btnEliminar.addEventListener('click', () => {
            if (confirm('¿ESTÁS COMPLETAMENTE SEGURO? Esta acción no se puede deshacer y perderás el acceso a tu cuenta.')) {
                if (confirm('Segunda confirmación: ¿Realmente quieres borrar tu cuenta?')) {
                    fetch('php/API/perfil_acciones.php?accion=eliminar', {
                        method: 'POST'
                    })
                    .then(res => res.json())
                    .then(data => {
                        if (data.success) {
                            alert('Tu cuenta ha sido eliminada. Lamentamos verte partir.');
                            window.location.href = 'login.php';
                        } else {
                            alert('Error: ' + data.message);
                        }
                    });
                }
            }
        });
    }

    // Botón de cámara en el avatar para ir a ajustes
    const btnCamara = document.querySelector('.cambiar-foto-btn');
    if (btnCamara) {
        btnCamara.addEventListener('click', () => {
            const tabAjustes = document.querySelector('.tab-btn[data-tab="ajustes"]');
            if (tabAjustes) {
                tabAjustes.click();
                setTimeout(() => {
                    const inputFoto = document.querySelector('input[name="foto_perfil"]');
                    if (inputFoto) inputFoto.click(); // Abrir el selector de archivos directamente
                }, 100);
            }
        });
    }
});
