function descargarMod(id) {
    const formData = new FormData();
    formData.append('id_recurso', id);

    fetch('php/API/descargar.php', {
        method: 'POST',
        body: formData
    })
    .then(res => res.json())
    .then(data => {
        if (data.success) {
            const spanDescargas = document.getElementById("num_descargas-" + id);
            if (spanDescargas) {
                spanDescargas.innerHTML = `<i class="fa-solid fa-download"></i> ${data.num_descargas}`;
            }
            alert("¡Descarga iniciada correctamente!");
        } else {
            console.error("Error en la descarga:", data.message);
        }
    })
    .catch(err => console.error("Error de conexión:", err));
}

function verDetallesMod(id) {
    const modsGrid = document.getElementById('mods-grid');
    const feedHeader = document.querySelector('.feed-header');
    const sidebar = document.querySelector('.catalogo-sidebar');
    const container = document.querySelector('.catalogo-container');
    
    // Efecto de carga
    container.classList.add('transition-active');
    modsGrid.style.opacity = '0';
    
    setTimeout(() => {
        fetch(`mod_detalle.php?id=${id}`)
            .then(res => res.text())
            .then(html => {
                // Ocultar header del feed y sidebar
                feedHeader.style.display = 'none';
                sidebar.classList.add('hidden');
                container.classList.add('full-width');
                
                // Cargar contenido
                modsGrid.innerHTML = html;
                modsGrid.style.opacity = '1';
                modsGrid.classList.add('detalle-view');
                
                // Scroll al inicio
                window.scrollTo({ top: 0, behavior: 'smooth' });
                
                // Inicializar eventos del detalle (estrellas, form)
                initDetalleEvents();
                
                setTimeout(() => {
                    container.classList.remove('transition-active');
                }, 300);
            })
            .catch(err => {
                console.error("Error al cargar detalles:", err);
                modsGrid.style.opacity = '1';
                container.classList.remove('transition-active');
            });
    }, 300);
}

function volverAlCatalogo() {
    const modsGrid = document.getElementById('mods-grid');
    const feedHeader = document.querySelector('.feed-header');
    const sidebar = document.querySelector('.catalogo-sidebar');
    const container = document.querySelector('.catalogo-container');
    const activeJuego = document.querySelector('.juego-item.active');
    const idJuego = activeJuego ? activeJuego.getAttribute('data-juego') : '';
    
    container.classList.add('transition-active');
    modsGrid.style.opacity = '0';
    
    setTimeout(() => {
        fetch(`php/API/obtener_mods.php?juego=${idJuego}`)
            .then(res => res.text())
            .then(html => {
                feedHeader.style.display = 'flex';
                sidebar.classList.remove('hidden');
                container.classList.remove('full-width');
                
                modsGrid.innerHTML = html;
                modsGrid.style.opacity = '1';
                modsGrid.classList.remove('detalle-view');
                
                setTimeout(() => {
                    container.classList.remove('transition-active');
                }, 300);
            });
    }, 300);
}

function initDetalleEvents() {
    // Estrellas
    const stars = document.querySelectorAll('.star-opt');
    const puntuacionInput = document.getElementById('puntuacion-input');
    
    stars.forEach(star => {
        star.addEventListener('click', () => {
            const val = star.getAttribute('data-value');
            puntuacionInput.value = val;
            
            // Actualizar visual
            stars.forEach(s => {
                if (s.getAttribute('data-value') <= val) {
                    s.classList.remove('fa-regular');
                    s.classList.add('fa-solid');
                    s.classList.add('active');
                } else {
                    s.classList.remove('fa-solid');
                    s.classList.add('fa-regular');
                    s.classList.remove('active');
                }
            });
        });
    });

    // Formulario de comentario
    const formComentario = document.getElementById('form-comentario');
    if (formComentario) {
        formComentario.addEventListener('submit', (e) => {
            e.preventDefault();
            const formData = new FormData(formComentario);
            
            fetch('php/API/mod_acciones.php', {
                method: 'POST',
                body: formData
            })
            .then(res => res.json())
            .then(data => {
                if (data.success) {
                    // Recargar detalles para ver el nuevo comentario y media
                    verDetallesMod(formData.get('id_recurso'));
                } else {
                    alert(data.message || "Error al enviar comentario");
                }
            })
            .catch(err => console.error("Error:", err));
        });
    }
}

const juegoLinks = document.querySelectorAll('.juego-item');
const modsGrid = document.getElementById('mods-grid');
const feedTitle = document.getElementById('feed-title');

juegoLinks.forEach(link => {
    link.addEventListener('click', (e) => {
        e.preventDefault();
        
        const idJuego = link.getAttribute('data-juego');
        const nombreJuego = link.getAttribute('data-nombre');
        
        // Si estamos en vista detalle, mostrar header primero
        document.querySelector('.feed-header').style.display = 'flex';
        modsGrid.classList.remove('detalle-view');

        // Actualizar UI activa
        juegoLinks.forEach(l => l.classList.remove('active'));
        link.classList.add('active');
        
        // Efecto visual de carga
        modsGrid.style.opacity = '0.4';
        
        // Petición AJAX
        fetch(`php/API/obtener_mods.php?juego=${idJuego}`)
            .then(res => res.text())
            .then(html => {
                modsGrid.innerHTML = html;
                modsGrid.style.opacity = '1';
                
                // Reiniciar búsqueda si hay algo escrito
                if (searchInput) searchInput.value = '';
                
                // Actualizar título
                if (idJuego === "") {
                    feedTitle.textContent = "Los más populares";
                    window.history.pushState({}, '', 'catalogo.php');
                } else {
                    feedTitle.textContent = "Mods para " + nombreJuego;
                    window.history.pushState({}, '', `catalogo.php?juego=${idJuego}`);
                }
            })
            .catch(err => {
                console.error("Error al cargar mods:", err);
                modsGrid.style.opacity = '1';
            });
    });
});

// Búsqueda de Mods (Funciona con contenido dinámico)
const searchInput = document.getElementById('search-input');
if (searchInput) {
    searchInput.addEventListener('input', (e) => {
        const term = e.target.value.toLowerCase();
        const cards = document.querySelectorAll('.mod-card');
        cards.forEach(card => {
            const title = card.querySelector('h3').textContent.toLowerCase();
            const desc = card.querySelector('.mod-desc').textContent.toLowerCase();
            if (title.includes(term) || desc.includes(term)) {
                card.style.display = 'flex';
            } else {
                card.style.display = 'none';
            }
        });
    });
}

