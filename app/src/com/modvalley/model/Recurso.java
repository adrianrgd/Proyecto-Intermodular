package app.src.com.modvalley.model;

public class Recurso {
    private int id;
    private String nombre;
    private int idJuego;
    private int idCategoria;
    private int descargas;

    public Recurso(int id, String nombre, int idJuego, int idCategoria) {
        this.id = id;
        this.nombre = nombre;
        this.idJuego = idJuego;
        this.idCategoria = idCategoria;
        this.descargas = 0;
    }

    public void sumarDescarga() {
        this.descargas++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Juego: " + idJuego + " | Categoria: " + idCategoria
                + " | Descargas: " + descargas;
    }
}