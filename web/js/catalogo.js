const modsGrid    = document.getElementById('mods-grid');
const searchInput = document.getElementById('search-input');
const feedTitle   = document.querySelector('.content-title');
const contentCount = document.querySelector('.content-count');
const aside       = document.querySelector('.catalogo-aside');
const juegoLinks  = document.querySelectorAll('.juego-item');

// ── Funcion para actualizar el contador de resultados
function setCount(n) {
    if (!contentCount) return;
    contentCount.textContent = `${n} resultado${n !== 1 ? 's' : ''}`;
}

function setTitle(html) {
    if (!feedTitle) return;
    feedTitle.innerHTML = html;
}

// ── Descarga de un mod (actualiza contador sin recargar)
function descargarMod(id) {
    const formData = new FormData();
    formData.append('id_recurso', id);

    fetch('php/API/descargar.php', { method: 'POST', body: formData })
        .then(res => res.json())
        .then(data => {
            if (data.success) {
                const span = document.getElementById('num_descargas-' + id);
                if (span) {
                    span.innerHTML = `<i class="fa-solid fa-download"></i> ${data.num_descargas}`;
                }
                alert('¡Descarga iniciada correctamente!');
            } else {
                console.error('Error en la descarga:', data.message);
            }
        })
        .catch(err => console.error('Error de conexión:', err));
}

// ── Ver detalles de un mod 
function verDetallesMod(id) {
    modsGrid.style.opacity = '0';
    modsGrid.style.transition = 'opacity 0.25s';

    setTimeout(() => {
        fetch(`mod_detalle.php?id=${id}`)
            .then(res => res.text())
            .then(html => {
                // Ocultar aside y cabecera de contenido
                aside.style.display = 'none';

                // El wrapper pasa a 1 sola columna
                document.querySelector('.catalogo-wrapper').style.gridTemplateColumns = '1fr';

                // Ocultar title y contador
                document.querySelector('.content-header').style.display = 'none';

                // Cambiar clases del grid para el detalle
                modsGrid.className = 'detalle-view';
                modsGrid.innerHTML  = html;
                modsGrid.style.opacity = '1';

                window.scrollTo({ top: 0, behavior: 'smooth' });
                initDetalleEvents();
            })
            .catch(err => {
                console.error('Error al cargar detalles:', err);
                modsGrid.style.opacity = '1';
            });
    }, 250);
}

// ── Volver al catálogo (desde mod_detalle) 
function volverAlCatalogo() {
    modsGrid.style.opacity = '0';

    const activeJuego = document.querySelector('.juego-item.active');
    const idJuego     = activeJuego ? activeJuego.getAttribute('data-juego') || '' : '';

    setTimeout(() => {
        fetch(`php/API/obtener_mods.php?juego=${idJuego}`)
            .then(res => res.text())
            .then(html => {
                // Restaurar layout
                aside.style.display = '';
                document.querySelector('.catalogo-wrapper').style.gridTemplateColumns = '';
                document.querySelector('.content-header').style.display = '';

                // Restaurar grid según vista
                modsGrid.className = idJuego === '' ? 'juegos-grid' : 'mods-grid';
                modsGrid.innerHTML  = html;
                modsGrid.style.opacity = '1';

                actualizarTitulo(idJuego, activeJuego ? activeJuego.querySelector('span')?.textContent : '');
                if (searchInput) searchInput.value = '';
            })
            .catch(err => {
                console.error('Error al volver:', err);
                modsGrid.style.opacity = '1';
            });
    }, 250);
}

// ── Cambio de juego (sidebar)
juegoLinks.forEach(link => {
    link.addEventListener('click', e => {
        e.preventDefault();

        const idJuego     = link.getAttribute('data-juego') || '';
        const nombreJuego = link.querySelector('span')?.textContent || '';

        // Salir de vista detalle si estamos en ella
        modsGrid.className = '';
        document.querySelector('.content-header').style.display = '';

        // Marcar activo
        juegoLinks.forEach(l => l.classList.remove('active'));
        link.classList.add('active');

        // Fade out
        modsGrid.style.opacity = '0';
        modsGrid.style.transition = 'opacity 0.2s';

        fetch(`php/API/obtener_mods.php?juego=${idJuego}`)
            .then(res => res.text())
            .then(html => {
                // Ajustar grid según si es "todos" o "juego concreto"
                modsGrid.className  = idJuego === '' ? 'juegos-grid' : 'mods-grid';
                modsGrid.id         = 'mods-grid';
                modsGrid.innerHTML  = html;
                modsGrid.style.opacity = '1';

                actualizarTitulo(idJuego, nombreJuego);
                if (searchInput) searchInput.value = '';

                // Actualizar URL sin recargar
                const url = idJuego === '' ? 'catalogo.php' : `catalogo.php?juego=${idJuego}`;
                window.history.pushState({}, '', url);
            })
            .catch(err => {
                console.error('Error al cargar mods:', err);
                modsGrid.style.opacity = '1';
            });
    });
});

// ── Actualizar título y contador según juego seleccionado
function actualizarTitulo(idJuego, nombreJuego) {
    if (idJuego === '') {
        setTitle('<span class="title-label">Explorar</span>Lo más popular');
    } else {
        setTitle(`<span class="title-label">Mods para</span>${nombreJuego}`);
    }

    // Contar tarjetas visibles
    setTimeout(() => {
        const cards = modsGrid.querySelectorAll('.mod-card, .mod-row-card');
        setCount(cards.length);
    }, 50);
}

// ── Búsqueda en tiempo real
if (searchInput) {
    searchInput.addEventListener('input', e => {
        const term  = e.target.value.toLowerCase().trim();
        // Busca tanto mod-card (grid 4 col) como mod-row-card (lista)
        const cards = modsGrid.querySelectorAll('.mod-card, .mod-row-card');
        let visible = 0;

        cards.forEach(card => {
            const title = card.querySelector('h3')?.textContent.toLowerCase() || '';
            const desc  = card.querySelector('.mod-desc')?.textContent.toLowerCase() || '';
            const show  = title.includes(term) || desc.includes(term);
            card.style.display = show ? '' : 'none';
            if (show) visible++;
        });

        setCount(visible);
    });
}

// ── Eventos del detalle (estrellas + comentario)
function initDetalleEvents() {
    const stars          = document.querySelectorAll('.star-opt');
    const puntuacionInput = document.getElementById('puntuacion-input');

    stars.forEach(star => {
        star.addEventListener('click', () => {
            const val = star.getAttribute('data-value');
            if (puntuacionInput) puntuacionInput.value = val;

            stars.forEach(s => {
                const isActive = s.getAttribute('data-value') <= val;
                s.classList.toggle('fa-solid',   isActive);
                s.classList.toggle('fa-regular', !isActive);
                s.classList.toggle('active',      isActive);
            });
        });
    });

    const formComentario = document.getElementById('form-comentario');
    if (formComentario) {
        formComentario.addEventListener('submit', e => {
            e.preventDefault();
            const formData = new FormData(formComentario);

            fetch('php/API/mod_acciones.php', { method: 'POST', body: formData })
                .then(res => res.json())
                .then(data => {
                    if (data.success) {
                        verDetallesMod(formData.get('id_recurso'));
                    } else {
                        alert(data.message || 'Error al enviar comentario');
                    }
                })
                .catch(err => console.error('Error:', err));
        });
    }
}
