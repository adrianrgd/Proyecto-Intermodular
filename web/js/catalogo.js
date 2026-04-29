const button = document.getElementsByTagName("button");

for (let i = 0; i < button.length; i++) {
    button[i].addEventListener("click", () => {
        window.location.href = "catalogo.recursos.html";
    
    });
}