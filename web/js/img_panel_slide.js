const slides = document.querySelectorAll('.slide');
let currentIndex = 0;

function cambiarImagen() {
    slides[currentIndex].classList.remove('active');

    currentIndex = (currentIndex + 1) % slides.length;

    slides[currentIndex].classList.add('active');

}

setInterval(cambiarImagen, 10000);