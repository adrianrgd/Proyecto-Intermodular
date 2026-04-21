package app.src.com.modvalley.model;

public class Videojuego {
    private int idjuego;
    private String nombre_juego;
    private String genero;
    private String plataforma;

    public Videojuego(int idjuego, String nombre_juego, String genero, String plataforma) {
        this.idjuego = idjuego;
        this.nombre_juego = nombre_juego;
        this.genero = genero;
        this.plataforma = plataforma;
    }

    public int getIdjuego() {
        return idjuego;
    }

    public void setIdjuego(int idjuego) {
        this.idjuego = idjuego;
    }

    public String getNombre_juego() {
        return nombre_juego;
    }

    public void setNombre_juego(String nombre_juego) {
        this.nombre_juego = nombre_juego;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    @Override
    public String toString() {
        return "ID: " + idjuego + " | Nombre: " + nombre_juego + " | Genero: " + genero + " | Plataforma: "
                + plataforma;
    }
}
