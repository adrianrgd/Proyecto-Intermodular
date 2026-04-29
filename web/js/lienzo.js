//Funcion para crear un lienzo de fondo animado
(function () {
  const lienzo = document.getElementById("lienzo-fondo");
  if (!lienzo) return;
  const contexto = lienzo.getContext("2d");
  let ancho, alto, particulas;
  const PALETA = ["#6688ff", "#ffb9dbff", "#4ddd72", "#ffc85a", "#a78bfa"];

  function redimensionar() {
    ancho = lienzo.width = window.innerWidth;
    alto = lienzo.height = window.innerHeight;
    particulas = Array.from(
      { length: Math.round((ancho * alto) / 6000) },
      () => ({
        x: Math.random() * ancho,
        y: Math.random() * alto,
        r: Math.random() * 3 + 1.5,
        vx: (Math.random() - 0.7) * 0.1,
        vy: (Math.random() - 0.7) * 0.1,
        c: PALETA[Math.floor(Math.random() * PALETA.length)],
        a: Math.random() * 0.3 + 0.2,
      }),
    );
  }

  function actualizar() {
    contexto.clearRect(0, 0, ancho, alto);
    for (const p of particulas) {
      contexto.beginPath();
      contexto.arc(p.x, p.y, p.r, 0, Math.PI * 2); 
      contexto.fillStyle = p.c;
      contexto.globalAlpha = p.a;
      contexto.fill();
      p.x += p.vx;
      p.y += p.vy;
      if (p.x < 0) p.x = ancho;
      if (p.x > ancho) p.x = 0;
      if (p.y < 0) p.y = alto;
      if (p.y > alto) p.y = 0;
    }
    contexto.globalAlpha = 1;
    requestAnimationFrame(actualizar);
  }

  redimensionar();
  window.addEventListener("resize", redimensionar);
  actualizar();
})();