package com.modvalley.view;

import com.modvalley.model.*;
import com.modvalley.Custom;
import com.modvalley.controller.*;

import java.util.Scanner;

public class SubirContenidoMenu {
    public void mostrar(Scanner sc, RecursoController recurso, Usuario autor, JuegoControler juego) {
        System.out.println(Custom.GRIS + "\n===== PUBLICAR UN MOD =====" + Custom.RESET);

        // 1. Elegir Juego
        for (Videojuego v : juego.juegos) {
            System.out.println(Custom.VERDE + v.getIdjuego() + ". " + v.getNombre_juego() + Custom.RESET);
        }
        System.out.println(Custom.GRIS + "===========================" + Custom.RESET);
        System.out.print(Custom.GRIS + "Selecciona el juego (ID): " + Custom.RESET);
        int idJuego = sc.nextInt();
        sc.nextLine();

        // 2. Elegir Categoría
        System.out.println(Custom.GRIS + "\n=== CATEGORIAS ===" + Custom.RESET);
        for (int i = 0; i < juego.categorias.length; i++) {
            System.out.println(Custom.VERDE + (i + 1) + ". " + juego.categorias[i] + Custom.RESET);
        }
        System.out.println(Custom.GRIS + "==================" + Custom.RESET);
        System.out.print(Custom.GRIS + "Categoria: " + Custom.RESET);
        int catNum = sc.nextInt();
        sc.nextLine();

        // 3. Datos del Mod
        System.out.println(Custom.GRIS + "\n=== DATOS DEL MOD ===" + Custom.RESET);
        System.out.print(Custom.VERDE + "Nombre: " + Custom.RESET);
        String nombre = sc.nextLine();
        System.out.print(Custom.VERDE + "Versión: " + Custom.RESET);
        String version = sc.nextLine();
        System.out.print(Custom.VERDE + "Descripción: " + Custom.RESET);
        String desc = sc.nextLine();

        // Lógica de ID: idJuego * 1000 + (cantidad de mods + 1)
        int nuevoId = (idJuego * 1000) + (recurso.mods.size() + 1);

        Recurso nuevo = new Recurso(nuevoId, nombre, desc, version, idJuego, catNum, autor.getIdUsuario());
        recurso.mods.add(nuevo);

        System.out.println(
                Custom.VERDE + "\n>> ¡Éxito! Mod '" + nombre + "' publicado con ID: " + nuevoId + Custom.RESET);
    }
}