package com.modvalley.view;

import com.modvalley.model.Usuario;
import com.modvalley.Custom;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    public Usuario mostrar(Scanner sc, ArrayList<Usuario> listaUsuarios) {
        System.out.println(Custom.GRIS + "========== BIENVENIDO A MODVALLEY ==========" + Custom.RESET);
        System.out.println(Custom.VERDE + "\nSelecciona un Usuario: " + Custom.RESET);

        for (Usuario u : listaUsuarios) {
            System.out.println(u);
        }
        System.out.println("");

        System.out.print(Custom.VERDE + "Introduce el ID del usuario (" + Custom.ROJO + "0 -> Salir" + Custom.VERDE
                + "): " + Custom.RESET);
        int idElejido = sc.nextInt();

        if (idElejido == 0) {
            System.out.println(Custom.ROJO + "Saliendo..." + Custom.RESET);
            return null;
        }

        for (Usuario u : listaUsuarios) {
            if (u.getIdUsuario() == idElejido) {
                return u;
            }
        }

        System.out.println(Custom.ROJO + "Error! ID no valido." + Custom.RESET);
        return null;
    }
}
