package com.modvalley.view;

import com.modvalley.Custom;
import com.modvalley.controller.*;
import com.modvalley.model.*;
import java.util.Scanner;

public class GestionarContenido {
    public void mostrar(Scanner sc, RecursoController recurso, Usuario autor, JuegoController juego,
            CategoriaController categoriaCtrl) {
        System.out.println(Custom.GRIS + "\n=== GESTIONAR CONTENIDO ===" + Custom.RESET);

        System.out.println(Custom.VERDE + "1. Subir un mod" + Custom.RESET);
        System.out.println(Custom.VERDE + "2. Eliminar un mod" + Custom.RESET);
        System.out.println(Custom.ROJO + "0. Volver al Menu" + Custom.RESET);
        System.out.println(Custom.GRIS + "===========================" + Custom.RESET);
        System.out.print(Custom.GRIS + "Opcion: " + Custom.RESET);
        int opcion_gestion = sc.nextInt();
        sc.nextLine();
        switch (opcion_gestion) {
            case 1 -> new SubirContenido().mostrar(sc, recurso, autor, juego, categoriaCtrl);

            case 2 -> new EliminarContenido().mostrar(sc, recurso, autor, juego, categoriaCtrl);

            default -> System.out.println(Custom.ROJO + "\n>> Opción inválida." + Custom.RESET);
        }
    }
}
