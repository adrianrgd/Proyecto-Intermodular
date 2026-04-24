package com.modvalley.view;

import com.modvalley.model.Usuario;
import com.modvalley.Custom;

import java.util.Scanner;

public class MenuUsuario {
    public int mostrar(Scanner sc, Usuario user) {

        System.out.println(Custom.GRIS + "\n==== Panel de " + Custom.CURSIVA + user.getNickname() + Custom.RESET
                + Custom.GRIS + " ====" + Custom.RESET);
        System.out.println(Custom.VERDE + "1. Ver catalogo de mods" + Custom.RESET);
        System.out.println(Custom.VERDE + "2. Subir contenido" + Custom.RESET);
        System.out.println(Custom.VERDE + "3. Perfil" + Custom.RESET);
        System.out.println(Custom.ROJO + "4. Cerrar Sesion" + Custom.RESET);
        System.out.println(Custom.GRIS + "=========================" + Custom.RESET);
        System.out.print(Custom.GRIS + "Opcion: " + Custom.RESET);
        int opcion = sc.nextInt();

        if (opcion == 4) {
            System.out.println(Custom.ROJO + "Cerrando sesion...\n" + Custom.RESET);
        }

        return opcion;

    }
}
