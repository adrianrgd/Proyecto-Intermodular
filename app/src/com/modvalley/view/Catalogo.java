package com.modvalley.view;

import com.modvalley.model.Videojuego;
import com.modvalley.model.Recurso;
import com.modvalley.controller.GestionController;

import java.util.ArrayList;
import java.util.Scanner;

public class Catalogo {
    public void mostrar(Scanner sc, GestionController gestion) {
        boolean enCatalogo = true;

        while (enCatalogo) {
            System.out.println("=== CATÁLOGO DE JUEGOS ===");
            for (Videojuego v : gestion.juegos) {
                System.out.println(v.getIdjuego() + ". " + v.getNombre_juego());
            }

            System.out.println("0 para volver al menu principal.");
            System.out.print("ID del juego: ");
            int idJuego = sc.nextInt();

            if (idJuego == 0) {
                enCatalogo = false;
            } else {
                mostrarModsJuego(sc, gestion, idJuego);
            }
        }
    }

    private void mostrarModsJuego(Scanner sc, GestionController gestion, int idJuego) {
        ArrayList<Recurso> filtrados = gestion.filtrarModsPorJuego(idJuego);

        System.out.println("=== MODS DISPONIBLES ===");
        if (filtrados.isEmpty()) {
            System.out.println("No hay mods disponibles para este juego.");
        } else {
            for (Recurso r : filtrados) {
                System.out.println(r.getId() + ". " + r.getNombre() + " | Categoria: " + r.getIdCategoria()
                        + " | Version: " + r.getVersion() + " | Descargas: " + r.getDescargas()
                        + " | Autor: " + r.getIdAutor());
            }
            System.out.println("Introduce '0' para volver al menu de juegos!");
            System.out.print("ID del mod: ");
            int idMod = sc.nextInt();
            if (idMod != 0) {
                for (Recurso r : gestion.mods) {
                    if (r.getId() == idMod) {
                        r.sumarDescarga();
                        System.out.println("Mod descargado correctamente.");
                    }
                }
            }
        }

    }
}
