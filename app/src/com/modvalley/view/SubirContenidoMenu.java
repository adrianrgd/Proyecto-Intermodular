package com.modvalley.view;

import com.modvalley.model.*;
import com.modvalley.Custom;
import com.modvalley.controller.*;

import java.util.Scanner;

public class SubirContenidoMenu {
    public void mostrar(Scanner sc, RecursoController recurso, Usuario autor, JuegoController juego,
            CategoriaController categoriaCtrl) {
        System.out.println(Custom.GRIS + "\n===== PUBLICAR UN MOD =====" + Custom.RESET);

        // 1. Elegir Juego
        for (Videojuego v : juego.getJuegos()) {
            System.out.println(Custom.VERDE + v.getIdjuego() + ". " + v.getNombre_juego() + Custom.RESET);
        }
        System.out.println(Custom.GRIS + "===========================" + Custom.RESET);
        System.out.print(Custom.GRIS + "Selecciona el juego (ID): " + Custom.RESET);
        int idJuego = sc.nextInt();
        sc.nextLine();

        // 2. Elegir Categoría
        System.out.println(Custom.GRIS + "\n=== CATEGORIAS ===" + Custom.RESET);
        for (Categoria cat : categoriaCtrl.obtenerCategorias()) {
            System.out.println(Custom.VERDE + cat.getIdCategoria() + ". " + cat.getNombre_cat() + Custom.RESET);
        }
        System.out.println(Custom.GRIS + "==================" + Custom.RESET);
        System.out.print(Custom.GRIS + "Categoria: " + Custom.RESET);
        int catNum = sc.nextInt();
        sc.nextLine();

        // 3. Datos del Mod
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Versión: ");
        String version = sc.nextLine();
        System.out.print("Descripción: ");
        String desc = sc.nextLine();

        Recurso nuevo = new Recurso(0, nombre, desc, version, 0, autor.getIdUsuario(), idJuego, catNum);
        recurso.subirMod(nuevo);

        System.out.println(Custom.VERDE + ">> ¡Mod guardado en la base de datos!" + Custom.RESET);
    }
}
