package com.modvalley.controller;

import java.util.ArrayList;
import com.modvalley.model.Recurso;

public class RecursoController {
    public ArrayList<Recurso> mods = new ArrayList<>();

    public RecursoController() {
        mods.add(new Recurso(101, "Optifine", "Mod para optimizar el juego", "1.16.5", 1, 1, 1));
        mods.add(new Recurso(102, "Sodium", "Mod para optimizar el juego", "1.16.5", 1, 1, 1));
        mods.add(new Recurso(103, "Xaero's Minimap", "Mod para optimizar el juego", "1.16.5", 1, 1, 3));
        mods.add(new Recurso(104, "Fusion", "Mod para optimizar el juego", "1.16.5", 2, 3, 1));
        mods.add(new Recurso(105, "Script Hook V", "Mod para optimizar el juego", "1.16.5", 2, 3, 1));
        mods.add(new Recurso(106, "GTA V Remastered", "Mod para optimizar el juego", "1.16.5", 2, 4, 2));
        mods.add(new Recurso(107, "Simple Zombies", "Mod para optimizar el juego", "1.16.5", 2, 4, 2));

    }

    public ArrayList<Recurso> filtrarPorJuego(int idJuego) {
        ArrayList<Recurso> filtrados = new ArrayList<>();
        for (Recurso mod : mods) {
            if (mod.getIdJuego() == idJuego) {
                filtrados.add(mod);
            }
        }
        return filtrados;
    }

    public ArrayList<Recurso> filtrarPorCategoria(int idCategoria) {
        ArrayList<Recurso> filtrados = new ArrayList<>();
        for (Recurso mod : mods) {
            if (mod.getIdCategoria() == idCategoria) {
                filtrados.add(mod);
            }
        }
        return filtrados;
    }

    public ArrayList<Recurso> filtrarPorVersion(String version) {
        ArrayList<Recurso> filtrados = new ArrayList<>();
        for (Recurso mod : mods) {
            if (mod.getVersion().equals(version)) {
                filtrados.add(mod);
            }
        }
        return filtrados;
    }

    public ArrayList<Recurso> filtrarPorAutor(int idUsuario) {
        ArrayList<Recurso> filtrados = new ArrayList<>();
        for (Recurso mod : mods) {
            if (mod.getIdAutor() == idUsuario) {
                filtrados.add(mod);
            }
        }
        return filtrados;
    }

    public ArrayList<Recurso> filtrarPorNombre(String nombre) {
        ArrayList<Recurso> filtrados = new ArrayList<>();
        for (Recurso mod : mods) {
            if (mod.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                filtrados.add(mod);
            }
        }
        return filtrados;
    }

    public int totalDescargasUsuario(int IdAutor) {
        int total = 0;
        for (Recurso mod : mods) {
            if (mod.getIdAutor() == IdAutor) {
                total += mod.getDescargas();
            }
        }
        return total;
    }

    public void registrarDescarga(int idMod) {
        for (Recurso r : mods) {
            if (r.getId() == idMod) {
                r.sumarDescarga();
            }
        }
    }

    public Recurso obtenerModPorId(int idMod) {
        for (Recurso r : mods) {
            if (r.getId() == idMod) {
                return r;
            }
        }
        return null; // Si no lo encuentra
    }
}
