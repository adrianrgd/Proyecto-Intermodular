package com.modvalley.controller;

import com.modvalley.model.*;
import java.util.ArrayList;

public class GestionController {
    public ArrayList<Videojuego> juegos = new ArrayList<>();
    public ArrayList<Recurso> mods = new ArrayList<>();
    public String[] categorias = { "Textura", "Mod", "Herramienta", "Mapa" };

    public GestionController() {
        juegos.add(new Videojuego(1, "Minecraft", "Sandbox", "PC"));
        juegos.add(new Videojuego(2, "Cyberpunk 2077", "RPG", "PC"));

        // Mod de prueba: ID 1001 (Juego 1 + correlativo)
        mods.add(new Recurso(1001, "Optifine ", "Mejora el rendimiento.", "v1.0", 1, 1, 1));
    }

    // Calcula descargas totales de un usuario
    public int calcularDescargasTotales(int idUsuario) {
        int total = 0;
        for (Recurso r : mods) {
            if (r.getIdAutor() == idUsuario)
                total += r.getDescargas();
        }
        return total;
    }

    // Lista solo los mods subidos por un usuario
    public ArrayList<Recurso> obtenerModsPorAutor(int idUsuario) {
        ArrayList<Recurso> misMods = new ArrayList<>();
        for (Recurso r : mods) {
            if (r.getIdAutor() == idUsuario)
                misMods.add(r);
        }
        return misMods;
    }

    public ArrayList<Recurso> filtrarModsPorJuego(int idJuego) {
        ArrayList<Recurso> filtrados = new ArrayList<>();
        for (Recurso r : mods) {
            if (r.getIdJuego() == idJuego)
                filtrados.add(r);
        }
        return filtrados;
    }
}