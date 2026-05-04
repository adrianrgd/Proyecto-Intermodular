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