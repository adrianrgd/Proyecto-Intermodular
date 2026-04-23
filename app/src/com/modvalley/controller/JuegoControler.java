package com.modvalley.controller;

import java.util.ArrayList;
import com.modvalley.model.*;

public class JuegoControler {
    public ArrayList<Videojuego> juegos = new ArrayList<>();

    public final String[] categorias = { "Texturas", "Mods", "Mapas", "Herramientas" };

    public JuegoControler() {
        juegos.add(new Videojuego(1, "Minecraft Java", "Sandbox", "PC"));
        juegos.add(new Videojuego(2, "Grand Theft Auto V", "Acción", "PC"));
    }

    public String obtenerNombreJuego(int idJuego) {
        for (Videojuego juego : juegos) {
            if (juego.getIdjuego() == idJuego) {
                return juego.getNombre_juego();
            }
        }
        return "Desconocido";
    }

    public String obtenerNombreCat(int idCat) {
        if (idCat >= 1 && idCat <= categorias.length) {
            return categorias[idCat - 1];
        }
        return "Desconocido";
    }
}
