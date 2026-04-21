package app.src.com.modvalley.controller;

import app.src.com.modvalley.model.*;
import java.util.ArrayList;

public class GestionController {
    public ArrayList<Videojuego> juegos = new ArrayList<>();
    public ArrayList<Recurso> mods = new ArrayList<>();

    public GestionController() {
        // DATOS DE PRUEBA
        juegos.add(new Videojuego(1, "Minecraft", "Sandbox", "PC"));
        juegos.add(new Videojuego(2, "Cyberpunk 2077", "RPG", "PC"));

        mods.add(new Recurso(101, "Optimización de FPS", 1, 1));
        mods.add(new Recurso(102, "Texturas 4K", 1, 1));
        mods.add(new Recurso(201, "Nuevos Implantes", 2, 2));
    }

    // Filtro para traer solo los mods del juego elegido
    public ArrayList<Recurso> filtrarModsPorJuego(int idJuego) {
        ArrayList<Recurso> filtrados = new ArrayList<>();
        for (Recurso m : mods) {
            if (m.getIdJuego() == idJuego)
                filtrados.add(m);
        }
        return filtrados;
    }
}