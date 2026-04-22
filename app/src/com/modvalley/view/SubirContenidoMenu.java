package com.modvalley.view;

import com.modvalley.controller.GestionController;
import com.modvalley.model.Recurso;
import com.modvalley.model.Usuario;
import com.modvalley.model.Videojuego;
import java.util.Scanner;

public class SubirContenidoMenu {
    public void mostrar(Scanner sc, GestionController gestion, Usuario autor) {
        System.out.println("\n--- PUBLICAR NUEVO RECURSO ---");

        // 1. Elegir Juego
        for (Videojuego v : gestion.juegos) {
            System.out.println(v.getIdjuego() + ". " + v.getNombre_juego());
        }
        System.out.print("Selecciona el juego (ID): ");
        int idJuego = sc.nextInt();
        sc.nextLine();

        // 2. Elegir Categoría
        System.out.println("Categorías disponibles:");
        for (int i = 0; i < gestion.categorias.length; i++) {
            System.out.println((i + 1) + ". " + gestion.categorias[i]);
        }
        System.out.print("Selecciona categoría (número): ");
        int catNum = sc.nextInt();
        sc.nextLine();

        // 3. Datos del Mod
        System.out.print("Nombre del mod: ");
        String nombre = sc.nextLine();
        System.out.print("Versión (ej: 1.0.2): ");
        String version = sc.nextLine();
        System.out.print("Breve descripción: ");
        String desc = sc.nextLine();

        // Lógica de ID: idJuego * 1000 + (cantidad de mods + 1)
        int nuevoId = (idJuego * 1000) + (gestion.mods.size() + 1);

        Recurso nuevo = new Recurso(nuevoId, nombre, desc, version, idJuego, catNum, autor.getIdUsuario());
        gestion.mods.add(nuevo);

        System.out.println("\n>> ¡Éxito! Mod '" + nombre + "' publicado con ID: " + nuevoId);
    }
}